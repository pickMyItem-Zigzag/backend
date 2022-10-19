package kakaostyle.pickMyItem.wishitem.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.wishitem.service.ItemService
import kakaostyle.pickMyItem.wishitem.dto.ItemList
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