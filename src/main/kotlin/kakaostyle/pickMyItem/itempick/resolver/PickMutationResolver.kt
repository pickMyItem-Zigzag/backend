package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.itempick.service.PickService
import org.springframework.stereotype.Component

@Component
class PickMutationResolver(
    private val pickService: PickService
): GraphQLMutationResolver {

    fun pickThisItem(userId: Long, postId: Long, itemId: Long): Boolean {
        pickService.pickThisItem(userId, postId, itemId)
        return true
    }

    fun unPickThisItem(userId: Long, postId: Long, itemId: Long): Boolean {
        pickService.unPickThisItem(userId, postId, itemId)
        return true
    }
}