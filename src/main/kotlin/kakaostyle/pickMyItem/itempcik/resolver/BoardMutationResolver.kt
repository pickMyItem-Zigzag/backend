package kakaostyle.pickMyItem.itempcik.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.board.dto.CreateBoardInput
import kakaostyle.pickMyItem.itempcik.service.BoardService
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

@Component
class BoardMutationResolver(
    private val boardService: BoardService
) : GraphQLMutationResolver {
    fun createBoard(input: CreateBoardInput): Boolean {
        boardService.saveBoard(input)
        return true
    }
}