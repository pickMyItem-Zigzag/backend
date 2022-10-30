package kakaostyle.pickMyItem.wishitem.dto

import kakaostyle.pickMyItem.wishitem.domain.Item

data class ItemList(
    val totalCount: Int,
    val itemList: List<ItemInfoResponse>
)

data class ItemInfoInput(
    val itemId: Long,
    val itemName: String,
    val itemImageUrl: String,
    val originPrice: Int,
)

data class ItemInfoResponse(
    val itemId: Long,
    val brandName: String,
    val itemName: String,
    val itemImageUrl: String,
    val originPrice: Int,
) {
    companion object {
        fun from(item: Item): ItemInfoResponse {
            return ItemInfoResponse(
                item.id,
                item.brandName,
                item.itemName,
                item.itemImageUrl,
                item.originPrice
            )
        }
    }
}