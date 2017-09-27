package org.vld.fp.sfpp.ch01

/**
 * Calculates letter frequency for a string [string] with letters and other symbols
 */
fun letterFrequency(string: String): Map<Char, Int> = string
        .filter(Char::isLetter)
        .groupBy { it }
        .map { (letter, occurrences) -> letter to occurrences.size }
        .toMap()

/**
 * Given a sorted list of numbers [sortedNumbers], calculates continuous groups of numbers,
 * where each number in a group is next to its predecessor as defined by optional [areContinuous] predicate
 */
fun groupContinuousNumbers(
        sortedNumbers: List<Int>,
        areContinuous: (lastNumber: Int, number: Int) -> Boolean = { lastNumber, number -> number - lastNumber == 1 }
): List<List<Int>> =
        // create a list with first empty number group
        sortedNumbers.fold(listOf(listOf()), { groups, number ->
            val lastGroup = groups.last()
            val initGroups = groups.dropLast(1)
            // add first number to the first empty number group
            if (lastGroup.isEmpty()) initGroups + listOf(lastGroup + number)
            else {
                val lastNumber = lastGroup.last()
                // add next continuous number to the current number group
                if (areContinuous(lastNumber, number)) initGroups + listOf(lastGroup + number)
                // create a new group of continuous numbers
                else groups + listOf(listOf(number))
            }
        })

/**
 * Given two lists [list1] and [list2] of items of the same size, maps the items from both lists
 * over a binary [binaryOperation] and returns a list of the binary operation results
 */
fun <T1, T2, R> applyBinaryOperationOverTwoLists(
        list1: List<T1>,
        list2: List<T2>,
        binaryOperation: (left: T1, right: T2) -> R
): List<R> = list1.zip(list2).map { (left, right) -> binaryOperation(left, right) }

fun main(args: Array<String>) {
    val list1 = listOf(1, 2, 3, 4)
    val list2 = listOf(10, 20, 30)
    val res = applyBinaryOperationOverTwoLists(list1, list2, Int::plus)
    println(res)
}
