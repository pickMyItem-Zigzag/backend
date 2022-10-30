package kakaostyle.pickMyItem.utils

fun Boolean?.isNullOrFalse(): Boolean {
    return this == null || this == false
}

fun Boolean?.isNotNullAndTrue(): Boolean {
    return this != null && this == true
}

enum class OrderType {
    DEFAULT, MOST_PICK, MIN_PICK
}