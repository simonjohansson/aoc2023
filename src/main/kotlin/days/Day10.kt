package days

data class Point(val x: Int = 0, val y: Int = 0)
data class Day10(
    val start: Point = Point(0, 0),
    val grid: List<List<String>> = emptyList()
)

//fun Day10.valid() =
//    nextMoves(this.grid, this.start).let {
//        it.size == 2
//    }

fun Day10.allStartPermutations() =
    listOf("|", "-", "L", "J", "7", "F")
        .map { new ->
            Day10(
                start = this.start,
                grid = grid.map { row ->
                    row.map { col ->
                        if (col == "S") new else col
                    }
                }
            )
        }

private fun possibleNextMoves(currentPos: Point, currentPipe: String): List<Point> =
    when (currentPipe) {
        "|" -> listOf(
            Point(currentPos.x, currentPos.y - 1),
            Point(currentPos.x, currentPos.y + 1),
        )

        "-" -> listOf(
            Point(currentPos.x - 1, currentPos.y),
            Point(currentPos.x + 1, currentPos.y),
        )

        "L" -> listOf(
            Point(currentPos.x, currentPos.y - 1),
            Point(currentPos.x + 1, currentPos.y),
        )

        "J" -> listOf(
            Point(currentPos.x - 1, currentPos.y),
            Point(currentPos.x, currentPos.y - 1),
        )

        "7" -> listOf(
            Point(currentPos.x - 1, currentPos.y),
            Point(currentPos.x, currentPos.y + 1),
        )

        "F" -> listOf(
            Point(currentPos.x, currentPos.y + 1),
            Point(currentPos.x + 1, currentPos.y),
        )

        else -> TODO("Doesnt make sense..")
    }

fun nextMoves(
    grid: List<List<String>>,
    currentPos: Point,
    previousPos: Point
): List<Point> =
    grid.let { rows ->
        val maxY = rows.count() - 1
        val maxX = rows.first().count() - 1
        possibleNextMoves(currentPos, grid[currentPos.y][currentPos.x])
            .filter { (newX, newY) ->
                newX >= 0 && newY >= 0 && newX <= maxX && newY <= maxY
            }.filter { it != previousPos }
    }

fun nextMove(
    grid: List<List<String>>,
    currentPos: Point,
    previousPos: Point
) = nextMoves(grid, currentPos, previousPos).first()

@Suppress("NAME_SHADOWING")
private fun traverseIter(grid: List<List<String>>, start: Point, next: Point): List<Point> {
    val visited = mutableListOf(start)
    var next = next
    while (next != start) {
        val previous = visited.last()
        visited.add(next)
        next = nextMove(grid, next, previous)
    }
    return visited.toList()
}


fun Day10.part1() = nextMoves(this.grid, this.start, Point(Int.MIN_VALUE, Int.MIN_VALUE))
    .map { traverseIter(this.grid, this.start, it) }
    .map { it.count() / 2 }
    .first()


fun ParseDay10(input: String, startChar: String) = input.split("\n").map {
    it.split("").filter { it.isNotEmpty() }
}.let { grid ->
    Day10(
        start = grid.mapIndexed { y, row ->
            if (row.contains("S"))
                row.mapIndexed { x, col ->
                    if (col == "S") Point(x, y) else Point(Int.MIN_VALUE, Int.MIN_VALUE)
                }.filter { it.x != Int.MIN_VALUE }
            else listOf()
        }.flatten().first(),
        grid = grid.map { row ->
            row.map {
                if (it == "S") startChar else it
            }
        }
    )
}
