package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day14Test {
    val input = """
        O....#....
        O.OO#....#
        .....##...
        OO.#O....O
        .O.....O#.
        O.#..O.#.#
        ..O..#O..O
        .......O..
        #....###..
        #OO..#....
    """.trimIndent()

    @Test
    fun parse() {
        assertEquals(
            Day14(
                listOf(
                    "O....#....",
                    "O.OO#....#",
                    ".....##...",
                    "OO.#O....O",
                    ".O.....O#.",
                    "O.#..O.#.#",
                    "..O..#O..O",
                    ".......O..",
                    "#....###..",
                    "#OO..#....",
                )
            ),
            Day14.from(input)
        )
    }

    @Test
    fun `rotate`() {
        val input = """
            1234
            1234
            1234
            1234
        """.trimIndent()

        assertEquals(
            listOf(
                "1111",
                "2222",
                "3333",
                "4444",
            ),
            Day14.from(input).rows.rotate()
        )

        assertEquals(
            listOf(
                "1234",
                "1234",
                "1234",
                "1234",
            ),
            Day14.from(input).rows.rotate().rotate()
        )
    }

    @Test
    fun north() {
        val expected = """
            OOOO.#.O..
            OO..#....#
            OO..O##..O
            O..#.OO...
            ........#.
            ..#....#.#
            ..O..#.O.O
            ..O.......
            #....###..
            #....#....
        """.trimIndent()

        assertEquals(Day14.from(expected), Day14.from(input).north())
    }

    @Test
    fun part1() {
        assertEquals(136, Day14.from(input).part1())
    }
}