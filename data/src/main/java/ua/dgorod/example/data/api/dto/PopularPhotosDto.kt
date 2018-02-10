package ua.dgorod.example.data.api.dto

import com.google.gson.annotations.SerializedName

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PopularPhotosDto {

    @SerializedName("current_page")
    val currentPage = 0

    @SerializedName("total_pages")
    val totalPages = 0

    @SerializedName("total_items")
    val totalItems = 0

    val photos: List<PhotoDto> = emptyList()
}