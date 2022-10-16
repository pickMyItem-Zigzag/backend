package kakaostyle.pickMyItem.item.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.item.service.ItemService
import kakaostyle.pickMyItem.utils.ItemList
import org.springframework.stereotype.Component

@Component
class ItemQueryResolver(
    private val itemService: ItemService
) : GraphQLQueryResolver {

    fun getItemInfoListByIdList(idList: List<Long>): ItemList {
        val itemInfoList = itemService.getItemInfoListByIdList(idList)
        return ItemList(
            itemInfoList.size,
            itemInfoList
        )
    }

    fun getItemWishList(): ItemList {
        val itemWishList = itemService.getItemWishList()
        return ItemList(
            itemWishList.size,
            itemWishList
        )
    }
}