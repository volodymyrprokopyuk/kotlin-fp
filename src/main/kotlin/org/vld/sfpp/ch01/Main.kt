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
        sortedNumbers.fold(mutableListOf<List<Int>>(listOf<Int>())) { groupedNumbers, number ->
            val lastGroup = groupedNumbers.last().toMutableList()
            when {
                lastGroup.isEmpty() -> {
                    lastGroup.add(number); groupedNumbers[groupedNumbers.lastIndex] = lastGroup
                }
                else -> {
                    val lastNumber = lastGroup.last()
                    when (number) {
                        lastNumber + 1 -> {
                            lastGroup.add(number); groupedNumbers[groupedNumbers.lastIndex] = lastGroup
                        }
                        else -> {
                            val newGroup = mutableListOf(number)
                            groupedNumbers.add(newGroup)
                        }
                    }
                }
            }
            groupedNumbers
        }


fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3, 5, 6, 8, 10, 11)
    val res = groupContinuousNumbers(numbers)
    println(res)
}
