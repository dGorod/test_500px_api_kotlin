package ua.dgorod.example.data.mapper

import ua.dgorod.example.data.api.dto.PhotoDto
import ua.dgorod.example.domain.Mapper
import ua.dgorod.example.domain.model.Photo

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotoDtoMapper : Mapper<PhotoDto, Photo> {

    override fun map(from: PhotoDto): Photo {
        val images = from.images.associateBy { it.size }.mapValues { it.value.url }

        return Photo(from.id, from.name, from.user.fullname, from.camera, from.lens, from.rating, images)
    }

    override fun map(from: List<PhotoDto>): List<Photo> = from.map { map(it) }
}