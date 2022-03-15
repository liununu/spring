package me.liununu.springgraphql

import com.jayway.jsonpath.TypeRef
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
        // given
        val query = """
            {
                members {
                    id
                    name
                    age
                }
            }
        """.trimIndent()

        // when
        val result = executor.executeAndExtractJsonPathAsObject(
            query,
            """$.data.members""",
            object : TypeRef<List<Member>>() {}
        )

        // then
        assertThat(result).hasSize(33)
        assertThat(result.first()).isEqualTo(Member("JHN38EVY0ZS", "Carla Cash", 22))
        assertThat(result.last()).isEqualTo(Member("CWJ73UWO2WR", "Jolie Nixon", 18))
    }
}