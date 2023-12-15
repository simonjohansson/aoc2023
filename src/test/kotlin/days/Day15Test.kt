package days

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class Day15Test {
    val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    @Test
    fun parse() {

        assertEquals(
            Day15(
                listOf(
                    "rn=1",
                    "cm-",
                    "qp=3",
                    "cm=2",
                    "qp-",
                    "pc=4",
                    "ot=9",
                    "ab=5",
                    "pc-",
                    "pc=6",
                    "ot=7"
                )
            ), Day15.from(input)
        )
    }

    @Test
    fun hash() {
        assertEquals(52, "HASH".HASH())
    }

    @Test
    fun part1() {
        assertEquals(1320, Day15.from(input).part1())
    }
}