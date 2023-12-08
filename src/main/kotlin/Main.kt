import days.*

fun main(args: Array<String>) {
    val games = ParseDay8({}.javaClass.getResource("day8.txt").readText())
    println(games.part1())
}