package kakaostyle.pickMyItem.wishitem.service

import kakaostyle.pickMyItem.wishitem.repository.ItemRepository
import kakaostyle.pickMyItem.wishitem.dto.ItemInfoResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {
    @Transactional(readOnly = true)
    fun getItemInfoListByIdList(idList: List<Long>): List<ItemInfoResponse> {
        return idList
            .map { itemRepository.findById(it) }
            .filter { it.isPresent }
            .map { ItemInfoResponse.from(it.get()) }
    }

    @Transactional(readOnly = true)
    fun getItemWishList(): List<ItemInfoResponse> {
        return itemRepository.findAll().map { ItemInfoResponse.from(it) }
    }
}