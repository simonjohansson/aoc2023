import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day14.txt").readText()
    println(Day14.from(input).part2())
}