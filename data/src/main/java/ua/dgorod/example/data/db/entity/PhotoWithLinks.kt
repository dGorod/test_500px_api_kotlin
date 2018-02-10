package ua.dgorod.example.data.db.entity

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

/**
 * Entity wrapper to collect photo data with corresponding images.
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
data class PhotoWithLinks(

    @Embedded
    var photo: PhotoEntity,

    @Relation(parentColumn = PhotoEntity.Field.id, entityColumn = ImageEntity.Field.photoId)
    var links: List<ImageEntity>
) {
    constructor() : this(PhotoEntity(-1L, "", "", null, null, 0.0), listOf())
}