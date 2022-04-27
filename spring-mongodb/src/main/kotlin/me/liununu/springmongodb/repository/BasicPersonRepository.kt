package me.liununu.springmongodb.repository

import me.liununu.springmongodb.entity.BasicPerson
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.aggregation.Aggregation.ROOT
import org.springframework.data.mongodb.core.aggregation.Aggregation.lookup
import org.springframework.data.mongodb.core.aggregation.Aggregation.match
import org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation
import org.springframework.data.mongodb.core.aggregation.Aggregation.project
import org.springframework.data.mongodb.core.aggregation.Aggregation.replaceRoot
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.ArrayElemAt.arrayOf
import org.springframework.data.mongodb.core.aggregation.ObjectOperators.valueOf
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class BasicPersonRepository(private val mongoTemplate: MongoTemplate) {

    fun findFullNameWithFullAddressByPersonId(pid: String): BasicPerson? {
        val match = match(where("pid").isEqualTo(pid))
        val lookup = lookup("addresses", "pid", "pid", "addresses")
        val replaceRoot = replaceRoot(valueOf(arrayOf("\$addresses").elementAt(0)).mergeWith(ROOT))
        val project = project()
            // ignore these unsafe implementations, just for demo
            .and("first_name").concat(" ", "\$last_name").`as`("full_name")
            .and("country").concat(", ", "\$street", ", ", "\$postal_code").`as`("full_address")
        val aggregation = newAggregation(match, lookup, replaceRoot, project)

        return mongoTemplate.aggregate(aggregation, "persons", BasicPerson::class.java)
            .mappedResults
            .firstOrNull()
    }
}
