package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun `Parse`() {
        val input = """
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

        assertEquals(
            Day11(
                grid = listOf(
                    listOf(".", ".", ".", "#", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", "#", ".", "."),
                    listOf("#", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", "#", ".", ".", "."),
                    listOf(".", "#", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", "#"),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", "#", ".", "."),
                    listOf("#", ".", ".", ".", "#", ".", ".", ".", ".", "."),
                )
            ), ParseDay11(input)
        )
    }

    @Test
    fun `Expand`() {
        val input = """
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

        assertEquals(
            Day11(
                grid = listOf(
                    listOf(".", ".", ".", ".", "#", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", "#", ".", ".", "."),
                    listOf("#", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", "#", ".", ".", ".", "."),
                    listOf(".", "#", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "#"),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", ".", "."),
                    listOf(".", ".", ".", ".", ".", ".", ".", ".", ".", "#", ".", ".", "."),
                    listOf("#", ".", ".", ".", ".", "#", ".", ".", ".", ".", ".", ".", "."),
                )
            ), ParseDay11(input).expand()
        )
    }

    @Test
    fun `find galaxies`() {
        val input = """
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

        assertEquals(
            listOf(
                Point(3, 0),
                Point(7, 1),
                Point(0, 2),
                Point(6, 4),
                Point(1, 5),
                Point(9, 6),
                Point(7, 8),
                Point(0, 9),
                Point(4, 9),
            ),
            ParseDay11(input).findGalaxies()
        )
    }

    @Test
    fun `find pairs`() {
        val input = listOf(
            Point(0, 0),
            Point(1, 1),
            Point(2, 2),
            Point(3, 3),
        )

        assertEquals(6, input.pairs().count())

        assertEquals(
            listOf(
                Pair(Point(0, 0), Point(1, 1)),
                Pair(Point(0, 0), Point(2, 2)),
                Pair(Point(0, 0), Point(3, 3)),
                Pair(Point(1, 1), Point(2, 2)),
                Pair(Point(1, 1), Point(3, 3)),
                Pair(Point(2, 2), Point(3, 3)),
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

        assertEquals(36, ParseDay11(input2).expand().findGalaxies().pairs().count())
    }

    @Test
    fun `Part1`() {
        """
            #.#
        """.trimIndent().let {
            assertEquals(3, ParseDay11(it).part1())
        }

        """
            #..
            ..#
        """.trimIndent().let {
            assertEquals(4, ParseDay11(it).part1())
        }

        """
            ..#
            #..
        """.trimIndent().let {
            assertEquals(4, ParseDay11(it).part1())
        }

        """
            #..
            .#.
            ..#
        """.trimIndent().let {
            assertEquals(8, ParseDay11(it).part1())
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
            assertEquals(374, ParseDay11(it).part1())
        }
    }
}