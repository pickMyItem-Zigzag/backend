package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.itempick.service.PickService
import org.springframework.stereotype.Component

@Component
class PickMutationResolver(
    private val pickService: PickService
): GraphQLMutationResolver {

    fun pickItem(postId: Long, itemId: Long): Boolean {
        pickService.pickThisItem(postId, itemId)
        return true
    }

    fun unPickItem(postId: Long, itemId: Long): Boolean {
        pickService.unPickThisItem(postId, itemId)
        return true
    }
}