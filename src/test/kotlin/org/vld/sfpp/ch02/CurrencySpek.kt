package org.vld.sfpp.ch02

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.throws
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object CurrencySpek : Spek({

    given("an amount in EUR") {
        val eurAmount = 2.0
        on("call CurrencyConverter.convert to USD") {
            val usdAmount = CurrencyConverter.convert(eurAmount, Currency.EUR, Currency.USD)
            it("should return amount in UDS") {
                val expectedUsdAmount = eurAmount * CurrencyConverter.EuroConverter.conversionRateToDollar
                assertThat(expectedUsdAmount, equalTo(usdAmount))
            }
        }
    }

    given("an amount in an unknown currency (UNK)") {
        val unkAmount = 2.0
        on("call CurrencyConverter.convert to EUR") {
            it("should throw IllegalArgumentException") {
                assertThat({ CurrencyConverter.convert(unkAmount, Currency.UNK, Currency.EUR) },
                        throws<IllegalArgumentException>())
            }
        }
    }

})
