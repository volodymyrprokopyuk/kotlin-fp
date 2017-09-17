package org.vld.sfpp.ch03

/**
 * Calculates recursively the [size] of the [list]
 */
tailrec
fun <T> listSize(list: List<T>, size: Int = 0): Int = if (list.isEmpty()) size else listSize(list.drop(1), size + 1)
