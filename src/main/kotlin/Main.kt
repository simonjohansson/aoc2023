import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day12.txt").readText()
    val parsed = ParseDay12(input)
    println(parsed.part1())
}