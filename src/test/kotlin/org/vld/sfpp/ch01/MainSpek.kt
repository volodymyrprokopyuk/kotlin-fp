package org.vld.sfpp.ch01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object MainSpek : Spek({

    given("a string with letters and other symbols") {
        val str = "hello world!"
        on("calling letterFrequency on the string") {
            val frequency = letterFrequency(str)
            it("should return correct letter frequency for the string") {
                val expectedFrequency = mapOf('h' to 1, 'e' to 1, 'l' to 3, 'o' to 2, 'w' to 1, 'r' to 1, 'd' to 1)
                assertThat(frequency, equalTo(expectedFrequency))
            }
        }
    }

    given("an empty string") {
        val str = ""
        on("calling letterFrequence on the empty string") {
            val frequency = letterFrequency(str)
            it("should return empty frequency map") {
                val expectedFrequency = emptyMap<Char, Int>()
                assertThat(frequency, equalTo(expectedFrequency))
            }
        }
    }

})
