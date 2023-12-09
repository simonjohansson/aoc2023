package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun Parse() {
        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()

        assertEquals(
            listOf(
                Day9(listOf(0, 3, 6, 9, 12, 15)),
                Day9(listOf(1, 3, 6, 10, 15, 21)),
                Day9(listOf(10, 13, 16, 21, 30, 45)),
            ),
            ParseDay9(input)
        )
    }

    @Test
    fun Pairs() {
        assertEquals(
            listOf(
                Pair(0, 3),
                Pair(3, 6),
                Pair(6, 9),
                Pair(9, 12),
                Pair(12, 15),
            ),
            listOf(0, 3, 6, 9, 12, 15).pairs()
        )
    }

    @Test
    fun Diffs() {
        assertEquals(
            listOf(
                3,
                3,
                3,
                3,
                3
            ),
            listOf(
                Pair(0, 3),
                Pair(3, 6),
                Pair(6, 9),
                Pair(9, 12),
                Pair(12, 15),
            ).diffs()
        )
    }

    @Test
    fun `Series`() {
        assertEquals(
            listOf(
                listOf(0, 3, 6, 9, 12, 15),
                listOf(3, 3, 3, 3, 3),
                listOf(0, 0, 0, 0),
            ),
            listOf(0, 3, 6, 9, 12, 15).series()
        )
    }

    @Test
    fun `day9 part1`() {
        assertEquals(18, Day9(listOf(0, 3, 6, 9, 12, 15)).nextValue())
        assertEquals(28, Day9(listOf(1, 3, 6, 10, 15, 21)).nextValue())
        assertEquals(68, Day9(listOf(10, 13, 16, 21, 30, 45)).nextValue())

        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()

        assertEquals(114, ParseDay9(input).part1())
    }

    @Test
    fun `day9 part2`() {
        assertEquals(-3, Day9(listOf(0, 3, 6, 9, 12, 15)).previousValue())
        assertEquals(0, Day9(listOf(1, 3, 6, 10, 15, 21)).previousValue())
        assertEquals(5, Day9(listOf(10, 13, 16, 21, 30, 45)).previousValue())

        val input = """
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent()

        assertEquals(2, ParseDay9(input).part2())
    }
}