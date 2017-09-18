package org.vld.sfpp.ch03

/**
 * Calculates recursively the [size] of the [list]
 */
tailrec
fun <T> listSize(list: List<T>, size: Int = 0): Int =
        // base case: the list is empty, the size of the list is calculated
        if (list.isEmpty()) size
        // recursive case: drop the first element from the list and increment by 1 the size of the list
        else listSize(list.drop(1), size + 1)

/**
 * Retrieves the zero-based [nthElement] from the [list] or returns null if the index is out of range
 */
tailrec
fun <T> listNthElement(list: List<T>, nthElement: Int): T? = when {
    // positive base case: the list is not empty and the Nth element is 0
    list.isNotEmpty() && nthElement == 0 -> list.first()
    // negative base case: the list is empty and the Nth element is out of range
    list.isEmpty() || nthElement >= list.size || nthElement < 0 -> null
    // recursive case: drop the first element from the list and decrement by 1 the Nth element
    else -> listNthElement(list.drop(1), nthElement - 1)
}

fun main(args: Array<String>) {
//    val list = listOf(1, 2, 3, 4)
//    val list = listOf<Int>()
//    val res = listNthElement(list, 4)
//    println(res)

    try {
        val res = Parser.evaluate("1+2+3*4*2")
        println(res)
    } catch (ex: IllegalArgumentException) {
        println("ERROR: ${ex.message}")
    }
}
