package days

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day12Test {

    @Test
    fun parse() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent()

        assertEquals(
            Day12(
                listOf(
                    Row(springs = "???.###", size = listOf(1, 1, 3)),
                    Row(springs = ".??..??...?##.", size = listOf(1, 1, 3)),
                    Row(springs = "?#?#?#?#?#?#?#?", size = listOf(1, 3, 1, 6)),
                    Row(springs = "????.#...#...", size = listOf(4, 1, 1)),
                    Row(springs = "????.######..#####.", size = listOf(1, 6, 5)),
                    Row(springs = "?###????????", size = listOf(3, 2, 1)),
                )
            ),
            ParseDay12(input)
        )
    }

    @Test
    fun `find arrangements`() {
        assertEquals(
            listOf(
                Row(springs = "###.###", size = listOf(1, 1, 3)),
                Row(springs = ".##.###", size = listOf(1, 1, 3)),
                Row(springs = "#.#.###", size = listOf(1, 1, 3)),
                Row(springs = "..#.###", size = listOf(1, 1, 3)),
                Row(springs = "##..###", size = listOf(1, 1, 3)),
                Row(springs = ".#..###", size = listOf(1, 1, 3)),
                Row(springs = "#...###", size = listOf(1, 1, 3)),
                Row(springs = "....###", size = listOf(1, 1, 3))
            ),
            Row(springs = "???.###", listOf(1, 1, 3)).arrangements()
        )
    }

    @Test
    fun `valid`() {
        assertFalse(Row(springs = "###.###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = ".##.###", size = listOf(1, 1, 3)).valid())
        assertTrue(Row(springs = "#.#.###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = "..#.###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = "##..###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = ".#..###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = "#...###", size = listOf(1, 1, 3)).valid())
        assertFalse(Row(springs = "....###", size = listOf(1, 1, 3)).valid())
    }

    @Test
    fun `part1`() {
        val input = """
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent()

        assertEquals(21, ParseDay12(input).part1())
    }
}