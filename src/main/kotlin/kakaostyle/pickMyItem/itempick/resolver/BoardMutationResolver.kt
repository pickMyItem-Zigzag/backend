package kakaostyle.pickMyItem.itempick.resolver

import graphql.kickstart.tools.GraphQLMutationResolver
import kakaostyle.pickMyItem.itempick.dto.CreateBoardInput
import kakaostyle.pickMyItem.itempick.service.BoardService
import org.springframework.stereotype.Component

@Component
class BoardMutationResolver(
    private val boardService: BoardService
) : GraphQLMutationResolver {
    fun createBoard(input: CreateBoardInput): Boolean {
        boardService.saveBoard(input)
        return true
    }
}