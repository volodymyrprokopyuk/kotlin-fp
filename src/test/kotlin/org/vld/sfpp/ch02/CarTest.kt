package org.vld.sfpp.ch02

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarTest {

    @Test
    @DisplayName("Given a valid car. When validate car. Then IllegalArgumentException is not thrown")
    fun givenValidCar_whenValidateCar_thenIllegalArgumentExceptionIsNotThrown() {
        // Given
        val x5 = Car("BMW", "X5", 2016, hasGps = true, hasAc = true)
        // When & Then
        assertThatCode({ x5.apply(Car::validate) }).doesNotThrowAnyException()
    }

    @Test
    @DisplayName("Given an invalid car. When validate car. Then throw IllegalArgumentException")
    fun givenInvalidCar_whenValidateCar_thenThrowIllegalArgumentException() {
        // Given
        val x0 = Car("BMW", "X0", 1984)
        // When & Then
        assertThatThrownBy { x0.apply(Car::validate) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageStartingWith("Car::year should be at least 1990:")
    }

}
