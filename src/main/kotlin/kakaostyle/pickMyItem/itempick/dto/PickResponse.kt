package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.Pick

data class PickResponse (
    val pickId: Long,
    val itemId: Long,
    val itemName: String,
    val pickCount: Int,
    val imageUrl: String?,
) {
    companion object {
        fun from(pick: Pick): PickResponse {
            return PickResponse(
                pick.id,
                pick.itemId,
                pick.itemName,
                pick.pickCount,
                pick.itemImageUrl,
            )
        }
    }
}

data class PickItem(
    val imageUrl: String,
    val itemName: String,
    val itemPrice: Int,
)
