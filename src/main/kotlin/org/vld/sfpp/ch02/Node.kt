package org.vld.sfpp.ch02

data class Node<T : Comparable<T>>(val value: T, var left: Node<T>? = null, var right: Node<T>? = null) {

    fun insertX(newValue: T) {
        when (value.compareTo(newValue)) {
            1 -> left?.insertX(newValue) ?: insertLeft(newValue)
            -1 -> right?.insertX(newValue) ?: insertRight(newValue)
        }
    }

    fun insertLeft(newValue: T) {
        left = Node(newValue)
    }

    fun insertRight(newValue: T) {
        right = Node(newValue)
    }

    fun insertY(newValue: T) {
        if (newValue < value && left == null) left = Node(newValue)
        if (newValue > value && right == null) right = Node(newValue)
        if (newValue < value) left?.insertY(newValue)
        if (newValue > value) right?.insertY(newValue)
    }
}

fun <T : Comparable<T>> insert(newValue: T, tree: Node<T>?): Node<T>? {
    if (newValue < tree?.value && tree?.left == null) {
        tree?.left = Node(newValue)
        return tree;
    }
    if (newValue > tree.value && tree?.right == null) {
        tree?.right = Node(newValue)
        return tree;
    }
    if (newValue < tree.value) {
        return insert(newValue, tree.left)
    }
    if (newValue > tree.value) {
        return insert(newValue, tree.right)
    }
}

