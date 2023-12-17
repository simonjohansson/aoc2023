import days.*

fun main(args: Array<String>) {
    val input = {}.javaClass.getResource("day16.txt").readText()
    println(Day16.from(input).part2())
}