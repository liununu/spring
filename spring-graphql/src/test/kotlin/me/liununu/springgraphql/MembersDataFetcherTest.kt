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

    @Test
    internal fun `should return simple pageable members`() {
        // given when
        val count = 15
        val pageInfoJsonPath = """$.data.simplePageableMembers.pageInfo"""
        val edgesJsonPath = """$.data.simplePageableMembers.edges"""

        val firstPartMembersQuery = buildSimplePageableMembersQuery(count)
        val firstPartPageInfo = executor.executeAndExtractPageInfo(firstPartMembersQuery, pageInfoJsonPath)
        val firstPartMemberEdges = executor.executeAndExtractMemberEdges(firstPartMembersQuery, edgesJsonPath)

        val secondPartMembersQuery = buildSimplePageableMembersQuery(count, firstPartPageInfo.endCursor)
        val secondPartPageInfo = executor.executeAndExtractPageInfo(secondPartMembersQuery, pageInfoJsonPath)
        val secondPartMemberEdges = executor.executeAndExtractMemberEdges(secondPartMembersQuery, edgesJsonPath)

        val thirdPartMembersQuery = buildSimplePageableMembersQuery(count, secondPartPageInfo.endCursor)
        val thirdPartPageInfo = executor.executeAndExtractPageInfo(thirdPartMembersQuery, pageInfoJsonPath)
        val thirdPartMemberEdges = executor.executeAndExtractMemberEdges(thirdPartMembersQuery, edgesJsonPath)

        // then
        assertThat(firstPartPageInfo.hasPreviousPage).isFalse
        assertThat(firstPartPageInfo.hasNextPage).isTrue
        assertThat(firstPartMemberEdges).hasSize(count)
        assertThat(firstPartMemberEdges.first().node).isEqualTo(Member("JHN38EVY0ZS", "Carla Cash", 22))

        assertThat(secondPartPageInfo.hasPreviousPage).isTrue
        assertThat(secondPartPageInfo.hasNextPage).isTrue
        assertThat(secondPartMemberEdges).hasSize(count)
        assertThat(secondPartMemberEdges.last().node).isEqualTo(Member("LSL41TQB5LD", "Nathaniel Wyatt", 28))

        assertThat(thirdPartPageInfo.hasPreviousPage).isTrue
        assertThat(thirdPartPageInfo.hasNextPage).isFalse
        assertThat(thirdPartMemberEdges).hasSize(3)
        assertThat(thirdPartMemberEdges.first().node).isEqualTo(Member("WCT75WSY2GL", "Mikayla Romero", 30))
    }

    private fun DgsQueryExecutor.executeAndExtractMemberEdges(query: String, jsonPath: String): List<MemberEdge> =
        this.executeAndExtractJsonPathAsObject(query, jsonPath, object : TypeRef<List<MemberEdge>>() {})

    private fun DgsQueryExecutor.executeAndExtractPageInfo(query: String, jsonPath: String): PageInfo =
        this.executeAndExtractJsonPathAsObject(query, jsonPath, PageInfo::class.java)

    private data class MemberEdge(val cursor: String, val node: Member)

    private data class PageInfo(
        val hasPreviousPage: Boolean,
        val hasNextPage: Boolean,
        val startCursor: String?,
        val endCursor: String?
    )

    private fun buildSimplePageableMembersQuery(count: Int, cursor: String? = null) = """
        {
            simplePageableMembers(first:$count ${cursor?.let { """after: "$it"""" }.orEmpty()}) {
                edges {
                    cursor
                    node {
                        id
                        name
                        age
                    }
                }
                pageInfo {
                    hasPreviousPage
                    hasNextPage
                    startCursor
                    endCursor
                }
            }
        }
    """.trimIndent()
}