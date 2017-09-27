package org.vld.fp.sfpp.ch01

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @Test
    @DisplayName("Given a non-empty string. When letterFrequency. Then return correct letter frequency")
    fun givenNonEmptyString_whenLetterFrequency_thenReturnCorrectLetterFrequency() {
        // Given
        val string = "Hello world!"
        // When
        val frequency = letterFrequency(string)
        // Then
        val expectedFrequency = mapOf('H' to 1, 'e' to 1, 'l' to 3, 'o' to 2, 'w' to 1, 'r' to 1, 'd' to 1)
        assertThat(frequency).isEqualTo(expectedFrequency)
    }

    @Test
    @DisplayName("Given an empty string. When letterFrequency. Then return empty letter frequency map")
    fun givenEmptyString_whenLetterFrequency_thenReturnEmptyLetterFrequency() {
        // Given
        val string = ""
        // When
        val frequency = letterFrequency(string)
        // Then
        assertThat(frequency.isEmpty()).isTrue()
    }

    @Test
    @DisplayName("Given a sorted list of numbers. When groupContinuousNumbers. Then return the list of groups of continuous numbers")
    fun givenSortedList_whenGroupContinuousNumbers_thenReturnGroupsOfContinuousNumbers() {
        // Given
        val sortedNumbers = listOf(1, 2, 3, 5, 6, 8, 10, 11)
        // When
        val groups = groupContinuousNumbers(sortedNumbers)
        // Then
        val expectedGroups = listOf(listOf(1, 2, 3), listOf(5, 6), listOf(8), listOf(10, 11))
        assertThat(groups).isEqualTo(expectedGroups)
    }

    @Test
    @DisplayName("Given a sorted list of numbers. When groupContinuousNumbers with a predicate. Then return the list of groups of continuous numbers as defined by the predicate")
    fun givenSortedList_whenGroupContinuousNumbersWithPredicate_thenReturnGroupsOfContinuousNumbers() {
        // Given
        val sortedNumbers = listOf(1, 2, 3, 5, 7, 10, 12, 14, 17)
        // When
        val groups = groupContinuousNumbers(sortedNumbers) { lastNumber, number -> number - lastNumber <= 2 }
        // Then
        val expectedGroups = listOf(listOf(1, 2, 3, 5, 7), listOf(10, 12, 14), listOf(17))
        assertThat(groups).isEqualTo(expectedGroups)
    }

    @Test
    @DisplayName("Given an empty list. When groupContinuousNumbers. Then return an empty list of groups of continuous numbers")
    fun givenEmptyList_whenGroupContinuousNumbers_thenReturnEmptyList() {
        // Given
        val sortedNumbers = listOf<Int>()
        // When
        val groups = groupContinuousNumbers(sortedNumbers)
        // Then
        val expectedGroups = listOf(listOf<Int>())
        assertThat(groups).isEqualTo(expectedGroups)
    }

    @Test
    @DisplayName("Given two non-empty lists. When applyBinaryOperationOverTwoLists. Then return a list of the binary operation application result")
    fun givenTwoNonEmptyLists_whenApplyBinaryOperationOverTwoLists_thenReturnResultingList(){
        // Given
        val list1 = listOf(1, 2, 3, 4)
        val list2 = listOf(10, 20, 30)
        // When
        val result = applyBinaryOperationOverTwoLists(list1, list2, Int::plus)
        // Then
        val expectedResult = listOf(11, 22, 33)
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    @DisplayName("Given tow empty lists. When applyBinaryOperationOverTwoLists. Then return an empty list")
    fun givenTwoEmptyLists_whenApplyBinaryOperationOverTwoLists_thenReturnEmptyList() {
        // Given
        val list1 = listOf<Int>()
        val list2 = listOf<Int>()
        // When
        val result = applyBinaryOperationOverTwoLists(list1, list2, Int::plus)
        // Then
        assertThat(result.isEmpty()).isTrue()
    }

}
