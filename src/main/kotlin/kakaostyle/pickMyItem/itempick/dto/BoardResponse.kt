package kakaostyle.pickMyItem.itempick.dto

import kakaostyle.pickMyItem.itempick.domain.Board
import kakaostyle.pickMyItem.utils.isNullOrFalse

data class BoardResponse(
    val id: Long,
    val boardName: String,
    val postIdList: List<Long>
) {
    companion object {
        fun from(
            board: Board
        ): BoardResponse {
            val postIdList = board.postList.filter { it.deleted.isNullOrFalse() }.map { it.id }
            return BoardResponse(
                board.id,
                boardName = board.boardName,
                postIdList = postIdList
            )
        }
    }
}

data class CreateBoardInput(
    val boardName: String
)

data class BoardList(
    val totalCount: Int,
    val itemList: List<BoardResponse>
)