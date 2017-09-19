package org.vld.sfpp.ch02

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CurrencyTest {

    @Test
    @DisplayName("Given an amount in EUR. When convert to USD. Then return the equivalent amount in USD")
    fun givenAmountInEur_whenConvertToUsd_thenReturnAmountInUsd() {
        // Given
        val eurAmount = 2.0
        // When
        val usdAmount = CurrencyConverter.convert(eurAmount, Currency.EUR, Currency.USD)
        // Then
        val expectedUsdAmount = eurAmount * CurrencyConverter.EuroConverter.conversionRateToDollar
        assertThat(usdAmount).isEqualTo(expectedUsdAmount)
    }

    @Test
    @DisplayName("Given an amount in unknown currency (UNK). When convert to EUR. Then throw IllegalArgumentException")
    fun givenAmountInUnk_whenConvertToEur_thenThrowIllegalArgumentException() {
        // Given
        val unkAmount = 2.0
        // When & Then
        assertThatThrownBy({ CurrencyConverter.convert(unkAmount, Currency.UNK, Currency.EUR) })
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Conversion rate from UNK to EUR is not defined")
    }
}