import java.util.regex.Pattern

/**
 * Capitalises the first letter of a String.
 * @return A new String with its first character converted to title case.
 */
fun String.capitalizeFirst(): String = replaceFirstChar { it.titlecase() }

/**
 * Converts a string to title case.
 * @return A new String with the first letter of each word capitalised.
 */
fun String.toTitleCase(): String = split(" ").joinToString(" ") { it.capitalizeFirst() }

/**
 * Removes all whitespace from a string.
 * @return A new String with all whitespace removed.
 */
fun String.removeWhitespaces(): String = replace("\\s".toRegex(), "")

/**
 * Removes all special characters from a string.
 * @return A new String with all special characters removed.
 */
fun String.removeSpecialChars(): String = replace("[^A-Za-z0-9 ]".toRegex(), "")

/**
 * Counts the number of words in a string.
 * @return The number of words in the string.
 */
fun String.wordCount(): Int = trim().split("\\s+".toRegex()).count { it.isNotEmpty() }

/**
 * Returns the total number of characters in a string.
 * @return The length of the string.
 */
fun String.totalChars(): Int = length

/**
 * Masks a portion of a string with a given character.
 * @param start The starting index of the mask (inclusive).
 * @param end The ending index of the mask (exclusive).
 * @return A new String with the specified portion masked.
 */
fun String.mask(
    start: Int = 0,
    end: Int = length
): String = mapIndexed { i, c ->
    if (i in start until end) '*' else c
}.joinToString("")

typealias Validate = (String) -> Boolean

/**
 * A `Validate` typealias that checks if a string is a well-formed email address.
 *
 * This lambda compiles a regular expression for email validation and checks if the input string matches it.
 * The regex checks for a standard email format: `username@domain.tld`.
 *
 * It is used by the `String.isEmail()` extension function.
 */
private val verifyEmail: Validate =
    { Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}").matcher(it).matches() }

/**
 * A validation function that checks if a string is a valid phone number.
 * It uses a regular expression to match common phone number formats,
 * including an optional leading '+' and allowing digits, spaces, parentheses, dots, and hyphens.
 * The length is expected to be between 7 and 25 characters.
 *
 * @param String The string to validate.
 * @return True if the string is a valid phone number, false otherwise.
 */
private val verifyPhoneNumber: Validate =
    { Pattern.compile("^\\+?[0-9. ()-]{7,25}$").matcher(it).matches() }

/**
 * Checks if a string is a valid email address.
 * @return True if the string is a valid email address, false otherwise.
 */
fun String.isEmail(): Boolean = verifyEmail(this)

/**
 * Checks if a string is a valid phone number.
 * @return True if the string is a valid phone number, false otherwise.
 */
fun String.isPhoneNumber(): Boolean = verifyPhoneNumber(this)
