package kakaostyle.pickMyItem.base

import java.time.LocalDateTime
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Base {
    var createdBy: String? = null
    var updatedBy: String? = null
    var created: LocalDateTime? = null
    var updated: LocalDateTime? = null
}