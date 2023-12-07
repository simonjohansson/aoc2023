package days

data class Day1(val numbers: List<List<Int>>) {
    fun part1() = this.numbers.map {
        it.first() * 10 + it.last()
    }.sum()
}
fun ParseDay1Part2(input: String) =
    input.split("\n")

fun ParseDay1(input: String) =
    Day1(input.split("\n").map { line ->
        line
        Regex("[0-9]|one|two|three|four|five|six|seven|eight|nine").findAll(line.trim()).map {
            it.value
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")
                .toInt()
        }.toList()
    })