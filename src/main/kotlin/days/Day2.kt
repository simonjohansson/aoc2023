package days

data class Cubes(val red: Int = 0, val green: Int = 0, val blue: Int = 0)

fun Cubes.valid() = this.red <= 12 && this.green <= 13 && this.blue <= 14

data class Game(val id: Int = 0, val cubes: List<Cubes> = emptyList())

fun Game.valid(): Boolean = this.cubes.all { it.valid() }
fun Game.min() = Cubes(
    red = this.cubes.map { it.red }.max(),
    green = this.cubes.map { it.green }.max(),
    blue = this.cubes.map { it.blue }.max()
)

fun List<Game>.part1() = filter { it.valid() }.map { it.id }.sum()
fun List<Game>.part2() = map { it.min() }.map { it.red * it.blue * it.green }.sum()

fun ParseDay2(input: String): Game {
    val id = input.split(":").first().split(" ").last().toInt()
    val cubes = input.split(":").last().split(";").map {
        var cubes = Cubes()
        for (c in it.split(",")) {
            val data = c.trim()
            if ("red" in data) {
                cubes = cubes.copy(red = data.split(" ").first().toInt())
            } else if ("green" in c) {
                cubes = cubes.copy(green = data.split(" ").first().toInt())
            } else if ("blue" in c) {
                cubes = cubes.copy(blue = data.split(" ").first().toInt())
            }
        }
        cubes
    }
    return Game(id, cubes)
}


