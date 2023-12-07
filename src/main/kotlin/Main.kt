import days.*

fun main(args: Array<String>) {
    val games = ParseDay7({}.javaClass.getResource("day7.txt").readText())
    println(games.part2())
}