package days

import kotlin.math.abs

data class Day11(val grid: List<List<String>> = listOf())

private fun expandRows(grid: List<List<String>>) = grid.map { row ->
    if (row.all { it == "." }) listOf(row, row) else listOf(row)
}.flatten()

private fun expandCols(grid: List<List<String>>) =
    grid.count().let { numRows ->
        grid.first().count().let { numCols ->
            (0..<numCols).mapIndexed { i, currCol ->
                Pair(i, (0..<numRows).map { currRow -> grid[currRow][currCol] }.all { it == "." })
            }
        }
    }.filter { (_, allEmpty) -> allEmpty }.map { (i, _) -> i }.let { colsToBeExpanded ->
        grid.map { row ->
            row.mapIndexed { index, col -> if (colsToBeExpanded.contains(index)) listOf(col, col) else listOf(col) }
                .flatten()
        }
    }

fun Day11.expand() = expandRows(this.grid).let { rowsExpanded ->
    Day11(grid = expandCols(rowsExpanded))
}

fun Day11.findGalaxies() = this.grid.mapIndexed { rowIndex, row ->
    row.mapIndexed { colIndex, col -> if (col == "#") listOf(Point(colIndex, rowIndex)) else listOf() }.flatten()
}.flatten()


fun List<Point>.pairs() = this.mapIndexed { index, current ->
    this.drop(index + 1).map { Pair(current, it) }
}.flatten()

fun Pair<Point, Point>.distance() =
    this.let { (start, end) ->
        val (x1, y1) = start
        val (x2, y2) = end
        abs(y1 - y2) + abs(x1 - x2)
    }

fun Day11.part1() = this.expand().findGalaxies().pairs()
    .map {
        it.distance()
    }.let {
        it.sum()
    }

fun ParseDay11(input: String) = Day11(grid = input.split("\n").map { row ->
    row.toCharArray().map { it.toString() }
})


