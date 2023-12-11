import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day11.txt").readText()
    val parsed = ParseDay11(input)
    println(parsed.part1())
}