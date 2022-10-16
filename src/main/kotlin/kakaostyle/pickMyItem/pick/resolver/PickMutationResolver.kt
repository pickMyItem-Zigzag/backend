package kakaostyle.pickMyItem.pick.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.pick.service.PickService
import org.springframework.stereotype.Component

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