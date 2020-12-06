package biscuits

fun findBiscuit(biscuitId: String?): BiscuitOrNone {
    return when(biscuitId) {
        "1" -> Biscuit("Club")
        "2" -> Biscuit("Trio")
        else -> BiscuitNotFound
    }
}

sealed class BiscuitOrNone
data class Biscuit(val name: String) : BiscuitOrNone()
object BiscuitNotFound : BiscuitOrNone()