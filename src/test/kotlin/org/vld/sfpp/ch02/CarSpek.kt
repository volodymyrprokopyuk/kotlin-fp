package org.vld.sfpp.ch02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.throws
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object CarSpek : Spek({

    given("a valid Car object") {
        val x5 = Car("BMW", "X5", 2016, hasGps = true, hasAc = true)
        on("apply Car::validate on the valid Car object") {
            it("should not throw IllegalArgumentException") {
                assertThat({ x5.apply(Car::validate) }, !throws<IllegalArgumentException>())
            }
        }
    }

    given("an invalid Car object") {
        val x0 = Car("BMW", "X0", 1984)
        on("apply Car::validate on the invalid Car object") {
            it("should throw IllegalArgumentException") {
                assertThat({ x0.apply(Car::validate) }, throws<IllegalArgumentException>())
            }
        }
    }

})
