package kakaostyle.pickMyItem.itempcik.service

import kakaostyle.pickMyItem.itempcik.domain.Pick
import kakaostyle.pickMyItem.itempcik.repository.PickRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickService(
    private val pickRepository: PickRepository
) {
    @Transactional
    fun createDefaultPick(
        productName: String,
        imageUrl: String,
    ): Pick {
        return Pick(
            pickCount = 0,
            productName = productName,
            imageUrl = imageUrl,
        )
    }

    @Transactional
    fun pickThisItem(pickId: Long) {
        pickRepository.findById(pickId)
            .orElseThrow { RuntimeException("해당하는 Pick이 없습니다.") }
            .pickThis()
    }

    @Transactional
    fun unPickThisItem(pickId: Long) {
        pickRepository.findById(pickId)
            .orElseThrow { RuntimeException("해당하는 Pick이 없습니다.") }
            .unPickThis()
    }
}
