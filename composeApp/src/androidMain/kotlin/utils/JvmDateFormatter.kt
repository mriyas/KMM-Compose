// jvmMain/src/jvmMain/kotlin/JvmDateFormatter.kt
import utils.DateFormatter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class JvmDateFormatter : DateFormatter {
    override fun String.toFormattedDateString(): String {
        // Define the input and output date formats
        val inputFormatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a")

        // Parse the input string to a ZonedDateTime object
        val zonedDateTime = ZonedDateTime.parse(this, inputFormatter)

        // Format the ZonedDateTime object to the desired output format
        return zonedDateTime.format(outputFormatter)
    }
}
