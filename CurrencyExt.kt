import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

/**
 * Formats a `Number` into a currency `String` using the default locale's currency format.
 *
 * This extension function simplifies the process of converting numerical values (like `Int`, `Double`, `Long`)
 * into a human-readable currency representation, for example, formatting `1234.56` to `"$1,234.56"` in the US locale.
 *
 * It handles `null` receivers gracefully by returning an empty string.
 *
 * @return A `String` representing the formatted currency value, or an empty string if the receiver is `null`.
 *
 * @sample
 * val price: Double = 99.99
 * println(price.toCurrency()) // Example output (in US locale): $99.99
 *
 * val total: Int? = 1500
 * println(total.toCurrency()) // Example output (in US locale): $1,500.00
 *
 * val noValue: Double? = null
 * println(noValue.toCurrency()) // Output: ""
 */
fun Number.toCurrency(
    locale: Locale = Locale.getDefault(),
    currency: Currency = Currency.getInstance(locale),
    showSymbol: Boolean = true
): String {
    val format = NumberFormat.getCurrencyInstance(locale).apply {
        this.currency = currency
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    val result = format.format(this)
    return if (showSymbol) result else result.replace(Regex("[^\\d.,-]"), "").trim()
}
