package biscuits

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BiscuitTest {

    @Test
    fun `should return Club for biscuit id 1`() {
        val club = Biscuit("Club")
        assertThat(findBiscuit("1")).isEqualTo(club)
    }

    @Test
    fun `should return Trio for biscuit id 2`() {
        val trio = Biscuit("Trio")
        assertThat(findBiscuit("2")).isEqualTo(trio)
    }

    @Test
    fun `should not find any other biscuit`() {
        assertThat(findBiscuit("3")).isEqualTo(BiscuitNotFound)
    }

}