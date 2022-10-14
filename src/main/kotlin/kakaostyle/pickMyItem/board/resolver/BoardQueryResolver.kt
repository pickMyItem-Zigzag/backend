package kakaostyle.pickMyItem.board.resolver

import kakaostyle.pickMyItem.board.dto.BoardDto
import kakaostyle.pickMyItem.board.service.BoardService
import org.springframework.graphql.data.method.annotation.QueryMapping

class BoardQueryResolver(
    private val boardService: BoardService,
) {
    @QueryMapping
    fun getBoard(boardId: Long): BoardDto {
        return boardService.getBoard(boardId)
    }

    @QueryMapping
    fun getBoardList(): List<BoardDto> {
        return boardService.getBoardList()
    }
}