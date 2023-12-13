package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.*

class Day13Test {

    @Test
    fun Parse() {
        val input = """
            #.##..##.
            ..#.##.#.

            #...##..#
            #....#..#
            
            ..#.##.#.
            ..##..##.
        """.trimIndent()

        assertEquals(
            Day13(
                listOf(
                    Pattern(listOf("#.##..##.", "..#.##.#.")),
                    Pattern(listOf("#...##..#", "#....#..#")),
                    Pattern(listOf("..#.##.#.", "..##..##."))
                )
            ),
            ParseDay13(input)
        )
    }

    @Test
    fun `horizontal result`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()

        assertEquals(Optional.empty<Int>(), ParseDay13(input).patterns.first().horizontalResults())
        assertEquals(Optional.of(4), ParseDay13(input).patterns.drop(1).first().horizontalResults())
    }

    @Test
    fun `vertical result`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()

        assertEquals(Optional.of(5), ParseDay13(input).patterns.first().verticalResult())
        assertEquals(Optional.empty<Int>(), ParseDay13(input).patterns.drop(1).first().verticalResult())
    }

    @Test
    fun `part1`() {
        val input = """
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent()

        assertEquals(405, ParseDay13(input).part1())
    }
}