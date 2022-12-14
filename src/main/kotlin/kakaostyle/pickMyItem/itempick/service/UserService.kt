package kakaostyle.pickMyItem.itempick.service

import kakaostyle.pickMyItem.itempick.domain.User
import kakaostyle.pickMyItem.itempick.dto.PostResponse
import kakaostyle.pickMyItem.itempick.dto.UserResponse
import kakaostyle.pickMyItem.itempick.repository.PostJpaRepository
import kakaostyle.pickMyItem.itempick.repository.UserJpaRepository
import kakaostyle.pickMyItem.utils.isNullOrFalse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userJpaRepository: UserJpaRepository,
    private val postJpaRepository: PostJpaRepository,
    private val userPostService: UserPostService,
) {
    @Transactional(readOnly = true)
    fun getUserBy(userId: Long): User {
        return userJpaRepository.findUserById(userId).takeIf { it?.deleted.isNullOrFalse() }
            ?: throw RuntimeException("해당하는 유저가 없습니다.")
    }

    @Transactional(readOnly = true)
    fun findUserResponseList(): List<UserResponse> {
        return userJpaRepository.findAll().filter { it.deleted.isNullOrFalse() }
            .map { UserResponse.from(it) }
    }

    @Transactional(readOnly = true)
    fun findUserResponseBy(userId: Long): UserResponse {
        return UserResponse.from(getUserBy(userId))
    }

    @Transactional(readOnly = true)
    fun findPostResponseListBy(userId: Long): List<PostResponse> {
        return postJpaRepository.findAllByPostingUserId(userId)
            .filter { it.deleted.isNullOrFalse() }
            .map {
                val userPost = userPostService.findUserPost(userId, it.id)
                PostResponse.from(
                    post = it,
                    pickedItemId = userPost?.itemId
                )
            }
    }

    @Transactional(readOnly = true)
    fun findMyPickedPostResponseBy(userId: Long): List<PostResponse> {
        return userPostService.findAllPickedPostOfUser(userId)
            .map {
                val userPost = userPostService.findUserPost(userId, it.postId)
                PostResponse.from(
                    post = postJpaRepository.findPostById(it.postId)
                        .takeIf { post -> post?.deleted.isNullOrFalse() }
                        ?: throw RuntimeException("해당하는 게시글이 없습니다."),
                    pickedItemId = userPost?.itemId
                )
            }
    }
}