package days

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {

    @Test
    fun Parse() {
        val input = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assertEquals(
            Day8(
                instructions = listOf("R", "L"),
                mapOf(
                    "AAA" to Pair("BBB", "CCC"),
                    "BBB" to Pair("DDD", "EEE"),
                    "CCC" to Pair("ZZZ", "GGG"),
                    "DDD" to Pair("DDD", "DDD"),
                    "EEE" to Pair("EEE", "EEE"),
                    "GGG" to Pair("GGG", "GGG"),
                    "ZZZ" to Pair("ZZZ", "ZZZ"),
                )
            ), ParseDay8(input)
        )
    }

    @Test
    fun repeat() {
        val seq = listOf("1", "2", "3").repeat()
        assertEquals("1", seq.next())
        assertEquals("2", seq.next())
        assertEquals("3", seq.next())
        assertEquals("1", seq.next())
    }

    @Test
    fun part1() {
        val input = """
        RL

        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assertEquals(2, ParseDay8(input).part1())

        val input2 = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        assertEquals(6, ParseDay8(input2).part1())
    }

    @Test
    fun `test lcm and gcd`() {
        assertEquals(9, gcd(18L, 27L))
        assertEquals(281, gcd(12083L, 13207L))

        assertEquals(567901, lcm(12083L, 13207L))

        assertEquals(510, listOf(10L, 15L, 17L).reduce { acc, l -> lcm(acc, l) })
    }

    @Test
    fun part2() {
        val input = """
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()

        assertEquals(6, ParseDay8(input).part2())
    }
}