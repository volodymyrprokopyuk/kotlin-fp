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
            it("should return size of the list") {
                val expectedSize = list.size
                assertThat(size, equalTo(expectedSize))
            }
        }
    }

    given("an empty lsit") {
        val list = listOf<Int>()
        on("call listSize on the empty list") {
            val size = listSize(list)
            it("should return 0 as size of the list") {
                val expectedSize = list.size
                assertThat(size, equalTo(expectedSize))
            }
        }
    }

})
