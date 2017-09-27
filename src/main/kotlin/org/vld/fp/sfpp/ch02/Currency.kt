package org.vld.fp.sfpp.ch02

/**
 * Supported currencies
 */
enum class Currency {
    EUR, USD, GBP, UNK
}

/**
 * Conversion rates from each currency to any other supported currency
 *
 * [ConversionRate] interface is implemented by every currency converter singleton object
 */
interface ConversionRate {
    val conversionRateToEuro: Double
    val conversionRateToDollar: Double
    val conversionRateToPound: Double
}

/**
 * Currency converter singleton object for converting amounts from one currency to another
 */
object CurrencyConverter {

    /**
     * Singleton [ConversionRate] implementation for [Currency.EUR]
     */
    object EuroConverter : ConversionRate {
        override val conversionRateToEuro: Double
            get() = 1.0
        override val conversionRateToDollar: Double
            get() = 1.194
        override val conversionRateToPound: Double
            get() = 0.879
    }

    /**
     * Singleton [ConversionRate] implementation for [Currency.USD]
     */
    object DollarConverter : ConversionRate {
        override val conversionRateToEuro: Double
            get() = 0.838
        override val conversionRateToDollar: Double
            get() = 1.0
        override val conversionRateToPound: Double
            get() = 0.736
    }

    /**
     * Singleton [ConversionRate] implementation for [Currency.GBP]
     */
    object PoundConverter : ConversionRate {
        override val conversionRateToEuro: Double
            get() = 1.138
        override val conversionRateToDollar: Double
            get() = 1.359
        override val conversionRateToPound: Double
            get() = 1.0
    }

    /**
     * Converts [amount] from [fromCurrency] to [toCurrency]
     * Throws IllegalArgumentException for not supported currencies
     */
    fun convert(amount: Double, fromCurrency: Currency, toCurrency: Currency): Double = when (fromCurrency to toCurrency) {
        // convert from EUR
        Currency.EUR to Currency.EUR -> EuroConverter.conversionRateToEuro
        Currency.EUR to Currency.USD -> EuroConverter.conversionRateToDollar
        Currency.EUR to Currency.GBP -> EuroConverter.conversionRateToPound

        // convert from USD
        Currency.USD to Currency.EUR -> DollarConverter.conversionRateToEuro
        Currency.USD to Currency.USD -> DollarConverter.conversionRateToDollar
        Currency.USD to Currency.GBP -> DollarConverter.conversionRateToPound

        // convert from GBP
        Currency.GBP to Currency.EUR -> PoundConverter.conversionRateToEuro
        Currency.GBP to Currency.USD -> PoundConverter.conversionRateToDollar
        Currency.GBP to Currency.GBP -> PoundConverter.conversionRateToPound

        // throw IllegalArgumentException for not supported currencies
        else -> throw IllegalArgumentException("Conversion rate from $fromCurrency to $toCurrency is not defined")
    } * amount

}
