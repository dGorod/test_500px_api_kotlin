package ua.dgorod.example.data.api.dto

import com.google.gson.annotations.SerializedName

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotoDto {

    val id = -1L
    val name: String = ""
    val camera: String? = null
    val lens: String? = null
    val rating: Double = 0.0
    val user: UserDto = UserDto()
    val images: List<ImageDto> = emptyList()

    @SerializedName("highest_rating_date")
    val highestRatingDate: String? = null
}