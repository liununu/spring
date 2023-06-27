package me.liununu.springdata.query.finder

import jakarta.persistence.EntityManager
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

        val query = criteriaBuilder.createQuery(Assignment::class.java)
        val employeeRoot = query.from(Employee::class.java)
        val projectRoot = query.from(Project::class.java)

        return query.multiselect(
            employeeRoot.get<String>(Employee::id.name),
            employeeRoot.get<String>(Employee::name.name),
            employeeRoot.get<String>(Employee::email.name),
            projectRoot.get<String>(Project::name.name),
            projectRoot.get<LocalDate>(Project::startDate.name),
            projectRoot.get<LocalDate>(Project::endDate.name),
        ).where(
            criteriaBuilder.equal(
                employeeRoot.get<String>(Employee::projectId.name),
                projectRoot.get<String>(Project::id.name)
            ),
            criteriaBuilder.isNotNull(employeeRoot.get<String>(Employee::projectId.name))
        )
            .let { entityManager.createQuery(it) }
            .resultList
    }
}
