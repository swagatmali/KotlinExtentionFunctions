/**
 * Returns this value if it is not null, or the [default] value otherwise.
 * This is a shorthand for the elvis operator (`?:`).
 *
 * @param default The value to return if the original value is null.
 * @return The original value if it is not null, otherwise the [default] value.
 */
fun <T> T?.or(default: T): T = this ?: default
inline fun <T, R> T?.mapOr(default: R, block: (T) -> R?): R {
    return this?.let { block(it) }.or(default)
}


/**
 * Executes the given [block] with two non-nullable values if both input parameters are not null.
 * This is similar to a Swift `if let` statement for two optional values.
 *
 * @param T1 The type of the first parameter.
 * @param T2 The type of the second parameter.
 * @param R The return type of the [block].
 * @param p1 The first nullable parameter.
 * @param p2 The second nullable parameter.
 * @param block The lambda to execute if both [p1] and [p2] are not null.
 *              It receives the non-nullable values of [p1] and [p2] as arguments.
 * @return The result of the [block] if both parameters were non-null, otherwise `null`.
 */
inline fun <T1, T2, R> ifLet(p1: T1?, p2: T2?, block: (T1, T2) -> R): R? {
    return if (p1 != null && p2 != null) {
        block(p1, p2)
    } else null
}
