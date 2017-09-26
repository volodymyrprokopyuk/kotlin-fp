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

}
