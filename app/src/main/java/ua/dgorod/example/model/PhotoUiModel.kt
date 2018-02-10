package ua.dgorod.example.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
@Parcelize
data class PhotoUiModel(
    val id: Long,
    val name: String,
    val author: String,
    val camera: String = "",
    val lens: String = "",
    val images: Map<Int, String> = emptyMap()
) : Parcelable {

    fun hasDeviceInfo() = camera.isNotEmpty() || lens.isNotEmpty()
}