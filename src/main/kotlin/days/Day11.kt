package days

import kotlin.math.abs


data class Day11(val grid: List<List<Pair<String, Pair<Long, Long>>>> = listOf())

fun Day11.expand(expansion: Int = 2): List<Pair<Long, Long>> {
    val emptyRows = (0..<this.grid.count()).filter { row ->
        this.grid[row].all { (c, _) -> c == "." }
    }

    val emptyCols = (0..<this.grid.first().count()).filter { col ->
        (0..<this.grid.count()).all { row ->
            this.grid[row][col].first == "."
        }
    }

    return findGalaxies().map { galaxy ->
        val newX = galaxy.first + emptyCols.count { it < galaxy.first } * (expansion-1)
        val newY = galaxy.second + emptyRows.count { it < galaxy.second } * (expansion-1)
        newX to newY
    }
}

private fun Day11.findGalaxies() = this.grid.map {
    it.filter { (c, _) -> c == "#" }
        .map { (_, p) -> p }
}.flatten()

fun List<Pair<Long, Long>>.pairs() = this.mapIndexed { index, current ->
    this.drop(index + 1).map { Pair(current, it) }
}.flatten()

fun Pair<Pair<Long, Long>, Pair<Long, Long>>.distance() =
    this.let { (start, end) ->
        val (x1, y1) = start
        val (x2, y2) = end
        abs(y1 - y2) + abs(x1 - x2)
    }

fun Day11.go(expansion: Int) = this.expand(expansion).pairs()
    .map {
        it.distance()
    }.sum()

fun ParseDay11(input: String) = input.split("\n")
    .mapIndexed { iRow, row ->
        row.split("").filter { it.isNotEmpty() }.mapIndexed { iCol, col ->
            col to Pair(iCol.toLong(), iRow.toLong())
        }
    }.let {
        Day11(it)
    }


