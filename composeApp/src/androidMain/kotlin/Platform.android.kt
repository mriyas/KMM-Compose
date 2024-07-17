import utils.DateFormatter

actual object Platform {
    actual val dateFormatter: DateFormatter = JvmDateFormatter()
}