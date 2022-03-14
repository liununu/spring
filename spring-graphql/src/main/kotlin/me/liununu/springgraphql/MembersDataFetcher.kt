package me.liununu.springgraphql

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import reactor.core.publisher.Flux

@DgsComponent
class MembersDataFetcher(private val repository: MembersRepository) {

    @DgsQuery
    fun members(): Flux<Member> = this.repository.findAll()
}
