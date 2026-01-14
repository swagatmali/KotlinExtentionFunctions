/**
 * Returns this list if it's not `null`, or an empty list otherwise.
 *
 * This is a convenience function that provides a non-nullable `List` to work with,
 * avoiding the need for explicit null-checks.
 *
 * @return The original list if it is not null, or a new read-only empty list if the original list is null.
 * @see kotlin.collections.orEmpty
 */
fun <T> List<T>?.orEmptyList(): List<T> = this ?: emptyList()

/**
 * Returns this map if it's not `null`, or an empty map otherwise.
 *
 * @return the original map or an empty map if the original map was `null`.
 */
fun <K, V> Map<K, V>?.orEmptyMap(): Map<K, V> = this ?: emptyMap()

/**
 * Returns this set if it's not `null`, or an empty read-only set otherwise.
 *
 * @return the original set if it is not null, or [emptySet] if it is null.
 */
fun <T> Set<T>?.orEmptySet(): Set<T> = this ?: emptySet()

/**
 * Executes the given [block] only if the list is not null and not empty.
 * The list itself is passed as a parameter to the [block].
 *
 * @param block The code block to execute with this list as its argument.
 */
inline fun <T> List<T>?.whenNotEmpty(block: (List<T>) -> Unit) {
    if (!this.isNullOrEmpty()) block(this)
}

/**
 * Returns the element at the specified [index] from the list, or a [default] value if the list is null
 * or the index is out of bounds. This is a safe way to access an element from a nullable list without
 * risking a NullPointerException or an IndexOutOfBoundsException.
 *
 * @param T The generic type of the list's elements.
 * @param index The index of the element to retrieve.
 * @param default The value to return if the list is null or the index is out of bounds.
 * @return The element at the specified index, or the default value if the access is unsafe.
 */
fun <T> List<T>?.safeGetOrElse(index: Int, default: T): T {
    return if (this != null && index in indices) this[index] else default
}

/**
 * Maps the list to a new list using the provided [transform] function only if the original list is not null and not empty.
 * If the list is null or empty, it returns an empty list.
 *
 * This is useful for chaining operations on a nullable list where you only want to perform a mapping
 * if there are elements to process.
 *
 * @param T the type of elements in the source list.
 * @param R the type of elements in the resulting list.
 * @param transform the function to apply to each element of the list.
 * @return a new list containing the results of applying the [transform] function to each element of the original list,
 * or an empty list if the original list is null or empty.
 *
 * @sample
 * val numbers: List<Int>? = listOf(1, 2, 3)
 * val squared = numbers.mapIfNotEmpty { it * it } // Result: [1, 4, 9]
 *
 * val emptyNumbers: List<Int>? = null
 * val squaredEmpty = emptyNumbers.mapIfNotEmpty { it * it } // Result: []
 */
inline fun <T, R> List<T>?.mapIfNotEmpty(transform: (T) -> R): List<R> =
    if (isNullOrEmpty()) emptyList() else map(transform)
