package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.repository.PickJpaRepository
import kakaostyle.pickMyItem.itempick.repository.UserJpaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickService(
    private val pickJpaRepository: PickJpaRepository,
    private val userJpaRepository: UserJpaRepository,
) {
    @Transactional
    fun pickThisItem(userId: Long, postId: Long, itemId: Long) {
        val user = userJpaRepository.findUserById(userId) ?: throw RuntimeException("해당하는 유저가 없습니다.")
        pickJpaRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.pickThis(user) ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }

    @Transactional
    fun unPickThisItem(userId: Long, postId: Long, itemId: Long) {
        val user = userJpaRepository.findUserById(userId) ?: throw RuntimeException("해당하는 유저가 없습니다.")
        pickJpaRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.unPickThis(user) ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }
}
