package days

data class Card(val id: Int = 0, val winning: List<Int> = emptyList(), val numbers: List<Int> = emptyList()) {
    fun count(): Int {
        var score = 0
        for (n in numbers) {
            if (winning.contains(n)) {
                score++
            }
        }
        return score
    }

    fun score(): Int {
        if (count() == 0) {
            return 0
        }
        var score = 1
        for (i in 1..count() - 1) {
            score *= 2
        }
        return score
    }
}


fun List<Card>.part1(): Number = this.map { it.score() }.sum()

fun ParseDay4(s: String): Card {
    val id = s.split(":")[0].split("\\s+".toRegex())[1].toInt()
    val parts = s.split(":")[1].split("|")
    val w = parts.get(0).trim()
    val n = parts.get(1).trim()

    return Card(id, w.split("\\s+".toRegex()).map { it.toInt() }, n.split("\\s+".toRegex()).map { it.toInt() })
}

data class Cards(val cards: List<Card> = emptyList())
fun Cards.part2(): Int {
    val score: MutableMap<Int, MutableList<Card>> = mutableMapOf()
    this.cards.forEach { card ->
        score[card.id] = mutableListOf(card)
    }
    this.cards.forEach {card ->
        score.getOrDefault(card.id, mutableListOf()).forEach{c ->
            this.cards.subList(c.id, c.id+c.count()).forEach{cc ->
                score[cc.id]!!.add(cc)
//                score[card.id]!!.add(cc)
            }
        }
    }
    return score.map { it.value.count()}.sum()
}

fun ParseDay4Part2(s: String): Cards {
    return Cards(s.split("\n").map { ParseDay4(it) })
}