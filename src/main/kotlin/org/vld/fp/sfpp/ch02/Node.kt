package org.vld.fp.sfpp.ch02

/**
 * Node of a Binary Search Tree (BST) with the node [value], [left] and [right] subtree
 *
 * All node values in the left subtree are lower than the node value.
 * All node values in the right subtree are greater than the node value.
 * The node value type should be Comparable<T>.
 * If the node is a leaf, its left and right subtree are null.
 */
data class Node<T : Comparable<T>>(
        val value: T,
        var left: Node<T>? = null,
        var right: Node<T>? = null
) {

    /**
     * Inserts a [newValue] intro the BST where the root is the current node
     */
    fun insert(newValue: T) {
        when (newValue.compareTo(value)) {
            // when the new value is lower then the current node value, insert the new value into the left subtree
            -1 -> left?.insert(newValue) ?: { left = Node(newValue) }()
            // when the new value is greater then the current node value, insert the new value into the right subtree
            1 -> right?.insert(newValue) ?: { right = Node(newValue) }()
        }
    }

    /**
     * Traverses in ascending order the BST where the root is the current node.
     * Returns an ordered list of BST [values]
     */
    fun traverse(values: MutableList<T> = mutableListOf()): List<T> {
        // first travers the left subtree and collect all its values in ascending order
        left?.traverse(values)
        // then add the current node value to the resulting list of values
        values.add(value)
        // finally travers the right subtree and collect all its values in ascending order
        right?.traverse(values)
        return values
    }

}
