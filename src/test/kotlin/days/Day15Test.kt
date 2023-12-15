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

    @Test
    fun `toOps`() {
        assertEquals(
            listOf(
                Add("rn", 1),
                Remove("cm"),
                Add("qp", 3),
                Add("cm", 2),
                Remove("qp"),
                Add("pc", 4),
                Add("ot", 9),
                Add("ab", 5),
                Remove("pc"),
                Add("pc", 6),
                Add("ot", 7)
            ), Day15.from(input).toOps()
        )
    }

    @Test
    fun part2() {
        assertEquals(145, Day15.from(input).part2())
    }
}