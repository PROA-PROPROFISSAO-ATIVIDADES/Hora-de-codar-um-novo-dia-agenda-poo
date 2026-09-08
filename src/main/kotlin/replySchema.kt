data class Reply<T>(
    val status: Int,
    val message: String,
    val item: T? = null
)