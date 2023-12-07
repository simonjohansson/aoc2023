package days

import org.junit.jupiter.api.Assertions.assertEquals
import java.util.*
import kotlin.test.Test

class Day5Test {

    @Test
    fun `Parse`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        assertEquals(listOf(79L, 14L, 55L, 13L), ParseDay5(input).seeds)

        assertEquals(
            listOf(
                Range(LongRange(50, 51), LongRange(98, 99)),
                Range(LongRange(52, 99), LongRange(50, 97))
            ), ParseDay5(input).seedToSoil
        )
    }

    @Test
    fun `Test range`() {
        assertEquals(
            Optional.of(3788621315),
            Range(LongRange(3788621315, 4057598289), LongRange(24578909, 293555883)).findDestination(24578909)
        )
        assertEquals(
            Optional.of(4057598289),
            Range(LongRange(3788621315, 4057598289), LongRange(24578909, 293555883)).findDestination(293555883)
        )
        assertEquals(
            Optional.of(3788621317),
            Range(LongRange(3788621315, 4057598289), LongRange(24578909, 293555883)).findDestination(24578911)
        )
        assertEquals(
            Optional.empty<Long>(),
            Range(LongRange(3788621315, 4057598289), LongRange(24578909, 293555883)).findDestination(100)
        )
    }

    @Test
    fun `Part1`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        assertEquals(
            35, ParseDay5(input).part1()
        )
    }

    @Test
    fun `Part2`() {
        val input = """
            seeds: 79 14 55 13

            seed-to-soil map:
            50 98 2
            52 50 48

            soil-to-fertilizer map:
            0 15 37
            37 52 2
            39 0 15

            fertilizer-to-water map:
            49 53 8
            0 11 42
            42 0 7
            57 7 4

            water-to-light map:
            88 18 7
            18 25 70

            light-to-temperature map:
            45 77 23
            81 45 19
            68 64 13

            temperature-to-humidity map:
            0 69 1
            1 0 69

            humidity-to-location map:
            60 56 37
            56 93 4
        """.trimIndent()

        assertEquals(46, ParseDay5(input).part2())
    }
}