package days

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day2Test {

    @Test
    fun `Parse line`() {
        val game = Game(
            id = 1,
            cubes = listOf(
                Cubes(blue = 3, red = 4),
                Cubes(red = 1, green = 2, blue = 6),
                Cubes(green = 2)
            )
        )
        assertEquals(game, ParseDay2("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"))
    }

    @Test
    fun `Valid games`() {
        assertTrue(ParseDay2("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").valid())
        assertFalse(ParseDay2("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red").valid())
    }

    @Test
    fun `Part1`() {
        val games = listOf(
            ParseDay2("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
            ParseDay2("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
            ParseDay2("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
            ParseDay2("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"),
            ParseDay2("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        )
        assertEquals(8, games.part1())
    }

    @Test
    fun `Min`() {
        assertEquals(
            Cubes(red = 4, green = 2, blue = 6),
            ParseDay2("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green").min()
        )

        assertEquals(
            Cubes(red = 20, green = 13, blue = 6),
            ParseDay2("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red").min()
        )
    }

    @Test
    fun `Part2`() {
        val games = listOf(
            ParseDay2("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green"),
            ParseDay2("Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue"),
            ParseDay2("Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red"),
            ParseDay2("Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red"),
            ParseDay2("Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")
        )

        assertEquals(2286, games.part2())
    }
}