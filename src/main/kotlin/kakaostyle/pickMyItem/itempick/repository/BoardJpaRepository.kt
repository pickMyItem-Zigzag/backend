package kakaostyle.pickMyItem.itempick.repository

import kakaostyle.pickMyItem.itempick.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardJpaRepository : JpaRepository<Board, Long> {
    fun findBoardById(boardId: Long): Board?
    fun findBoardByBoardName(boardName: String): Board?
}