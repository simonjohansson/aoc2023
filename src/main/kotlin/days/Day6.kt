package days

data class Day6(val duration: Long, val record: Long, val wait: Long = 0)

fun Day6.play() = this.duration * this.wait > this.record

fun Day6.games(): List<Day6> =
    (0..duration).map { this.copy(duration = this.duration - it, record = this.record, wait = it) }

fun List<Day6>.play() = this.map { it.play() }

fun List<Day6>.part1() = this.map { it.games().play().filter { it }.count() }.reduce { acc, i -> acc * i }

fun ParseDay6(input: String): List<Day6> {
    val inputs = input.split("\n").map { it.split(":").last().trim().split(" ").filter { it != "" }.map { it.toLong() } }
    return inputs.first().zip(inputs.last()).map { (dur, rec) -> Day6(dur, rec) }
}

fun ParseDay6Part2(input: String): List<Day6> {
    val combined = ParseDay6(input).map { Pair(it.duration.toString(), it.record.toString()) }.reduce { acc, pair -> Pair(acc.first+pair.first, acc.second+pair.second) }
    return listOf(Day6(combined.first.toLong(), combined.second.toLong()))
}

