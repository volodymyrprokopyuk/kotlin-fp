package org.vld.sfpp.ch01

/**
 * Calculates letter frequency for a string [str] with letters and other symbols
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
        sortedNumbers.fold(listOf(emptyList()), { groups, number ->
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

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 5, 6, 8, 10, 11)
    val res = groupContinuousNumbers(numbers)
    println(res)
}
