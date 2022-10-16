package kakaostyle.pickMyItem.board.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.board.dto.CreateBoardInput
import kakaostyle.pickMyItem.board.service.BoardService
import org.springframework.stereotype.Controller

@Controller
class BoardMutationResolver(
    private val boardService: BoardService
) : GraphQLMutationResolver {
    fun createBoard(input: CreateBoardInput): Boolean {
        boardService.saveBoard(input)
        return true
    }
}