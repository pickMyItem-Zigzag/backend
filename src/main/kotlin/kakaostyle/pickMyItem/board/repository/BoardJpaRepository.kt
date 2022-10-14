package kakaostyle.pickMyItem.board.repository

import kakaostyle.pickMyItem.board.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardJpaRepository : JpaRepository<Board, Long> {
    fun findBoardById(boardId: Long): Board?
}