package days

import days.Direction.*
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day16Test {
    val input = """
        .|...\....
        |.-.\.....
        .....|-...
    """.trimIndent()

    @Test
    fun parse() {
        assertEquals(
            Day16(
                listOf(
                    """.|...\....""",
                    """|.-.\.....""",
                    """.....|-...""",
                ),
            ), Day16.from(input)
        )
    }

    @Test
    fun nextDot() {
        // .
        val grid = Day16.from(
            """
                ...
                ...
                ...
                """.trimIndent()
        ).grid

        assertEquals(
            listOf(EAST to Point(2, 1)),
            next(grid, EAST to Point(1, 1))
        )

        assertEquals(
            listOf(WEST to Point(0, 1)),
            next(grid, WEST to Point(1, 1))
        )

        assertEquals(
            listOf(SOUTH to Point(1, 2)),
            next(grid, SOUTH to Point(1, 1))
        )

        assertEquals(
            listOf(NORTH to Point(1, 0)),
            next(grid, NORTH to Point(1, 1))
        )
    }

    @Test
    fun nextDash() {
        // .
        val grid = Day16.from(
            """
                ...
                .-.
                ...
                """.trimIndent()
        ).grid

        assertEquals(
            listOf(EAST to Point(2, 1)),
            next(grid, EAST to Point(1, 1))
        )

        assertEquals(
            listOf(WEST to Point(0, 1)),
            next(grid, WEST to Point(1, 1))
        )

        assertEquals(
            listOf(
                EAST to Point(2, 1),
                WEST to Point(0, 1)
            ),
            next(grid, SOUTH to Point(1, 1))
        )

        assertEquals(
            listOf(
                EAST to Point(2, 1),
                WEST to Point(0, 1)
            ),
            next(grid, NORTH to Point(1, 1))
        )
    }

    @Test
    fun nextPipe() {
        // .
        val grid = Day16.from(
            """
                ...
                .|.
                ...
                """.trimIndent()
        ).grid

        assertEquals(
            listOf(
                NORTH to Point(1, 0),
                SOUTH to Point(1, 2)
            ),
            next(grid, EAST to Point(1, 1))
        )

        assertEquals(
            listOf(
                NORTH to Point(1, 0),
                SOUTH to Point(1, 2)
            ),
            next(grid, WEST to Point(1, 1))
        )

        assertEquals(
            listOf(
                SOUTH to Point(1, 2),
            ),
            next(grid, SOUTH to Point(1, 1))
        )

        assertEquals(
            listOf(
                NORTH to Point(1, 0)
            ),
            next(grid, NORTH to Point(1, 1))
        )
    }

    @Test
    fun nextForwardSlash() {
        // .
        val grid = Day16.from(
            """
                ...
                ./.
                ...
                """.trimIndent()
        ).grid

        assertEquals(
            listOf(
                NORTH to Point(1, 0)
            ),
            next(grid, EAST to Point(1, 1))
        )

        assertEquals(
            listOf(
                SOUTH to Point(1, 2)
            ),
            next(grid, WEST to Point(1, 1))
        )

        assertEquals(
            listOf(
                WEST to Point(0, 1),
            ),
            next(grid, SOUTH to Point(1, 1))
        )

        assertEquals(
            listOf(
                EAST to Point(2, 1)
            ),
            next(grid, NORTH to Point(1, 1))
        )
    }

    @Test
    fun nextBackwardSlash() {
        // .
        val grid = Day16.from(
            """
                ...
                .\.
                ...
                """.trimIndent()
        ).grid

        assertEquals(
            listOf(
                SOUTH to Point(1, 2)
            ),
            next(grid, EAST to Point(1, 1))
        )

        assertEquals(
            listOf(
                NORTH to Point(1, 0)
            ),
            next(grid, WEST to Point(1, 1))
        )

        assertEquals(
            listOf(
                EAST to Point(2, 1),
            ),
            next(grid, SOUTH to Point(1, 1))
        )

        assertEquals(
            listOf(
                WEST to Point(0, 1)
            ),
            next(grid, NORTH to Point(1, 1))
        )
    }

    @Test
    fun part1() {
        val input = """
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent()

        assertEquals(46, Day16.from(input).part1())
    }
}