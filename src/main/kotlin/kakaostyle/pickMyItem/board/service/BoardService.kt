package kakaostyle.pickMyItem.board.service

import kakaostyle.pickMyItem.board.domain.Board
import kakaostyle.pickMyItem.board.dto.BoardDto
import kakaostyle.pickMyItem.board.dto.SaveBoardInput
import kakaostyle.pickMyItem.board.repository.BoardJpaRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardJpaRepository: BoardJpaRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @Transactional(readOnly = true)
    fun getBoard(boardId: Long): BoardDto {
        val board = boardJpaRepository.findBoardById(boardId)
        if (board == null) {
            logger.info("해당하는 Borad가 없습니다.")
            throw RuntimeException("해당하는 Borad가 없습니다.")
        }
        return BoardDto.from(board)
    }

    @Transactional(readOnly = true)
    fun getBoardList(): List<BoardDto> {
        return boardJpaRepository
            .findAll()
            .filter { it.deleted != null }
            .map { BoardDto.from(it) }
    }

    @Transactional
    fun saveBoard(input: SaveBoardInput) {
        boardJpaRepository.save(
            Board(boardName = input.boardName)
        )
    }
}