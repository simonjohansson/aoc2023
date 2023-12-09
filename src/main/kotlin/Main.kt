import days.*

fun main(args: Array<String>) {
    val games = ParseDay9({}.javaClass.getResource("day9.txt").readText())
    println(games.part1())
}