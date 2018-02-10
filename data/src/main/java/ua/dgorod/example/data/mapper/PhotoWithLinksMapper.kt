package ua.dgorod.example.data.mapper

import ua.dgorod.example.data.db.entity.PhotoWithLinks
import ua.dgorod.example.domain.Mapper
import ua.dgorod.example.domain.model.Photo

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
class PhotoWithLinksMapper : Mapper<PhotoWithLinks, Photo> {

    override fun map(from: PhotoWithLinks): Photo {
        val images = from.links.associateBy { it.size }.mapValues { it.value.url }

        with(from.photo) {
            return Photo(id, name, author, camera, lens, rating, images)
        }
    }

    override fun map(from: List<PhotoWithLinks>): List<Photo> = from.map { map(it) }
}