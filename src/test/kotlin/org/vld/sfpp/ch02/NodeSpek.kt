package org.vld.sfpp.ch02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object NodeSpek : Spek({

    given("an unorderd list of numbers") {
        val numbers = listOf(72, 50, 25, 65, 95, 88, 112)
        on("insert the numbers into a BST and travers the tree in ascending order") {
            val tree = Node(numbers.first())
            for (number in numbers.drop(1)) tree.insert(number)
            val result = tree.traverse()
            it("shuold return a list of tree values in ascending order") {
                val expectedResult = listOf(25, 50, 65, 72, 88, 95, 112)
                assertThat(result, equalTo(expectedResult))
            }
        }
    }

    given("an unorderd list of numbers with some duplicates") {
        val numbers = listOf(1, 2, 1, 3, 4, 5, 2)
        on("insert the number into a BST") {
            val tree = Node(numbers.first())
            for (number in numbers) tree.insert(number)
            val result = tree.traverse()
            it("should return an ordered list of numbers without duplicates") {
                val expectedResult = listOf(1, 2, 3, 4, 5)
                assertThat(result, equalTo(expectedResult))
            }
        }
    }

})
