package days

import kotlin.math.abs

data class Day18(val ops: List<String>) {
    companion object {
        fun from(input: String): Day18 = Day18(input.split("\n"))
    }
}

fun Day18.toPoints(): List<Point> {
    var points = mutableListOf(Point(0, 0))
    this.ops.forEach {
        val (dir, steps) = it.split(" ")
        val r = (0..<steps.toInt())
        when (dir) {
            "R" -> for (n in r) {
                points.add(points.last().copy(x = points.last().x + 1))
            }

            "D" -> for (n in r) {
                points.add(points.last().copy(y = points.last().y + 1))
            }

            "L" -> for (n in r) {
                points.add(points.last().copy(x = points.last().x - 1))
            }

            "U" -> for (n in r) {
                points.add(points.last().copy(y = points.last().y - 1))
            }

            else -> TODO()
        }
    }
    val minX = points.minOf { it.x }
    val minY = points.minOf { it.y }
    return points.map { it.copy(x = it.x + abs(minX), y = it.y + abs(minY)) }
}

fun Day18.size() = this.toPoints().let { points ->
    points.maxOf { it.x + 1 } to points.maxOf { it.y + 1 }
}

fun Day18.charGrid() = this.toPoints().let { points ->
    val (maxX, maxY) = this.size()

    val grid = Array<Array<Char>>(maxY) {
        Array<Char>(maxX) {
            '.'
        }
    }

    points.forEach {
        grid[it.y][it.x] = '#'
    }

    grid.map { it.joinToString(separator = "") }.joinToString("\n")
}

fun Day18.findInside() = this.charGrid().let { grid ->
    val (y, firstRow) = grid
        .split("\n")
        .mapIndexed { index, row -> index to row }
        .filter { (i, r) -> r.count { it == '#' } == 2 }
        .first()

    val x = firstRow.indexOf('#') + 1
    x to y
}

fun Day18.fill() = this.charGrid().let {g ->
    val (maxX, maxY) =  this.size()
    val (startX, startY) = this.findInside()

    val seen = mutableSetOf<Point>()
    val queue = ArrayDeque(listOf(Point(startX, startY)))

    val grid: List<CharArray> = g.split("\n").map { it.toCharArray() }

    while (queue.isNotEmpty()) {
        val next = queue.removeFirst()

        if (next in seen
            || grid[next.y][next.x] == '#'
            || next.y < 0
            || next.y > maxY
            || next.x < 0
            || next.x > maxX
            ) {
            continue
        }

        seen.add(next)

        grid[next.y][next.x] = '#'

        queue.add(Point(next.x, next.y-1))
        queue.add(Point(next.x, next.y+1))
        queue.add(Point(next.x-1, next.y))
        queue.add(Point(next.x+1, next.y))
    }

    grid.map { it.joinToString(separator = "") }.joinToString(separator = "\n")
}

fun Day18.part1() = this.fill().count { it == '#' }