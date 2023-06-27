package me.liununu.springdata.query.finder

import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.CriteriaBuilder
import me.liununu.springdata.entity.Employee
import me.liununu.springdata.entity.Project
import me.liununu.springdata.query.Assignment
import me.liununu.springdata.query.Assignments
import me.liununu.springdata.query.finder.AssignmentsFinder.Name.JPA_CRITERIA
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class JPACriteriaFinder(private val entityManager: EntityManager) : AssignmentsFinder {
    override val name = JPA_CRITERIA

    override fun findAll(): Assignments {
        val criteriaBuilder = entityManager.criteriaBuilder

        val projects = findAssignedProjects(criteriaBuilder).associateBy { it.name }

        val criteriaQuery = criteriaBuilder.createQuery(Assignment::class.java)
        val employeeRoot = criteriaQuery.from(Employee::class.java)

        // Left join requires the existing association between entities
        return criteriaQuery.multiselect(
            employeeRoot.get<String>(Employee::id.name),
            employeeRoot.get<String>(Employee::name.name),
            employeeRoot.get<String>(Employee::email.name),
            employeeRoot.get<String>(Employee::projectName.name),
            criteriaBuilder.nullLiteral(LocalDate::class.java),
            criteriaBuilder.nullLiteral(LocalDate::class.java),
        )
            .where(employeeRoot.get<String>(Employee::projectName.name).isNotNull)
            .let { entityManager.createQuery(it) }
            .resultList
            .map {
                it.copy(
                    projectStartDate = projects[it.projectName]?.startDate,
                    projectEndDate = projects[it.projectName]?.endDate
                )
            }
    }

    private fun findAssignedProjects(criteriaBuilder: CriteriaBuilder): List<ProjectInfo> {
        val criteriaQuery = criteriaBuilder.createQuery(ProjectInfo::class.java)
        val projectRoot = criteriaQuery.from(Project::class.java)

        val subquery = criteriaQuery.subquery(String::class.java)
        val employeeRoot = subquery.from(Employee::class.java)
        val distinctProjectNames = subquery
            .select(employeeRoot.get(Employee::projectName.name))
            .where(employeeRoot.get<Boolean>(Employee::projectName.name).isNotNull)
            .distinct(true)

        return criteriaQuery.multiselect(
            projectRoot.get<String>(Project::name.name),
            criteriaBuilder.least(projectRoot.get<LocalDate>(Project::startDate.name)),
            criteriaBuilder.greatest(projectRoot.get<LocalDate>(Project::endDate.name)),
        )
            .where(projectRoot.get<String>(Project::name.name).`in`(distinctProjectNames))
            .groupBy(projectRoot.get<String>(Project::name.name))
            .let { entityManager.createQuery(it) }
            .resultList
    }

    private data class ProjectInfo(val name: String, val startDate: LocalDate?, val endDate: LocalDate?)
}
