package kakaostyle.pickMyItem.board.dto

import kakaostyle.pickMyItem.board.domain.Board

data class BoardResponse(
    val id: Long,
    val boardName: String,
    val postIdList: List<Long>
) {
    companion object {
        fun from(
            board: Board
        ): BoardResponse {
            val postIdList = board.postList.map { it.id }
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