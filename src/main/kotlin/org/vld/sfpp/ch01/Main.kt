package org.vld.sfpp.ch01

/**
 * Calculates letter frequency for a string [str]
 */
fun letterFrequency(string: String): Map<Char, Int> = string
        .filter(Char::isLetter)
        .groupBy { it }
        .map { (letter, occurrences) -> letter to occurrences.size }
        .toMap()

/**
 * Given a sorted list of numbers [sortedNumbers], calculates continuous groups of numbers,
 * where each number in a group is higher by one than its predecessor
 */
fun groupContinuousNumbers(sortedNumbers: List<Int>): List<List<Int>> =
        // create list with first empty number group
        sortedNumbers.fold(listOf(emptyList()), { groups, number ->
            val lastGroup = groups.last()
            val initGroups = groups.dropLast(1)
            when {
                // add first number to the first empty number group
                lastGroup.isEmpty() -> initGroups + listOf(lastGroup + number)
                else -> {
                    val lastNumber = lastGroup.last()
                    when (number) {
                        // add next continuous number to the current number group
                        lastNumber + 1 -> initGroups + listOf(lastGroup + number)
                        // create new group of continuous numbers
                        else -> groups + listOf(listOf(number))
                    }
                }
            }
        })

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 5, 6, 8, 10, 11)
    val res = groupContinuousNumbers(numbers)
    println(res)
}
