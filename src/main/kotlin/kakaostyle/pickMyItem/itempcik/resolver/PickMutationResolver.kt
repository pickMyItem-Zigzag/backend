package kakaostyle.pickMyItem.itempcik.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.itempcik.service.PickService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

@Component
class PickMutationResolver(
    private val pickService: PickService
): GraphQLMutationResolver {

    fun pickItem(pickId: Long): Boolean {
        pickService.pickThisItem(pickId)
        return true
    }

    fun unPickItem(pickId: Long): Boolean {
        pickService.unPickThisItem(pickId)
        return true
    }
}