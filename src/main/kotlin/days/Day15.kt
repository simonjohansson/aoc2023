package days

data class Day15(val strings: List<String>) {
    companion object {
        fun from(input: String) = Day15(input.split(",").map { it.trim() }.filter { it != "" })
    }
}

fun String.HASH(): Int {
    var curr = 0
    this.toList().forEach {
        curr += it.code
        curr *= 17
        curr = curr.rem(256)
    }
    return curr
}

data class Lens(val label: String, val focalLength: Int)
sealed interface Op {
    fun box(): Int
}

data class Remove(val label: String) : Op {
    override fun box() = label.HASH()
}

data class Add(val label: String, val focalLength: Int) : Op {
    override fun box() = label.HASH()
    fun toLens() = Lens(label, focalLength)
}

fun Day15.toOps() = this.strings.map { chars ->
    if (chars.contains('-')) Remove(chars.takeWhile { it != '-' })
    else Add(chars.split("=").first(), chars.split("=").last().toInt())
}

fun Day15.part1() = this.strings.map { it.HASH() }.sum()
fun Day15.part2() = this.toOps().let { ops ->

    val state = Array<List<Lens>>(256){ listOf() }

    ops.forEach { op ->
        when (op) {
            is Add -> state[op.box()] = state[op.box()].let { b ->
                when (b.isEmpty()) {
                    true -> listOf(op.toLens())
                    false -> when (b.any { it.label == op.label }) {
                        true -> b.map { if (it.label == op.label) op.toLens() else it }
                        false -> b + listOf(op.toLens())
                    }
                }
            }

            is Remove -> state[op.box()] = state[op.box()].filter { it.label != op.label }
        }
    }

    state.mapIndexed { boxIndex, lenses ->
        lenses.mapIndexed { lensIndex, lens -> (1 + boxIndex) * (1 + lensIndex) * lens.focalLength }.sum()
    }.sum()
}