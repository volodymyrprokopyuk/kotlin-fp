package org.vld.sfpp.ch02

data class Node<T : Comparable<T>>(val value: T, var left: Node<T>? = null, var right: Node<T>? = null) {

    fun insert(newValue: T) {
        when (value.compareTo(newValue)) {
            1 -> left?.insert(newValue) ?: (left = Node(newValue))
            -1 -> right?.insert(newValue) ?: (right = Node(newValue))
        }
    }
}