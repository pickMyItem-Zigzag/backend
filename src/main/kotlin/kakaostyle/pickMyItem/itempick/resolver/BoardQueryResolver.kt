package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLQueryResolver
import kakaostyle.pickMyItem.itempick.dto.BoardList
import kakaostyle.pickMyItem.itempick.dto.BoardResponse
import kakaostyle.pickMyItem.itempick.service.BoardService
import org.springframework.stereotype.Component

@Component
class BoardQueryResolver(
    private val boardService: BoardService,
) : GraphQLQueryResolver {
    fun getBoard(boardId: Long): BoardResponse {
        return boardService.findBoardResponseBy(boardId)
    }

    fun getBoardList(): BoardList {
        val boardList = boardService.findBoardResponseList()
        return BoardList(
            boardList.size,
            boardList
        )
    }
}