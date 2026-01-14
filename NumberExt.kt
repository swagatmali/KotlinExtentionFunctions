/**
 * Returns this value if it is not null, or the [default] value otherwise.
 * This is a concise alternative to the Elvis operator (`?:`) for chained calls.
 *
 * @param default The value to return if the original value is null.
 * @return The original value if not null, otherwise the [default] value.
 */
fun <T> T?.or(default: T): T = this ?: default

/**
 * Returns this [Int] if it's not `null`, or `0f` otherwise.
 */
fun Int?.orZero(): Int = or(0)

/**
 * Returns this [Float] if it's not `null`, or `0f` otherwise.
 */
fun Float?.orZero(): Float = or(0f)

/**
 * Returns this [Double] if it's not `null`, or `0f` otherwise.
 */
fun Double?.orZero(): Double = or(0.0)

/**
 * Returns this [Long] if it's not `null`, or `0f` otherwise.
 */
fun Long?.orZero(): Long = or(0L)

/**
 * Calculates the percentage of this [Int] value with respect to a given [total].
 *
 * This extension function safely handles division by zero by returning `0f` if the [total] is `0`.
 * Otherwise, it calculates `(this / total) * 100`.
 *
 * @param total The total value to calculate the percentage against.
 * @return The calculated percentage as a [Float], or `0f` if [total] is `0`.
 */
fun Int.percentageOf(total: Int): Float = 0f.takeIf { total == 0 }.or((toFloat() / total) * 100)

/**
 * Checks if this [Int] is within a specified range (inclusive).
 * If it is, the original value is returned. Otherwise, a [default] value is returned.
 *
 * @param min The minimum value of the range (inclusive).
 * @param max The maximum value of the range (inclusive).
 * @param default The value to return if this [Int] is not within the [min]..[max] range.
 * @return This [Int] if it is between [min] and [max], otherwise returns [default].
 */
fun Int.inRageOr(min: Int, max: Int, default: Int): Int = if (this in min..max) this else default

/**
 * Formats a [Number] to a [String] with a specified number of decimal places.
 *
 * @param digits The number of decimal places to include in the formatted string. Defaults to 2.
 * @return A [String] representation of the number formatted to the given number of decimal places.
 * @receiver The [Number] to be formatted.
 */
fun Number.formatDecimal(digits: Int = 2) = "%.${digits}f".format(toDouble())
