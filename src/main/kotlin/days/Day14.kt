package days

data class Day14(val rows: List<String> = emptyList()) {
    companion object {
        fun from(input: String): Day14 = input.split("\n").let { rows ->
            Day14(rows)
        }
    }
}

fun <B> List<String>.rotate(apply: (List<String>) -> B) = this.first().count().let { numCols ->
    (0..<numCols).map { col ->
        (0..<this.count()).map { row ->
            this[row][col]
        }.joinToString(separator = "")
    }.let(apply)
}

fun Day14.north() = this.rows.rotate { rotated ->
    rotated.map { row ->
        "([.O]+)|(#+)".toRegex().findAll(row).map { matchResult -> matchResult.value }.toList().fold("") { acc, s ->
            if (s.all { it == '#' }) acc + s
            else acc + "O".repeat(s.count { it == 'O' }) + ".".repeat(s.count { it == '.' })
        }
    }.rotate { Day14(it) }
}

fun Day14.west() = this.rows.map { row ->
    "([.O]+)|(#+)".toRegex().findAll(row).map { matchResult -> matchResult.value }.toList().fold("") { acc, s ->
        if (s.all { it == '#' }) acc + s
        else acc + "O".repeat(s.count { it == 'O' }) + ".".repeat(s.count { it == '.' })
    }
}.let { Day14(it) }

fun Day14.south() = this.rows.rotate { rotated ->
    rotated.map { it.reversed() }.map { row ->
        "([.O]+)|(#+)".toRegex().findAll(row).map { matchResult -> matchResult.value }.toList().fold("") { acc, s ->
            if (s.all { it == '#' }) acc + s
            else acc + "O".repeat(s.count { it == 'O' }) + ".".repeat(s.count { it == '.' })

        }
    }.rotate { Day14(it.reversed()) }
}

fun Day14.east() = this.rows.map { row ->
    "([.O]+)|(#+)".toRegex().findAll(row).map { matchResult -> matchResult.value }.toList().fold("") { acc, s ->
        if (s.all { it == '#' }) acc + s
        else acc + ".".repeat(s.count { it == '.' }) + "O".repeat(s.count { it == 'O' })
    }
}.let { Day14(it) }

fun Day14.spin() = this.north().west().south().east()
fun Day14.spin(n: Long): Day14 {
    val cache = mutableListOf(this.spin())

    var loopStart = 0L
    var loopEnd = n
    var maxI = 1L

    for (i in (1..n)) {
        maxI = i
        val next = cache.last().spin()
        if (cache.indexOf(next) != -1) {
            loopStart = cache.indexOf(next).toLong()
            loopEnd = i
            break
        }
        cache.add(next)
    }

    val subList = cache.subList(loopStart.toInt(), loopEnd.toInt())

    var i = ((n - maxI) % (loopEnd-loopStart))-1

    if (i < 0) {
        // In case we have yet to enter the loop
        i = n-maxI.rem(loopEnd-loopStart)-1
    } else if (i > subList.count()) {
        i = subList.count().toLong()-1
    }

    return subList[i.toInt()]
}

fun Day14.load() = this.rows
    .zip((this.rows.count() downTo 1))
    .sumOf { (row, value) -> row.count { it == 'O' } * value }

fun Day14.part1() = this.north().load()
fun Day14.part2() = this.spin(1000000000).load()

