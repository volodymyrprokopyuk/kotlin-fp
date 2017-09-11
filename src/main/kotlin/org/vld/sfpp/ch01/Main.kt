package org.vld.sfpp.ch01

/**
 * Calculates letter frequency for a string [str]
 */
fun letterFrequency(str: String): Map<Char, Int> = str
        .filter(Char::isLetter)
        .groupBy { it }
        .map { (letter, occurrences) -> letter to occurrences.size }
        .toMap()

/**
 * Given a sorted list of numbers [nums], calculates continuous groups of numbers,
 * so each number in a group is higher by on than its predecessor
 */
fun groupContinuousNums(nums: List<Number>) {

}

fun main(args: Array<String>) {
    val res = letterFrequency("hello world!")
    println(res)
}
