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
 * 1. Parenthesis: ()
 * 1. Multiplication: *
 * 1. Addition: +
 */
object Parser {

    private val digitsPattern = Regex("""^\d+""")
    private val parentsPattern = Regex("""^\(""")

    private fun term(expression: String): ParserResult = when {
        // DIGIT TERM: parse digits and return numerical value (base case)
        digitsPattern.containsMatchIn(expression) -> {
            val digits = digitsPattern.find(expression)?.value.orEmpty()
            val subExpression = expression.drop(digits.length)
            ParserResult(subExpression, digits.toInt())
        }
        // OPEN PARENTHESIS TERM: drop the opening parenthesis and start with the lowest priority operation + (recursive case)
        parentsPattern.containsMatchIn(expression) -> {
            val (subExpression, sum) = sum(expression.drop(1), 0)
            // drop the closing parenthesis and return the parenthesis expression evaluation result
            if (subExpression.startsWith(")")) ParserResult(subExpression.drop(1), sum)
            // throw IllegalArgumentException if closing parenthesis is missing
            else throw IllegalArgumentException("Missing ) in $expression")
        }
        // throw IllegalArgumentException if the term doesn't start with a number or an opening parenthesis
        else -> throw IllegalArgumentException("Number or (Subexpression) expected. Found: $expression")
    }

    tailrec
    private fun product(expression: String, result: Int): ParserResult {
        // Step 3: finally recursively proceed with the highest priority operation (parenthesis/grouping or number) to evaluate the result in parenthesis
        val (subExpression, term) = term(expression)
        // proceed with MULTIPLICATION if the next operation is *
        return if (subExpression.startsWith("*")) product(subExpression.drop(1), result * term)
        // otherwise return PRODUCT result
        else ParserResult(subExpression, result * term)
    }

    tailrec
    private fun sum(expression: String, result: Int): ParserResult {
        // Step 2: then recursively proceed with higher priority operation * and set the initial result to 1 for multiplication
        val (subExpression, product) = product(expression, 1)
        // proceed with ADDITION if the next operation is +
        return if (subExpression.startsWith("+")) sum(subExpression.drop(1), result + product)
        // otherwise return SUM result
        else ParserResult(subExpression, result + product)
    }

    /**
     * Evaluates the [expression] and returns the result
     *
     * Throws IllegalArgumentException on empty expression, parenthesis mismatch or
     * when the expression has characters out of [-+()0-9] range
     */
    fun evaluate(expression: String): Int {
        // Step 1: start with the lowest priority operation + and set the INITIAL RESULT to 0 for addition
        val (subExpression, sum) = sum(expression, 0)
        // throw IllegalArgumentException if expression has additional terms
        if (subExpression.isNotEmpty()) throw IllegalArgumentException("Expression has additional terms: $subExpression")
        // return the final expression evaluation result after recursively evaluating all the subexpressions
        return sum
    }

}