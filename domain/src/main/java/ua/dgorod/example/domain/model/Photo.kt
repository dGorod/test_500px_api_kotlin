package ua.dgorod.example.domain.model

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
data class Photo(
    val id:Long,
    val name: String,
    val author: String,
    val camera: String?,
    val lens: String?,
    val rating: Double,
    val images: Map<Int, String> = emptyMap()
)