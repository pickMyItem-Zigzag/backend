package kakaostyle.pickMyItem.utils

fun Boolean?.isNullOrFalse(): Boolean {
    return this == null || this == false
}

fun Boolean?.isNotNullAndTrue(): Boolean {
    return this != null && this == true
}
