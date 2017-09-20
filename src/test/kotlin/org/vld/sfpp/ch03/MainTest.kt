package org.vld.sfpp.ch03

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    @Test
    @DisplayName("Given a non-empty list. When listSize. Then return the size of the list")
    fun givenNonEmptyList_whenListSize_thenReturnListSize() {
        // Given
        val list = listOf(1, 2, 3, 4)
        // When
        val size = listSize(list)
        // Then
        val expectedSize = list.size
        assertThat(size).isEqualTo(expectedSize)
    }

    @Test
    @DisplayName("Given an empty list. When listSize. Then return 0")
    fun givenEmptyList_whenListSize_thenReturnZero() {
        // Given
        val list = listOf<Int>()
        // When
        val size = listSize(list)
        // Then
        val expectedSize = list.size
        assertThat(size).isEqualTo(expectedSize)
    }

    @Test
    @DisplayName("Given a large list. When listSize. Then return the size of the large list")
    fun givenLargeList_whenListSize_thenReturnListSize() {
        // Given
        val list = (0..100).toList()
        // When
        val size = listSize(list)
        // Then
        val expectedSize = list.size
        assertThat(size).isEqualTo(expectedSize)
    }

    @Test
    @DisplayName("Given a non-empty list. When listNthElement with an index withing the range. Then return Nth element")
    fun givenNonEmptyList_whenListNthElementWithIndexWithinRange_thenReturnNthElement() {
        // Given
        val list = listOf(1, 2, 3, 4)
        // When
        val element = listNthElement(list, 1)
        // Then
        val expectedElement = list[1]
        assertThat(element).isEqualTo(expectedElement)
    }

    @Test
    @DisplayName("Given a non-empty list. When listNthElement with an index out of the range. Then return null")
    fun givenNonEmptyList_whenListNthElementWithIndexOutOfRange_thenReturnNull() {
        // Given
        val list = listOf(1, 2, 3, 4)
        // When
        val element = listNthElement(list, 10)
        // Then
        assertThat(element).isNull()
    }

    @Test
    @DisplayName("Given an empty list. When listNthElement. Then return null")
    fun givenEmptyList_whenListNthElement_thenReturnNull() {
        // Given
        val list = listOf<Int>()
        // When
        val element = listNthElement(list, 1)
        // Then
        assertThat(element).isNull()
    }

}