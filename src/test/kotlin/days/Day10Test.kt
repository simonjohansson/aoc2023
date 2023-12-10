package days

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun parse() {
        var input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

        assertEquals(
            Day10(
                start = Point(1, 1),
                grid = listOf(
                    listOf(".", ".", ".", ".", "."),
                    listOf(".", "F", "-", "7", "."),
                    listOf(".", "|", ".", "|", "."),
                    listOf(".", "L", "-", "J", "."),
                    listOf(".", ".", ".", ".", "."),
                ),
            ),
            ParseDay10(input, "F")
        )
    }

    @Test
    fun `part1`() {
        var input = """
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent()

        assertEquals(4, ParseDay10(input, "F").part1())

        var input2 = """
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent()

        assertEquals(8, ParseDay10(input2, "F").part1())

    }
}