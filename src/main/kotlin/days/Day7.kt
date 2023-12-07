package days

enum class Type(val value: Int) {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1),
}

data class Hand(val cards: String = "", val bid: Int = 0) : Comparable<Hand> {
    override fun compareTo(other: Hand): Int {
        this.cardScores().zip(other.cardScores()).forEach { (l, r) ->
            if (l > r) {
                return 1
            } else if (r > l) {
                return -1
            }
        }
        return 0
    }

    fun compareToPart2(other: Hand): Int {
        this.cardScores(part2 = true).zip(other.cardScores(part2 = true)).forEach { (l, r) ->
            if (l > r) {
                return 1
            } else if (r > l) {
                return -1
            }
        }
        return 0
    }
}

private fun Hand.getType(): Type {
    val freq = this.cards.groupingBy { it }.eachCount()
    if (freq.values.size == 1) {
        return Type.FIVE_OF_A_KIND
    }
    if (freq.values.size == 2) {
        if (freq.values.contains(4)) {
            return Type.FOUR_OF_A_KIND
        }
        return Type.FULL_HOUSE
    }
    if (freq.values.contains(3)) {
        return Type.THREE_OF_A_KIND
    }
    return when(freq.values.filter { it == 2 }.count()) {
        2 -> Type.TWO_PAIR
        1 -> Type.ONE_PAIR
        else -> Type.HIGH_CARD
    }
}

private fun permutations(hand: List<Hand>): List<Hand> =
    if (hand.filter { it.cards.contains("J") }.isEmpty())
        hand
    else
        hand.map { h ->
            listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2").map {
                h.copy(cards = h.cards.replaceFirst("J", it)).permutations()
            }.flatten()
        }.flatten()


fun Hand.permutations() = days.permutations(listOf(this))

fun Hand.type(part2: Boolean = false): Type {
    if (part2 && this.cards.contains("J")) {
        return this.permutations().map { it.getType() }.sorted().first()
    }

    return this.getType()
}


fun Hand.cardScores(part2: Boolean = false) = this.cards.split("").filter { it.isNotBlank() }.map {
    when (it) {
        "A" -> 13
        "K" -> 12
        "Q" -> 11
        "J" -> if (!part2) 10 else 0
        "T" -> 9
        "9" -> 8
        "8" -> 7
        "7" -> 6
        "6" -> 5
        "5" -> 4
        "4" -> 3
        "3" -> 2
        "2" -> 1
        else -> -1
    }
}

fun List<Hand>.sort(part2: Boolean = false) =
    if (part2) this.sortedWith(Hand::compareToPart2)
    else this.sortedWith(Hand::compareTo)

fun List<Hand>.sortByTypeAndScore(part2: Boolean = false) = this.groupBy { it.type(part2) }
    .map { (k, v) -> Pair(k, v) }
    .sortedBy { it.first }.reversed()
    .map { (_, list) -> list.sort(part2) }
    .flatten()

fun ParseDay7(input: String) =
    input.split("\n").map {
        val (cards, bid) = it.split(" ")
        Hand(cards.trim(), bid.toInt())
    }

fun List<Hand>.part1() = this.gogo()
fun List<Hand>.part2() = this.gogo(true)

fun List<Hand>.gogo(part2: Boolean = false) = this.sortByTypeAndScore(part2)
    .mapIndexed { index, hand -> hand.bid * (index + 1) }
    .sum()