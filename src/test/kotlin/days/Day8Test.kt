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
}