package days

data class Day8(val instructions: List<String> = listOf(), val nodes: Map<String, Pair<String, String>> = emptyMap())

fun ParseDay8(input: String): Day8 {
    val (instructions, nodeLines) = input.split("\n\n")

    val nodes = nodeLines.split("\n").map { nodeLine ->
        val (k, v) = nodeLine.split("=").filter { it.isNotEmpty() }
        val (L, R) = v.split("(", ",", " ", ")").filter { it.isNotEmpty() }
        k.trim() to Pair(L, R)
    }.toMap()

    return Day8(
        instructions = instructions.split("").filter { it.isNotEmpty() },
        nodes = nodes
    )
}
fun List<String>.repeat(): Iterator<String> {
    var i = 0
    return generateSequence { this[i++ % this.size] }.iterator()
}
tailrec private fun startPart1(
    currentNode: Pair<String, String>,
    nodes: Map<String, Pair<String, String>>,
    instructions: Iterator<String>,
    accumulator: Int = 0
): Int {
    val nextNode = if (instructions.next() == "L") currentNode.first else currentNode.second
    if (nextNode == "ZZZ") {
        return accumulator
    }

    return startPart1(nodes[nextNode]!!, nodes, instructions, accumulator + 1)
}
fun Day8.part1() = startPart1(
    this.nodes.get("AAA")!!,
    this.nodes,
    this.instructions.repeat(),
    1
)