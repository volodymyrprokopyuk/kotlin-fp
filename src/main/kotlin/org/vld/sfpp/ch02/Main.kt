package org.vld.sfpp.ch02

fun main(args: Array<String>) {
    try {
        val x5 = Car("BMW", "X5", 2016, hasGps = true, hasAc = true).apply(Car::validate)
        println(x5)
        val x0 = Car("BMW", "X0", 1984).apply(Car::validate)
        println(x0)
    } catch (ex: IllegalArgumentException) {
        println("ERROR: ${ex.message}")
    }
}
