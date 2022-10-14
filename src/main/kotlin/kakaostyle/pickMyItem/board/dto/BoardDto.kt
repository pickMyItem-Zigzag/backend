package kakaostyle.pickMyItem.board.dto

import kakaostyle.pickMyItem.board.domain.Board
import kakaostyle.pickMyItem.post.dto.PostDto

data class BoardDto(
    val boardName: String,
    val postList: List<PostDto>
) {
    companion object {
        fun from(
            board: Board
        ): BoardDto {
            return BoardDto(
                boardName = board.boardName,
                postList = board.postList.map { PostDto.getPostDto(it) }
            )
        }
    }
}

data class SaveBoardInput(
    val boardName: String
)