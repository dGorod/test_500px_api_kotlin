package ua.dgorod.example.mapper

import ua.dgorod.example.domain.Mapper
import ua.dgorod.example.domain.model.Photo
import ua.dgorod.example.model.PhotoUiModel

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class PhotoUiMapper : Mapper<Photo, PhotoUiModel> {

    override fun map(from: Photo): PhotoUiModel {
        with(from) {
            return PhotoUiModel(id, name, author, camera ?: "", lens ?: "", images)
        }
    }

    override fun map(from: List<Photo>): List<PhotoUiModel> = from.map { map(it) }
}