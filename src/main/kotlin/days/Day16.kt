package days

import days.Direction.*

enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

data class Day16(
    val grid: List<String> = emptyList(),
) {
    companion object {
        fun from(input: String): Day16 = Day16(grid = input.split("\n"))
    }
}

private fun outsideOfGrid(grid: List<String>, currentPos: Point) = (currentPos.x > grid.first()
    .count() - 1 || currentPos.x < 0) || (currentPos.y > grid.count() - 1 || currentPos.y < 0)

fun next(grid: List<String>, curr: Pair<Direction, Point>): List<Pair<Direction, Point>> {
    val (currDir, currPos) = curr
    val currChar = grid[currPos.y][currPos.x]

    return when (currChar) {
        '.' -> when (currDir) {
            NORTH -> listOf(NORTH to currPos.copy(y = currPos.y - 1))
            SOUTH -> listOf(SOUTH to currPos.copy(y = currPos.y + 1))
            EAST -> listOf(EAST to currPos.copy(x = currPos.x + 1))
            WEST -> listOf(WEST to currPos.copy(x = currPos.x - 1))
        }

        '|' -> when (currDir) {
            NORTH -> listOf(NORTH to currPos.copy(y = currPos.y - 1))
            SOUTH -> listOf(SOUTH to currPos.copy(y = currPos.y + 1))
            EAST, WEST -> listOf(
                NORTH to currPos.copy(y = currPos.y - 1), SOUTH to currPos.copy(y = currPos.y + 1)
            )
        }

        '-' -> when (currDir) {
            EAST -> listOf(EAST to currPos.copy(x = currPos.x + 1))
            WEST -> listOf(WEST to currPos.copy(x = currPos.x - 1))
            NORTH, SOUTH -> listOf(
                EAST to currPos.copy(x = currPos.x + 1),
                WEST to currPos.copy(x = currPos.x - 1),
            )
        }

        '/' -> when (currDir) {
            NORTH -> listOf(EAST to currPos.copy(x = currPos.x + 1))
            SOUTH -> listOf(WEST to currPos.copy(x = currPos.x - 1))
            EAST -> listOf(NORTH to currPos.copy(y = currPos.y - 1))
            WEST -> listOf(SOUTH to currPos.copy(y = currPos.y + 1))
        }

        '\\' -> when (currDir) {
            NORTH -> listOf(WEST to currPos.copy(x = currPos.x - 1))
            SOUTH -> listOf(EAST to currPos.copy(x = currPos.x + 1))
            EAST -> listOf(SOUTH to currPos.copy(y = currPos.y + 1))
            WEST -> listOf(NORTH to currPos.copy(y = currPos.y - 1))
        }

        else -> TODO()
    }.filter { !outsideOfGrid(grid, it.second) }
}

fun Day16.beam(): MutableSet<Pair<Direction, Point>> = run {
    val start = EAST to Point(0, 0)

    val seen = mutableSetOf<Pair<Direction, Point>>(start)
    val queue = ArrayDeque<Pair<Direction, Point>>()

    queue.add(start)
    while (!queue.isEmpty()) {
        val nextPos = queue.removeFirst()
        for (n in next(this.grid, nextPos)) {
            if (n in seen) {
                continue
            }
            queue.add(n)
            seen.add(n)
        }
    }
    return seen
}

fun Day16.part1() = this.beam().map { it.second }.toSet().count()