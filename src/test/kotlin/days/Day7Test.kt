package days

import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertTrue

class Day7Test {

    @Test
    fun `Parse`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        assertEquals(
            listOf(
                Hand("32T3K", 765),
                Hand("T55J5", 684),
                Hand("KK677", 28),
                Hand("KTJJT", 220),
                Hand("QQQJA", 483),
            ), ParseDay7(input)
        )
    }

    @Test
    fun `Score`() {
        assertEquals(
            listOf(13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1),
            Hand("AKQJT98765432").cardScores()
        )
        assertEquals(
            listOf(13, 12, 11, 0, 9, 8, 7, 6, 5, 4, 3, 2, 1),
            Hand("AKQJT98765432").cardScores(true)
        )
    }

    @Test
    fun `Type`() {
        assertEquals(Type.FIVE_OF_A_KIND, Hand("AAAAA").type())

        assertEquals(Type.FOUR_OF_A_KIND, Hand("AAAAK").type())
        assertEquals(Type.FOUR_OF_A_KIND, Hand("AAKAA").type())

        assertEquals(Type.FULL_HOUSE, Hand("AAAKK").type())
        assertEquals(Type.FULL_HOUSE, Hand("AKAKA").type())

        assertEquals(Type.THREE_OF_A_KIND, Hand("TTT98").type())
        assertEquals(Type.THREE_OF_A_KIND, Hand("T9T8T").type())

        assertEquals(Type.TWO_PAIR, Hand("23432").type())
        assertEquals(Type.TWO_PAIR, Hand("23231").type())

        assertEquals(Type.ONE_PAIR, Hand("A23A4").type())

        assertEquals(Type.HIGH_CARD, Hand("23456").type())
    }

    @Test
    fun `Sort`() {
        val input = listOf(
            Hand("44432"),
            Hand("44431"),
            Hand("44324"),
            Hand("23444"),
            Hand("32444")
        )
        val expected = listOf(
            Hand("23444"),
            Hand("32444"),
            Hand("44324"),
            Hand("44431"),
            Hand("44432")
        )
        assertEquals(expected, input.sort())
    }

    @Test
    fun `compareTo`() {
        assertEquals(1, Hand("KK677").compareTo(Hand("KTJJT")))
    }

    @Test
    fun `sortByTypeAndScore`() {
        val input = """
            2345A 1
            Q2KJJ 13
            Q2Q2Q 19
            T3T3J 17
            T3Q33 11
            2345J 3
            J345A 2
            32T3K 5
            T55J5 29
            KK677 7
            KTJJT 34
            QQQJA 31
            JJJJJ 37
            JAAAA 43
            AAAAJ 59
            AAAAA 61
            2AAAA 23
            2JJJJ 53
            JJJJ2 41
        """.trimIndent()

        val expectedPart1 = """
            2345J 3
            2345A 1
            J345A 2
            32T3K 5
            Q2KJJ 13
            T3T3J 17
            KTJJT 34
            KK677 7
            T3Q33 11
            T55J5 29
            QQQJA 31
            Q2Q2Q 19
            2JJJJ 53
            2AAAA 23
            JJJJ2 41
            JAAAA 43
            AAAAJ 59
            JJJJJ 37
            AAAAA 61
        """.trimIndent()

        val expectedPart2 = """
            2345A 1
            J345A 2
            2345J 3
            32T3K 5
            KK677 7
            T3Q33 11
            Q2KJJ 13
            T3T3J 17
            Q2Q2Q 19
            2AAAA 23
            T55J5 29
            QQQJA 31
            KTJJT 34
            JJJJJ 37
            JJJJ2 41
            JAAAA 43
            2JJJJ 53
            AAAAJ 59
            AAAAA 61
        """.trimIndent()

        assertEquals(ParseDay7(expectedPart1), ParseDay7(input).sortByTypeAndScore())
    }

    @Test
    fun `Part1`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

        assertEquals(6440, ParseDay7(input).part1())

        val extraInput = """
            2345A 1
            Q2KJJ 13
            Q2Q2Q 19
            T3T3J 17
            T3Q33 11
            2345J 3
            J345A 2
            32T3K 5
            T55J5 29
            KK677 7
            KTJJT 34
            QQQJA 31
            JJJJJ 37
            JAAAA 43
            AAAAJ 59
            AAAAA 61
            2AAAA 23
            2JJJJ 53
            JJJJ2 41
        """.trimIndent()

        assertEquals(6592, ParseDay7(extraInput).part1())
    }

    @Test
    fun `Permutations`() {

        assertEquals(
            listOf(
                Hand("2222A"),
                Hand("2222K"),
                Hand("2222Q"),
                Hand("2222T"),
                Hand("22229"),
                Hand("22228"),
                Hand("22227"),
                Hand("22226"),
                Hand("22225"),
                Hand("22224"),
                Hand("22223"),
                Hand("22222"),
            ),
            Hand("2222J").permutations()
        )

        val twoJacks = Hand("222JJ").permutations()
        listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2").flatMap { first ->
            listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2").map { second ->
                first to second
            }
        }
            .map { (a, b) -> Hand("222$a$b") }
            .forEach {
                assertTrue(twoJacks.contains(it), "Looking for $it")
            }
    }

    @Test
    fun `Part two full house`() {
        assertEquals(Type.FULL_HOUSE, Hand("2233J").type(true))
        assertEquals(Type.THREE_OF_A_KIND, Hand("AJJ94").type(true))
        assertEquals(Type.FOUR_OF_A_KIND, Hand("KTJJT").type(true))
    }

    @Test
    fun `Part2`() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        assertEquals(5905, ParseDay7(input).part2())
    }
}