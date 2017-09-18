package org.vld.sfpp.ch03

/**
 * [ParserResult] contains:
 * - Remaining subexpression to be processed in subsequent calls to the parser methods
 * - Numerical result calculated in the chain of calls to the parser methods till now including the current call
 */
typealias ParserResult = Pair<String, Int>

/**
 * Parser for evaluating arithmetic expressions on integer numbers
 *
 * Supported operations in order of precedence from highest to lowest:
 * 1. (parenthesis)
 * 1. multiplication
 * 1. addition
 */
object Parser {

    private val digitsPattern = Regex("""^\d+""")
    private val parentsPattern = Regex("""^\(""")

    private fun term(expression: String): ParserResult = when {
        digitsPattern.containsMatchIn(expression) -> {
            val digits = digitsPattern.find(expression)?.value.orEmpty()
            val subExpression = expression.drop(digits.length)
            ParserResult(subExpression, digits.toInt())
        }
        parentsPattern.containsMatchIn(expression) -> {
            val (subExpression, sum) = sum(expression.drop(1), 0)
            if (subExpression.startsWith(")")) ParserResult(subExpression.drop(1), sum)
            else throw IllegalArgumentException("Missing ) in $expression")
        }
        else -> throw IllegalArgumentException("Number or (SubExpression) expected. Found: $expression")
    }

    tailrec
    private fun product(expression: String, result: Int): ParserResult {
        val (subExpression, term) = term(expression)
        return if (subExpression.startsWith("*")) product(subExpression.drop(1), result * term)
        else ParserResult(subExpression, result * term)
    }

    tailrec
    private fun sum(expression: String, result: Int): ParserResult {
        // Step 2: proceed with the next priority operation (product) and the initial result set to one
        val (subExpression, product) = product(expression, 1)
        // if the next operation is + proceed with sum operation
        return if (subExpression.startsWith("+")) sum(subExpression.drop(1), result + product)
        // otherwise
        else ParserResult(subExpression, result + product)
    }

    /**
     * Evaluates the [expression] and returns the result
     * Throws IllegalArgumentException on parenthesis mismatch or when the expression has characters out of [-+()0-9]
     */
    fun evaluate(expression: String): Int {
        // Step 1: start with the lowest priority operation (sum) and initial result set to zero
        val (_, sum) = sum(expression, 0)
        return sum
    }

}