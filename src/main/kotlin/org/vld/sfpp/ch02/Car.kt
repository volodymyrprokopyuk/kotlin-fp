package org.vld.sfpp.ch02

data class Car(val make: String, val model: String, val year: Int, val hasGps: Boolean = false, val hasAc: Boolean = false)

fun Car.validate() {
    if (make.isEmpty()) throw IllegalArgumentException("Car::make shouldn't be empty: $this")
    if (model.isEmpty()) throw IllegalArgumentException("Car::model shouldn't be empty: $this")
    if (year < 1990) throw IllegalArgumentException("Car::year should be at least 1990: $this")
}
