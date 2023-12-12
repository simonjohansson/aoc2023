package days

data class Row(val springs: String = "", val size: List<Int> = emptyList()) {
    tailrec private fun arrs(curr: String, accum: List<String>): List<String> {
        if (curr.isEmpty()) {
            return accum
        }

        return arrs(curr.drop(1), curr.first().let { currChar ->
            when (currChar) {
                '?' -> accum.map { "$it#" } + accum.map { "$it." }
                else -> accum.map { "$it$currChar" }

            }
        })
    }


    fun arrangements(): List<Row> {
        return arrs(this.springs, listOf(""))
            .map { Row(it, size) }
    }

    fun valid(): Boolean {
        val matches = "(#+)".toRegex()
            .findAll(this.springs)
            .map { it.value.count().toInt() }
            .toList()

        return matches == this.size
    }
}

data class Day12(val rows: List<Row> = emptyList())

fun ParseDay12(input: String) = input.split("\n")
    .map { row ->
        val (springs, size) = row.split(" ")
        Row(
            springs = springs.trim(),
            size = size.split(",").map { it.toInt() }
        )
    }.let {
        Day12(it)
    }

fun Day12.part1() = this.rows.sumOf { row ->
    row.arrangements().count { it.valid() }
}