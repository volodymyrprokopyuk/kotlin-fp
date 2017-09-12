package org.vld.sfpp.ch01

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object MainSpek : Spek({

    given("a string with letters and other symbols") {
        val string = "hello world!"
        on("call letterFrequency on the string") {
            val frequency = letterFrequency(string)
            it("should return correct letter frequency for the string") {
                val expectedFrequency = mapOf('h' to 1, 'e' to 1, 'l' to 3, 'o' to 2, 'w' to 1, 'r' to 1, 'd' to 1)
                assertThat(frequency, equalTo(expectedFrequency))
            }
        }
    }

    given("an empty string") {
        val string = ""
        on("call letterFrequency on the empty string") {
            val frequency = letterFrequency(string)
            it("should return empty frequency map") {
                val expectedFrequency = emptyMap<Char, Int>()
                assertThat(frequency, equalTo(expectedFrequency))
            }
        }
    }

    given("a list of sorted numbers") {
        val sortedNumbers = listOf(1, 2, 3, 5, 6, 8, 10, 11)
        on("call groupContinuousNumbers on the sorted numbers") {
            val groups = groupContinuousNumbers(sortedNumbers)
            it("should return a list of groups of continuous numbers") {
                val expectedGroups = listOf(listOf(1, 2, 3), listOf(5, 6), listOf(8), listOf(10, 11))
                assertThat(groups, equalTo(expectedGroups))
            }
        }
    }

    given("an empty list of sorted numbers") {
        val sortedNumbers = emptyList<Int>()
        on("call groupContinuousNumbers ib the empty list of sorted numbers") {
            val groups = groupContinuousNumbers(sortedNumbers)
            it("shoudl return an empty list of groups of continuous numbers") {
                val expectedGroups = listOf(emptyList<Int>())
                assertThat(groups, equalTo(expectedGroups))
            }
        }
    }

})
