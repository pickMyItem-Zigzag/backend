package kakaostyle.pickMyItem.board.resolver

import kakaostyle.pickMyItem.board.dto.SaveBoardInput
import kakaostyle.pickMyItem.board.service.BoardService
import org.springframework.graphql.data.method.annotation.MutationMapping

class BoardMutationResolver(
    private val boardService: BoardService
) {
    @MutationMapping
    fun saveBoard(input: SaveBoardInput) {
        boardService.saveBoard(input)
    }
}