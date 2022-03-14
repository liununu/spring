package me.liununu.springgraphql

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import reactor.core.publisher.Flux

@DgsComponent
class MembersDataFetcher(private val repository: MembersRepository) {

    @DgsQuery
    fun members(): Flux<Member> = this.repository.findAll()

    @DgsQuery
    fun simplePageableMembers(
        env: DataFetchingEnvironment,
        @InputArgument first: Int,
        @InputArgument after: String?
    ): Connection<Member> = SimpleListConnection(this.repository.findAllWithBlocking()).get(env)
}
