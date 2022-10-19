package kakaostyle.pickMyItem.wishitem.dto

import kakaostyle.pickMyItem.wishitem.domain.Item

data class ItemList(
    val totalCount: Int,
    val itemList: List<ItemInfoResponse>
)

data class ItemInfoInput(
    val productName: String,
    val productImageUrl: String,
    val originPrice: Int,
)

data class ItemInfoResponse(
    val id: Long,
    val productName: String,
    val productImageUrl: String,
    val originPrice: Int,
) {
    companion object {
        fun from(item: Item): ItemInfoResponse {
            return ItemInfoResponse(
                item.id,
                item.productName,
                item.productImageUrl,
                item.originPrice
            )
        }
    }
}