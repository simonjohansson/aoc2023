import days.*

fun main(args: Array<String>) {
    val games = ParseDay10({}.javaClass.getResource("day10.txt").readText(), "|")
    println(games.part1())
}