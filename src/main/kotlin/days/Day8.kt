package days

data class Day8(val instructions: List<String> = listOf(), val nodes: Map<String, Pair<String, String>> = emptyMap())

fun ParseDay8(input: String) = input.split("\n\n").let { (instructions, nodeLines) ->
    Day8(
        instructions = instructions.split("").filter { it.isNotEmpty() },
        nodes = nodeLines.split("\n").map { nodeLine ->
            val (k, v) = nodeLine.split("=").filter { it.isNotEmpty() }
            val (L, R) = v.split("(", ",", " ", ")").filter { it.isNotEmpty() }
            k.trim() to Pair(L, R)
        }.toMap()
    )
}

fun List<String>.repeat(): Iterator<String> {
    var i = 0
    return generateSequence { this[i++ % this.size] }.iterator()
}

tailrec private fun traverse(
    currentNode: Pair<String, String>,
    nodes: Map<String, Pair<String, String>>,
    instructions: Iterator<String>,
    ender: (String) -> Boolean,
    accumulator: Long = 0
): Long = (if (instructions.next() == "L") currentNode.first else currentNode.second).let { nextNode ->
    return if (ender(nextNode)) accumulator
    else traverse(nodes[nextNode]!!, nodes, instructions, ender, accumulator + 1)
}
fun Day8.part1() = traverse(
    this.nodes.get("AAA")!!, this.nodes, this.instructions.repeat(), { it: String -> it == "ZZZ" }, 1
)

fun gcd(a: Long, b: Long): Long = if (a == 0L) b else gcd(b % a, a)
fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)
fun Day8.part2() = this.nodes.filter { (k, _) -> k.endsWith("A") }
    .map { traverse(it.value, this.nodes, this.instructions.repeat(), { s: String -> s.endsWith("Z") }, 1) }
    .let { it.reduce { acc, l -> lcm(acc, l) } }
