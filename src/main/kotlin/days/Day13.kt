package days

import java.util.*
import kotlin.jvm.optionals.getOrElse

fun ParseDay13(input: String) = Day13(input.split("\n\n").map {
    Pattern(it.split("\n"))
})

data class Pattern(val pattern: List<String>)

fun checkPair(before: List<String>, after: List<String>) =
    (before.count() - after.count()).let { if (it > 0) it else 0 }.let { skipBefore ->
        if (before.drop(skipBefore).reversed().zip(after).all { (a, b) -> a == b })
            Optional.of(before.count() + 1)
        else
            Optional.empty()
    }

fun Pattern.horizontalResults(): Optional<Int> = this.pattern.withIndex().windowed(2).let { windowed ->
    windowed.filter { (a, b) -> a.value == b.value }
        .map { (a, b) ->
            checkPair(
                this.pattern.subList(0, a.index),
                this.pattern.subList(b.index + 1, this.pattern.count())
            )
        }.firstOrNull { it.isPresent } ?: Optional.empty()
}

fun Pattern.verticalResult() =
    (0..<this.pattern.first().count()).map { col ->
        (0..<this.pattern.count()).joinToString("") { row ->
            this.pattern[row][col].toString()
        }
    }.let {
        Pattern(it).horizontalResults()
    }


data class Day13(val patterns: List<Pattern> = emptyList())

fun Day13.part1() = this.patterns.mapIndexed { index, it ->
    val hr = it.horizontalResults().map { it * 100 }
    if (hr.isPresent) hr.get() else it.verticalResult().get()
    hr.getOrElse { it.verticalResult().getOrElse { TODO("Hmm, issue on line $index") } }
}.sum()
