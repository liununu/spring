package me.liununu.springgraphql

import com.netflix.graphql.dgs.DgsQueryExecutor
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MembersDataFetcherTest {
    @Autowired
    private lateinit var executor: DgsQueryExecutor

    @Test
    internal fun `should return all members`() {
        val query = """
            {
                members {
                    id
                    name
                    age
                }
            }
        """.trimIndent()

        val result = executor.execute(query)

        assertThat(result.errors).isEmpty()
        val data = result.getData<Map<String, List<Member>>>()
        assertThat(data).containsOnlyKeys("members")
        val members = data.getValue("members")
        assertThat(members).hasSize(33)
    }
}