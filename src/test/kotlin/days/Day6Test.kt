package days

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day6Test {

    @Test
    fun `Test parse`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        assertEquals(
            listOf(
                Day6(7, 9),
                Day6(15, 40),
                Day6(30, 200),
            ), ParseDay6(input)
        )
    }

    @Test
    fun `Day6 part one`() {
        val expected = listOf(
            Day6(7, 9, 0),
            Day6(6, 9, 1),
            Day6(5, 9, 2),
            Day6(4, 9, 3),
            Day6(3, 9, 4),
            Day6(2, 9, 5),
            Day6(1, 9, 6),
            Day6(0, 9, 7),
        )
        assertEquals(expected, Day6(7, 9).games())

        assertEquals(listOf(false, false, true, true, true, true, false, false), expected.map { it.play() })
        assertEquals(expected.play(), expected.map { it.play() })

        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        assertEquals(288, ParseDay6(input).part1())
    }

    @Test
    fun `Parse second`() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        assertEquals(
            listOf(Day6(71530, 940200)), ParseDay6Part2(input)
        )


        assertEquals(71503, ParseDay6Part2(input).part1())
    }

}