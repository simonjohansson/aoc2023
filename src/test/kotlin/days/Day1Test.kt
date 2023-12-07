package days

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day1Test {

    @Test
    fun Parse() {
        val input = """
        12bc
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent()

        assertEquals(Day1(listOf(listOf(1, 2), listOf(3, 8), listOf(1, 2, 3, 4, 5), listOf(7))), ParseDay1(input))
    }

    @Test
    fun `Part1`() {
        val input = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
        """.trimIndent()
        assertEquals(142, ParseDay1(input).part1())
    }

    @Test
    fun Parse2() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()
        assertEquals(
            Day1(
                listOf(
                    listOf(2, 1, 9),
                    listOf(8, 3),
                    listOf(1, 2, 3),
                    listOf(2, 3, 4),
                    listOf(4, 9, 8, 7, 2),
                    listOf(1, 2, 3, 4),
                    listOf(7, 6)
                )
            ), ParseDay1(input)
        )
    }
}