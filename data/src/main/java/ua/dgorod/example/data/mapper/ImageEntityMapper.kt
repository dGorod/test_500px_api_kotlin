package ua.dgorod.example.data.mapper

import ua.dgorod.example.data.api.dto.ImageDto
import ua.dgorod.example.data.db.entity.ImageEntity
import ua.dgorod.example.domain.Mapper

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class ImageEntityMapper : Mapper<ImageDto, ImageEntity> {

    override fun map(from: ImageDto): ImageEntity {
        return ImageEntity(from.url, from.size, -1L)
    }

    override fun map(from: List<ImageDto>): List<ImageEntity> = from.map { map(it) }
}