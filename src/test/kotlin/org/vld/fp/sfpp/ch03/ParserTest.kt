package org.vld.fp.sfpp.ch03

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParserTest {

    @DisplayName("Given a valid expression. When evaluate expression. Then return the result")
    @ParameterizedTest(name = "{0} = {1}")
    @MethodSource("validExpressionProvider")
    fun givenValidExpression_whenEvaluateExpression_thenReturnResult(expression: String, expectedResult: Int) {
        // Given & When
        val result = Parser.evaluate(expression)
        // Then
        assertThat(result).isEqualTo(expectedResult)
    }

    fun validExpressionProvider(): Stream<Arguments> {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("1+2", 3),
                Arguments.of("1+2+3", 6),
                Arguments.of("1*2", 2),
                Arguments.of("1*2*3", 6),
                Arguments.of("(1)", 1),
                Arguments.of("((2))", 2),
                Arguments.of("(1+2)", 3),
                Arguments.of("(1+2+3)", 6),
                Arguments.of("(1*2)", 2),
                Arguments.of("(1*2*3)", 6),
                Arguments.of("(1+2)*3", 9),
                Arguments.of("(1+2)*3+1", 10),
                Arguments.of("(1+2)*3+1*2", 11),
                Arguments.of("(1+2)*3+1*2+3", 14),
                Arguments.of("(1+2)*3+4*(5+6)", 53),
                Arguments.of("(1+(2*3))", 7),
                Arguments.of("(1+(2*(3+4)))", 15)
        )
    }

    @DisplayName("Given an invalid expression. When evaluate expression. Then throw IllegalArgumentException")
    @ParameterizedTest(name = "{0} throws IllegalArgumentException")
    @MethodSource("invalidExpressionProvider")
    fun givenInvalidExpression_whenEvaluateExpression_thenThrowIllegalArgumentException(expression: String) {
        // Given & When & Then
        assertThatThrownBy { Parser.evaluate(expression) }
                .isInstanceOf(IllegalArgumentException::class.java)
    }

    fun invalidExpressionProvider(): Stream<Arguments> {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("x"),
                Arguments.of(")"),
                Arguments.of("()"),
                Arguments.of("((1)"),
                Arguments.of("(1))"),
                Arguments.of("(1)x"),
                Arguments.of("(x1)"),
                Arguments.of("(1x)"),
                Arguments.of("(1+((2+3)"),
                Arguments.of("(1+(2+3)))"),
                Arguments.of("(1+())")
        )
    }

}
