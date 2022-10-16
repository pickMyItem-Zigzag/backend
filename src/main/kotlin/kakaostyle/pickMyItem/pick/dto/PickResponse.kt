package kakaostyle.pickMyItem.pick.dto

import kakaostyle.pickMyItem.pick.domain.Pick

data class PickResponse (
    val id: Long,
    val productName: String,
    val pickCount: Int,
    val imageUrl: String?,
) {
    companion object {
        fun from(pick: Pick): PickResponse {
            return PickResponse(
                pick.id,
                pick.productName,
                pick.pickCount,
                pick.imageUrl,
            )
        }
    }
}

data class PickItem(
    val imageUrl: String,
    val itemName: String,
    val itemPrice: Int,
)
