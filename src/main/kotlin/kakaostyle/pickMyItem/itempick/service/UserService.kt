package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.dto.PickResponse
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.dto.UserResponse
import kakaostyle.pickMyItem.itempick.repository.PickJpaRepository
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.itempick.repository.UserJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userJpaRepository: UserJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val pickJpaRepository: PickJpaRepository,
) {
    @Transactional(readOnly = true)
    fun getAllUserList(): List<UserResponse> {
        return userJpaRepository.findAll()
            .filter { it.deleted.isNullOrFalse() }
            .map { UserResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getUser(userId: Long): UserResponse {
        return UserResponse.from(
            userJpaRepository.findUserById(userId).takeIf { it?.deleted.isNullOrFalse() }
                ?: throw RuntimeException("해당하는 유저가 없습니다.")
        )
    }

    @Transactional(readOnly = true)
    fun getMyPostList(userId: Long): List<PostResponse> {
        return postJpaRepository.findAllByPostingUserId(userId)
            .filter { it.deleted.isNullOrFalse() }
            .map { PostResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun getMyPickList(userId: Long): List<PickResponse> {
        return pickJpaRepository.findAllByUserId(userId)
            .filter { it.deleted.isNullOrFalse() }
            .map { PickResponse.from(it) }
    }
}