package org.vld.sfpp.ch03

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object MainSpek : Spek({

    given("a non empty list") {
        val list = listOf(1, 2, 3, 4)
        on("call listSize on the non empty list") {
            val size = listSize(list)
            it("should return the size of the list") {
                val expectedSize = list.size
                assertThat(size, equalTo(expectedSize))
            }
        }
    }

    given("an empty list") {
        val list = listOf<Int>()
        on("call listSize on the empty list") {
            val size = listSize(list)
            it("should return 0 as size of the list") {
                val expectedSize = list.size
                assertThat(size, equalTo(expectedSize))
            }
        }
    }

    given("a large list") {
        val list = (0..100).toList()
        on("call listSize on the large list") {
            val size = listSize(list)
            it("should return the size of the large list") {
                val expectedSize = list.size
                assertThat(size, equalTo(expectedSize))
            }
        }
    }

    given("a non empty list") {
        val list = listOf(1, 2, 3, 4)
        on("call listNthElement with an index withing the range") {
            val element = listNthElement(list, 1)
            it("should return the Nth element") {
                val expectedElement = list[1]
                assertThat(element, equalTo(expectedElement))
            }
        }
    }

    given("an empty list") {
        val list = listOf<Int>()
        on("call listNthElement on the empty list") {
            val element = listNthElement(list, 1)
            it("should return null") {
                val expectedElement: Int? = null
                assertThat(element, equalTo(expectedElement))
            }
        }
    }

    given("a non empty list") {
        val list = listOf(1, 2, 3, 4)
        on("call listNthElement with an index out of the range") {
            val element = listNthElement(list, 10)
            it("should return null") {
                val expectedElement: Int? = null
                assertThat(element, equalTo(expectedElement))
            }
        }
    }

})
