package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.Pick

data class PickResponse(
    val pickId: Long,
    val itemId: Long,
    val pickCount: Int,
) {
    companion object {
        fun from(pick: Pick): PickResponse {
            return PickResponse(
                pick.id,
                pick.itemId,
                pick.pickCount
            )
        }
    }
}

data class PickList(
    val totalCount: Int,
    val itemList: List<PickResponse>,
)

data class PickItem(
    val imageUrl: String,
    val itemName: String,
    val itemPrice: Int,
)
