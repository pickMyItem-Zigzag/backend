package kakaostyle.pickMyItem.utils

import kakaostyle.pickMyItem.board.dto.BoardResponse
import kakaostyle.pickMyItem.item.domain.Item

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

data class ItemInfoInput(
    val productName: String,
    val productImageUrl: String,
    val originPrice: Int,
)

data class BoardList(
    val totalCount: Int,
    val itemList: List<BoardResponse>
)

data class ItemList(
    val totalCount: Int,
    val itemList: List<ItemInfoResponse>
)