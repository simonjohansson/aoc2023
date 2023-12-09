package days


data class Day9(val numbers: List<Int>)

fun List<Int>.pairs(): List<Pair<Int, Int>> =
    this.zip(this.drop(1))

fun List<Pair<Int, Int>>.diffs() = this.map { (a, b) -> b - a }

fun List<Int>.series(): List<List<Int>> =
    if (this.all { it == 0 })
        listOf(this)
    else
        listOf(this) + this.pairs().diffs().series()

fun Day9.nextValue() = this.numbers.series()
    .reversed()
    .map { it.last() }
    .reduce { acc, i -> acc + i }

fun Day9.previousValue() = this.numbers.series()
    .reversed()
    .map { it.first() }
    .reduce { acc, i -> i - acc }

fun List<Day9>.part1() = this.sumOf { it.nextValue() }
fun List<Day9>.part2() = this.sumOf { it.previousValue() }
fun ParseDay9(input: String) = input
    .split("\n")
    .map { it.split(" ").map { it.toInt() } }
    .map { Day9(it) }