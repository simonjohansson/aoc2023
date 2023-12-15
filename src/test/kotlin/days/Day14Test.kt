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
            Day14.from(input).rows.rotate { it }
        )

        assertEquals(
            listOf(
                "1234",
                "1234",
                "1234",
                "1234",
            ),
            Day14.from(input).rows.rotate { it }.rotate { it }
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

    @Test
    fun `west`() {
        val expected = """
        O....#....
        OOO.#....#
        .....##...
        OO.#OO....
        OO......#.
        O.#O...#.#
        O....#OO..
        O.........
        #....###..
        #OO..#....
    """.trimIndent()

        assertEquals(Day14.from(expected), Day14.from(input).west())
    }

    @Test
    fun `south`() {
        val expected = """
        .....#....
        ....#....#
        ...O.##...
        ...#......
        O.O....O#O
        O.#..O.#.#
        O....#....
        OO....OO..
        #OO..###..
        #OO.O#...O
    """.trimIndent()
        assertEquals(Day14.from(expected), Day14.from(input).south())
    }

    @Test
    fun `east`() {
        val expected = """
        ....O#....
        .OOO#....#
        .....##...
        .OO#....OO
        ......OO#.
        .O#...O#.#
        ....O#..OO
        .........O
        #....###..
        #..OO#....
    """.trimIndent()

        assertEquals(Day14.from(expected), Day14.from(input).east())
    }

    @Test
    fun `spin`() {
        val spin1 = """
            .....#....
            ....#...O#
            ...OO##...
            .OO#......
            .....OOO#.
            .O#...O#.#
            ....O#....
            ......OOOO
            #...O###..
            #..OO#....
        """.trimIndent()

        val spin2 = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #..OO###..
            #.OOO#...O
        """.trimIndent()

        val spin3 = """
            .....#....
            ....#...O#
            .....##...
            ..O#......
            .....OOO#.
            .O#...O#.#
            ....O#...O
            .......OOO
            #...O###.O
            #.OOO#...O
        """.trimIndent()

        assertEquals(Day14.from(spin1), Day14.from(input).spin())
        assertEquals(Day14.from(spin2), Day14.from(input).spin().spin())
        assertEquals(Day14.from(spin3), Day14.from(input).spin().spin().spin())

        assertEquals(Day14.from(spin1), Day14.from(input).spin(1))
        assertEquals(Day14.from(spin2), Day14.from(input).spin(2))
        assertEquals(Day14.from(spin3), Day14.from(input).spin(3))
    }

    @Test
    fun part2() {
        assertEquals(64 ,Day14.from(input).part2())
    }
}
