package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.repository.PickJpaRepository
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.itempick.repository.UserJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PickService(
    private val pickJpaRepository: PickJpaRepository,
    private val userJpaRepository: UserJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val userPostService: UserPostService,
) {
    @Transactional
    fun pickThisItem(userId: Long, postId: Long, itemId: Long) {
        val user = userJpaRepository.findUserById(userId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 유저가 없습니다.")
        val post = postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")

        if (userPostService.existPostOfUserPicked(userId, postId)) throw RuntimeException("이미 투표한 게시글입니다.")

        pickJpaRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.pickThis(user, post) ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }

    @Transactional
    fun unPickThisItem(userId: Long, postId: Long, itemId: Long) {
        val user = userJpaRepository.findUserById(userId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 유저가 없습니다.")
        val post = postJpaRepository.findPostById(postId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 게시글이 없습니다.")

        if (!userPostService.existPostOfUserPicked(userId, postId)) throw RuntimeException("해당 게시글에 투표 내역이 존재하지 않습니다.")

        pickJpaRepository
            .findByPostIdAndItemId(postId, itemId)
            ?.unPickThis(user, post) ?: throw RuntimeException("해당하는 Pick이 없습니다.")
    }
}
