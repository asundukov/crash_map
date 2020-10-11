package io.cutebot.crashmap.tools

import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*


internal class StringCarefulDividerTest {
    @Test
    fun testShortMessage() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 20)
        val actual = stringCarefulDivider.divide("short message")

        assertThat(actual.size, equalTo(1))
        assertThat(actual[0], equalTo("short message"))
    }

    @Test
    fun testTwoLines() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("short\nmessage")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("short"))
        assertThat(actual[1], equalTo("message"))
    }

    @Test
    fun testTwoBlocks() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("short. message")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("short."))
        assertThat(actual[1], equalTo("message"))
    }

    @Test
    fun testTwoWords() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("short message")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("short"))
        assertThat(actual[1], equalTo("message"))
    }

    @Test
    fun testNoDividers() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("shortmessage")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("shortmessa"))
        assertThat(actual[1], equalTo("ge"))
    }

    @Test
    fun testLastDivider() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("shortshort.")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("shortshort"))
        assertThat(actual[1], equalTo("."))
    }

    @Test
    fun testOnlyDividers() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 2)
        val actual = stringCarefulDivider.divide("..,,.")

        assertThat(actual.size, equalTo(3))
        assertThat(actual[0], equalTo(".."))
        assertThat(actual[1], equalTo(",,"))
        assertThat(actual[2], equalTo("."))
    }

    @Test
    fun testLastSpaces() {
        val stringCarefulDivider = StringCarefulDivider(chunkSize = 10)
        val actual = stringCarefulDivider.divide("Short message    ")

        assertThat(actual.size, equalTo(2))
        assertThat(actual[0], equalTo("Short"))
        assertThat(actual[1], equalTo("message"))
    }
}