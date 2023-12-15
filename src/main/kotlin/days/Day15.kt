package days

data class Day15(val strings: List<String>) {
    companion object {
        fun from(input: String) = Day15(input.split(",").map { it.trim() }.filter { it != "" })
    }
}

fun String.HASH(): Int {
    var curr = 0
    this.toList().forEach {
        curr += it.code
        curr *= 17
        curr = curr.rem(256)
    }
    return curr
}

fun Day15.part1() = this.strings.map { it.HASH() }.sum()
