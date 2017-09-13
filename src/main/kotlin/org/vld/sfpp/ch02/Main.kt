package org.vld.sfpp.ch02

fun main(args: Array<String>) {
    val tree = Node(30)
//    tree.insert(20)
//    tree.insert(40)
//    tree.insert(4)
//    tree.insert(400)

    insert(20, tree)
    insert(40, tree)
    insert(4, tree)
    insert(400, tree)
    println(tree)
}
