package kakaostyle.pickMyItem.base

import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class Base {
    var deleted: Boolean? = null
}