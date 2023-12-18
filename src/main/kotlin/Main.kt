import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day18.txt").readText()
    println(Day18.from(input).part1())
}