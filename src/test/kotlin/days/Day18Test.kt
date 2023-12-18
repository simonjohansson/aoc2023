package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day18Test {

    @Test
    fun parse() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
        """.trimIndent()

        assertEquals(
            Day18(
                listOf(
                    "R 6 (#70c710)",
                    "D 5 (#0dc571)",
                    "L 2 (#5713f0)"
                )
            ),
            Day18.from(input)
        )
    }

    @Test
    fun coords() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            U 1 (#5713f0)
        """.trimIndent()

        assertEquals(
            listOf(
                Point(0, 0),
                Point(1, 0),
                Point(2, 0),
                Point(3, 0),
                Point(4, 0),
                Point(5, 0),
                Point(6, 0),
                Point(6, 1),
                Point(6, 2),
                Point(6, 3),
                Point(6, 4),
                Point(6, 5),
                Point(5, 5),
                Point(4, 5),
                Point(4, 4),
            ),
            Day18.from(input).toPoints()
        )


        val input2 = """
            L 1 (#70c710)
            U 1 (#0dc571)
        """.trimIndent()

        assertEquals(
            listOf(
                Point(1, 1),
                Point(0, 1),
                Point(0, 0)
            ),
            Day18.from(input2).toPoints()
        )
    }

    @Test
    fun size() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent()

        assertEquals(7 to 10, Day18.from(input).size())
    }

    @Test
    fun print() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent()

        val expected = """
            #######
            #.....#
            ###...#
            ..#...#
            ..#...#
            ###.###
            #...#..
            ##..###
            .#....#
            .######
        """.trimIndent()

        """
            ####
            #  #
            #  #
            ####
        """.trimIndent()
        assertEquals(expected, Day18.from(input).charGrid())
    }

    @Test
    fun findInside() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent()

        assertEquals(1 to 1, Day18.from(input).findInside())
    }

    @Test
    fun fill() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent()
        val expected = """
            #######
            #######
            #######
            ..#####
            ..#####
            #######
            #####..
            #######
            .######
            .######
        """.trimIndent()

        assertEquals(expected, Day18.from(input).fill())
    }

    @Test
    fun part1() {
        val input = """
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent()

        val inp = """
            R 461937
            D 56407
            R 356671
            D 863240
            R 367720
            D 266681
            L 577262
            U 829975
            L 112010
            D 829975
            L 491645
            U 686074
            L 5411
            U 500254
        """.trimIndent()
        assertEquals(62, Day18.from(inp).part1())
    }
}