package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.repository.PickRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickService(
    private val pickRepository: PickRepository
) {
    @Transactional
    fun pickThisItem(postId: Long, itemId: Long) {
        pickRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.pickThis() ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }

    @Transactional
    fun unPickThisItem(postId: Long, itemId: Long) {
        pickRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.unPickThis() ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }
}
