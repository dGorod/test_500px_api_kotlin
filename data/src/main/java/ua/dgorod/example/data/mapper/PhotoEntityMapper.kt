package ua.dgorod.example.data.mapper

import ua.dgorod.example.data.db.entity.PhotoEntity
import ua.dgorod.example.data.api.dto.PhotoDto
import ua.dgorod.example.domain.Mapper

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotoEntityMapper : Mapper<PhotoDto, PhotoEntity> {

    override fun map(from: PhotoDto): PhotoEntity {
        with(from) {
            return PhotoEntity(id, name, user.fullname, camera, lens, rating)
        }
    }

    override fun map(from: List<PhotoDto>): List<PhotoEntity> = from.map { map(it) }
}