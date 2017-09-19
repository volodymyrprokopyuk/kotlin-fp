package org.vld.sfpp.ch02

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class NodeTest {

    @Test
    @DisplayName("Given an unordered list of numbers. When insert the numbers into a BST. Then return a list of tree values in ascending order")
    fun givenUnorderedList_whenInsertIntoBst_thenReturnOrderedList() {
        // Given
        val numbers = listOf(72, 50, 25, 65, 95, 88, 112)
        // When
        val tree = Node(numbers.first())
        for (number in numbers.drop(1)) tree.insert(number)
        val result = tree.traverse()
        // Then
        val expectedResult = listOf(25, 50, 65, 72, 88, 95, 112)
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    @DisplayName("Given an unordered list with duplicate numbers. When insert the numbers into a BST. Then return an ordered list of numbers without duplicates")
    fun givenUnorderedListWithDuplicates_whenInsertIntoBst_thenReturnOrderedListWithoutDuplicates() {
        // Given
        val numbers = listOf(1, 2, 1, 3, 4, 5, 2)
        // When
        val tree = Node(numbers.first())
        for (number in numbers) tree.insert(number)
        val result = tree.traverse()
        // Then
        val expectedResult = listOf(1, 2, 3, 4, 5)
        assertThat(result).isEqualTo(expectedResult)
    }

}