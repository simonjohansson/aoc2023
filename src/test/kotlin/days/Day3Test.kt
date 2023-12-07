package days

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day3Test {

    @Test
    fun `Parse`() {
        val input = """467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..""".trimIndent()


        assertEquals(
            listOf(
                Day3Number(467, IntRange(0, 2), 0),
                Day3Number(114, IntRange(5, 7), 0),
                Day3Number(35, IntRange(2, 3), 2),
                Day3Number(633, IntRange(6, 8), 2),
                Day3Number(617, IntRange(0, 2), 4),
                Day3Number(58, IntRange(7, 8), 5),
                Day3Number(592, IntRange(2, 4), 6),
                Day3Number(755, IntRange(6, 8), 7),
                Day3Number(664, IntRange(1, 3), 9),
                Day3Number(598, IntRange(5, 7), 9),
            ), ParseDay3(input).numbers
        )

        assertEquals(".", ParseDay3(input).grid.at(-1, 0))
        assertEquals(".", ParseDay3(input).grid.at(0, -1))
        assertEquals(".", ParseDay3(input).grid.at(100, 0))
        assertEquals(".", ParseDay3(input).grid.at(0, 100))

        assertEquals("4", ParseDay3(input).grid.at(0, 0))
        assertEquals("6", ParseDay3(input).grid.at(1, 0))

        assertFalse(ParseDay3(input).grid.at(0, 0).isSymbol())
        assertFalse(ParseDay3(input).grid.at(3, 0).isSymbol())
        assertTrue(ParseDay3(input).grid.at(3, 1).isSymbol())
    }

    @Test
    fun `Part1`() {
        val input = """467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..""".trimIndent()

        assertEquals(4361, ParseDay3(input).part1())
    }

    @Test
    fun `Part2`() {
        val input = """467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...$.*....
            .664.598..""".trimIndent()

        assertEquals(
            467835, ParseDay3(input).part2()
        )
    }
}