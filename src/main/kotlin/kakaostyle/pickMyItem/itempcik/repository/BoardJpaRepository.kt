package kakaostyle.pickMyItem.itempcik.repository

import kakaostyle.pickMyItem.itempcik.domain.Board
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardJpaRepository : JpaRepository<Board, Long> {
    fun findBoardById(boardId: Long): Board?
    fun findBoardByBoardName(boardName: String): Board?
}