import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day15.txt").readText()
    println(Day15.from(input).part1())
}