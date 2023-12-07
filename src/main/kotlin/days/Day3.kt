package days

data class Day3Number(val number: Int = 0, val x: IntRange = IntRange(0, 0), val y: Int = 0)

fun Day3Number.coordinates() = IntRange(this.y - 1, this.y + 1).map { newY ->
    IntRange(this.x.start - 1, this.x.endInclusive + 1).map { newX ->
        Pair(newX, newY)
    }
}.flatten()

data class Day3(
    val grid: List<List<String>> = emptyList(),
    val numbers: List<Day3Number> = emptyList()
)

fun List<Day3Number>.contains(coords: Pair<Int, Int>) =
    this.filter { it.y == coords.second }.filter { it.x.contains(coords.first) }


fun List<List<String>>.at(x: Int, y: Int): String {
    return this.getOrElse(y) {
        listOf()
    }.getOrElse(x) { "." }
}

fun String.isSymbol() = !"\\d+|\\.".toRegex().matches(this)

fun ParseDay3(input: String) =
    Day3(
        grid = input.trim().split("\n").map { it.trim() }
            .map { it -> it.split("").filter { c -> c != "" } },

        numbers = input.trim().split("\n").mapIndexed { index, s ->
            Regex("[0-9]+").findAll(s.trim())
                .map {
                    Day3Number(it.value.toInt(), it.range, index)
                }.toList()
        }.flatten()
    )

fun Day3.part1() = this.numbers.filter {
    it.coordinates().map { (x, y) ->
        grid.at(x, y).isSymbol()
    }.any { it }
}.map { it.number }
    .sum()

fun Day3.part2() = this.grid.mapIndexed { y, row ->
    row.mapIndexed { x, s ->
        Triple(x, y, s)
    }
}.flatten()
    .filter { it.third == "*" }
    .map {trip  ->
        IntRange(trip.first-1,trip.first+1).map {x ->
            IntRange(trip.second-1, trip.second+1).map { y ->
                Pair(x, y)
            }
        }.flatten()
    }.map {
        it.map { this.numbers.contains(it) }.flatten().toSet()
    }.filter { it.count() == 2 }
    .map { it.map { it.number }.reduce { acc, i -> acc * i } }
    .sum()