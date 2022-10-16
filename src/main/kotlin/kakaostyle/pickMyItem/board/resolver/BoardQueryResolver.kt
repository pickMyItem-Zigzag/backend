package kakaostyle.pickMyItem.board.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.board.dto.BoardResponse
import kakaostyle.pickMyItem.board.service.BoardService
import kakaostyle.pickMyItem.utils.BoardList
import org.springframework.stereotype.Component

@Component
class BoardQueryResolver(
    private val boardService: BoardService,
) : GraphQLQueryResolver {
    fun getBoard(boardId: Long): BoardResponse {
        return boardService.getBoard(boardId)
    }

    fun getBoardList(): BoardList {
        val boardList = boardService.getBoardList()
        return BoardList(
            boardList.size,
            boardList
        )
    }
}