import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day13.txt").readText()
    val parsed = ParseDay13(input)
    println(parsed.part1())
}