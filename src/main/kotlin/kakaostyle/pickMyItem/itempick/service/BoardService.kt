package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.domain.Board
import kakaostyle.pickMyItem.itempick.dto.BoardResponse
import kakaostyle.pickMyItem.itempick.dto.CreateBoardInput
import kakaostyle.pickMyItem.itempick.repository.BoardJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardJpaRepository: BoardJpaRepository,
) {
    private val logger = LoggerFactory.getLogger(this::class.simpleName)

    @Transactional(readOnly = true)
    fun getBoardById(boardId: Long): Board {
        return boardJpaRepository.findBoardById(boardId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시판이 없습니다.")
    }

    @Transactional(readOnly = true)
    fun findBoardResponseById(boardId: Long): BoardResponse {
        val board = boardJpaRepository
            .findBoardById(boardId) ?: throw RuntimeException("해당하는 게시판이 없습니다.")
        return BoardResponse.from(board)
    }

    @Transactional(readOnly = true)
    fun findBoardResponseList(): List<BoardResponse> {
        return boardJpaRepository
            .findAll()
            .filter { it.deleted.isNullOrFalse() }
            .map { BoardResponse.from(it) }
    }

    @Transactional
    fun saveBoard(input: CreateBoardInput) {
        if (boardJpaRepository.findBoardByBoardName(input.boardName) == null) {
            boardJpaRepository.save(Board(boardName = input.boardName, postList = mutableListOf()))
        } else throw RuntimeException("동일한 이름의 게시판이 존재합니다.")
        logger.info("${input.boardName} 게시판이 생성되었습니다.")
    }
}