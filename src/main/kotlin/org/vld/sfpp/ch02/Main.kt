package org.vld.sfpp.ch02

fun main(args: Array<String>) {
    val tree = Node(72)
    tree.insert(50)
    tree.insert(25)
    tree.insert(65)
    tree.insert(95)
    tree.insert(88)
    tree.insert(112)
    println(tree)
    val res = tree.traverse()
    println(res)
}
