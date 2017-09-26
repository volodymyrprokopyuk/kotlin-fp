package org.vld.sfpp.ch09

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MainTest {

    val authorToTitleList = listOf(
            Pair("Dmitry Jemerov", "Kotlin in Action"),
            Pair("Dmitry Jemerov", "Kotlin in Action"),
            Pair("Svetlana Isakova", "Kotlin in Action"),
            Pair("Antonio Leiva", "Kotlin for Android Developers"),
            Pair("Denis Kalinin", "Modern Web Development with Kotlin"),
            Pair("Stephen Samuel", "Programming Kotlin"),
            Pair("Stefan Bocutiu", "Programming Kotlin"),
            Pair("Milos Vasic", "Fundamental Kotlin"),
            Pair("Marcin Moskala", "Android Development with Kotlin"),
            Pair("Igor Wojda", "Android Development with Kotlin")
    )

    @DisplayName("Given a transform operation and a source list. When listFunctor. Then return transformed source list")
    @ParameterizedTest(name = "{1} -> {2}")
    @MethodSource("functorSourceListProvider")
    fun givenTransformAndSource_whenListFunctor_thenReturnTransformedSource(
            transform: (Int) -> Int, source: List<Int>, expectedResult: List<Int>) {
        // Given & When
        val result = listFunctor(transform, source)
        // Then
        assertThat(result).isEqualTo(expectedResult)
    }

    fun functorSourceListProvider(): Stream<Arguments> {
        val transform = { element: Int -> element * 10 }
        return Stream.of(
                Arguments.of(transform, listOf<Int>(), listOf<Int>()),
                Arguments.of(transform, listOf(1, 2, 3, 4), listOf(10, 20, 30, 40))
        )
    }

    @DisplayName("Given a transform operation and a source list. When listMonad. Then return transformed source list")
    @ParameterizedTest(name = "{1} -> {2}")
    @MethodSource("monadSourceListProvider")
    fun givenTransformAndSource_whenListMonad_thenReturnTransformedSource(
            transform: (Int) -> List<Int>, source: List<Int>, expectedResult: List<Int>) {
        // Given & When
        val result = listMonad(transform, source)
        // Then
        assertThat(result).isEqualTo(expectedResult)
    }

    fun monadSourceListProvider(): Stream<Arguments> {
        val transform = { element: Int -> listOf(element * 10) }
        return Stream.of(
                Arguments.of(transform, listOf<Int>(), listOf<Int>()),
                Arguments.of(transform, listOf(1, 2, 3, 4), listOf(10, 20, 30, 40))
        )
    }

    @DisplayName("Given an author to title list. When buildTitleWordToAuthorInvertedIndex. Then return title word to author inverted index")
    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("titleWordSearchProvider")
    fun givenAuthorToTitleList_whenBuildTitleWordToAuthorInvertedIndex_thenReturnTitleWordToAuthorInvertedIndex(
            titleWord: String, expectedResult: Set<String>
    ) {
        // Given & When
        val invertedIndex = buildTitleWordToAuthorInvertedIndex(authorToTitleList)
        val result = invertedIndex[titleWord]
        // Then
        assertThat(result).isEqualTo(expectedResult)
    }

    fun titleWordSearchProvider(): Stream<Arguments> {
        return Stream.of(
                Arguments.of("Kotlin", setOf(
                        "Dmitry Jemerov", "Svetlana Isakova", "Antonio Leiva", "Denis Kalinin", "Stephen Samuel",
                        "Stefan Bocutiu", "Milos Vasic", "Marcin Moskala", "Igor Wojda")),
                Arguments.of("Android", setOf("Antonio Leiva", "Marcin Moskala", "Igor Wojda"))
        )
    }

}
