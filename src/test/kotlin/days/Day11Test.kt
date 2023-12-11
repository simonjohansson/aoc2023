package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun `Parse`() {
        val input = """
            .#
            #.
        """.trimIndent()

        assertEquals(
            Day11(
                grid = listOf(
                    listOf("." to Pair(0, 0), "#" to Pair(1, 0)),
                    listOf("#" to Pair(0, 1), "." to Pair(1, 1)),
                )
            ), ParseDay11(input)
        )
    }

    @Test
    fun `Expand`() {
        val input = """
            ..#
            ...
            ...
            #..
        """.trimIndent()

        assertEquals(
            listOf(
                Pair(3L, 0L),
                Pair(0L, 5L),
            ),
            ParseDay11(input).expand()
        )
    }

    @Test
    fun `find pairs`() {
        val input = listOf(
            Pair(0L, 0L),
            Pair(1L, 1L),
            Pair(2L, 2L),
            Pair(3L, 3L),
        )

        assertEquals(6, input.pairs().count())

        assertEquals(
            listOf(
                Pair(Pair(0L, 0L), Pair(1L, 1L)),
                Pair(Pair(0L, 0L), Pair(2L, 2L)),
                Pair(Pair(0L, 0L), Pair(3L, 3L)),
                Pair(Pair(1L, 1L), Pair(2L, 2L)),
                Pair(Pair(1L, 1L), Pair(3L, 3L)),
                Pair(Pair(2L, 2L), Pair(3L, 3L)),
            ),
            input.pairs()
        )

        val input2 = """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent()

        assertEquals(36, ParseDay11(input2).expand().pairs().count())
    }

    @Test
    fun `Part1`() {
        """
            #.#
        """.trimIndent().let {
            assertEquals(3, ParseDay11(it).go(2))
        }

        """
            #..
            ..#
        """.trimIndent().let {
            assertEquals(4, ParseDay11(it).go(2))
        }

        """
            ..#
            #..
        """.trimIndent().let {
            assertEquals(4, ParseDay11(it).go(2))
        }

        """
            #..
            .#.
            ..#
        """.trimIndent().let {
            assertEquals(8, ParseDay11(it).go(2))
        }

        """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent().let {
            assertEquals(374, ParseDay11(it).go(2))
        }
    }

    @Test
    fun `Part2`() {
        """
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent().let {
            assertEquals(1030, ParseDay11(it).go(10))
            assertEquals(8410, ParseDay11(it).go(100))
        }
    }
}