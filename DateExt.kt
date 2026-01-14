import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Formats the [LocalDate] to a [String] representation.
 *
 * @param pattern The pattern to format the date with. Defaults to "dd/MM/yyyy".
 * @return The formatted date string.
 * @see DateTimeFormatter
 */
fun LocalDate.toFormattedString(pattern: String = "dd/MM/yyyy") =
    format(DateTimeFormatter.ofPattern(pattern))

/**
 * Converts a [LocalDateTime] instance to a human-readable, relative time string.
 *
 * This extension function calculates the duration between the given [LocalDateTime] and the current time (`LocalDateTime.now()`)
 * and formats it into a user-friendly string like "Just now", "5 minutes ago", "3 hours ago", or "2 days ago".
 *
 * - If the duration is less than a minute, it returns "Just now".
 * - If the duration is less than an hour, it returns the time in minutes (e.g., "45 minutes ago").
 * - If the duration is less than 24 hours, it returns the time in hours (e.g., "14 hours ago").
 * - Otherwise, it returns the time in days (e.g., "3 days ago").
 *
 * @return A [String] representing the relative time from now.
 * @receiver [LocalDateTime] The date-time instance to compare against the current time.
 */
fun LocalDateTime.toRelativeTime(): String {
    val duration = Duration.between(this, LocalDateTime.now())
    return when {
        duration.toMinutes() < 1 -> "Just now"
        duration.toHours() < 1 -> "${duration.toMinutes()} minutes ago"
        duration.toHours() < 24
            -> "${duration.toHours()} hours ago"

        else -> "${duration.toDays()} days ago"
    }
}

/**
 * Formats the [LocalDate] into a human-friendly, relative string.
 *
 * This extension function provides a more readable representation of a date relative to a given `currentDate`.
 * - If the date is the same as `currentDate`, it returns "Today".
 * - If the date is the day before `currentDate`, it returns "Yesterday".
 * - If the date is the day after `currentDate`, it returns "Tomorrow".
 * - Otherwise, it formats the date into the "dd MMM yyyy" pattern (e.g., "25 Dec 2023").
 *
 * @param currentDate The date to compare against. Defaults to the current system date.
 * @return A human-friendly [String] representation of the date.
 */
fun LocalDate.humanFriendly(currentDate: LocalDate = LocalDate.now()) = when (this) {
    currentDate -> "Today"
    currentDate.minusDays(1) -> "Yesterday"
    currentDate.plusDays(1) -> "Tomorrow"
    else -> this.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
}


/**
 * Returns the suffix for a given day of the month (e.g., "st", "nd", "rd", "th").
 *
 * This function determines the correct ordinal suffix for any given integer representing
 * the day of the month.
 *
 * - For days ending in 1 (but not 11), it returns "st".
 * - For days ending in 2 (but not 12), it returns "nd".
 * - For days ending in 3 (but not 13), it returns "rd".
 * - For all other days, it returns "th".
 *
 * @param n The day of the month (an integer).
 * @return The corresponding ordinal suffix as a [String].
 *
 * @sample
 * getDay0fMonthSuffix(1) // returns "st"
 * getDay0fMonthSuffix(2) // returns "nd"
 * getDay0f-MonthSuffix(11) // returns "th"
 * getDay0fMonthSuffix(23) // returns "rd"
 */
fun Int.getDay0fMonthSuffix(): String {
    if (this !in 1..31) throw IllegalArgumentException("Invalid day: $this")
    return when {
        this in 11..13 -> "th"
        this % 10 == 1 -> "st"
        this % 10 == 2 -> "nd"
        this % 10 == 3 -> "rd"
        else -> "th"
    }
}
