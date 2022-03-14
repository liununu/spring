package me.liununu.springgraphql

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class MembersDataFetcher(private val repository: MembersRepository) {

    @DgsQuery
    fun members(): List<Member> = this.repository.findAll()
}
