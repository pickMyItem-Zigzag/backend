package kakaostyle.pickMyItem.itempcik.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.board.dto.BoardList
import kakaostyle.pickMyItem.board.dto.BoardResponse
import kakaostyle.pickMyItem.itempcik.service.BoardService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

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