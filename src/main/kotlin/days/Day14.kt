package days

data class Day14(val rows: List<String> = emptyList()) {
    companion object {
        fun from(input: String): Day14 = input.split("\n").let { rows ->
            Day14(rows)
        }
    }
}

fun List<String>.rotate() = this.first().count().let { numCols ->
    (0..<numCols).map { col ->
        (0..<this.count()).map { row ->
            this[row][col]
        }.joinToString(separator = "")

    }
}

fun Day14.north() = this.rows.rotate().let { rotated ->
    rotated.map { row ->
        "([.O]+)|(#+)".toRegex().findAll(row).map { matchResult -> matchResult.value }.toList().fold("") { acc, s ->
            if (s.all { it == '#' }) acc + s
            else acc + "O".repeat(s.count { it == 'O' }) + ".".repeat(s.count { it == '.' })
        }
    }.rotate().let { Day14(it) }
}

fun Day14.part1() = this.north()
    .rows
    .zip((this.rows.count() downTo 1))
    .sumOf { (row, value) -> row.count { it == 'O' } * value }
