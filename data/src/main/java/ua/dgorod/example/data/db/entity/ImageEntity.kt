package ua.dgorod.example.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import ua.dgorod.example.data.db.Tables

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
@Entity(tableName = Tables.images,
        foreignKeys = [
                ForeignKey(
                        entity = PhotoEntity::class,
                        parentColumns = [PhotoEntity.Field.id],
                        childColumns = [ImageEntity.Field.photoId],
                        onDelete = ForeignKey.CASCADE)
        ])
data class ImageEntity(

        @PrimaryKey
        @ColumnInfo(name = Field.url)
        var url: String,

        @ColumnInfo(name = Field.size)
        var size: Int,

        @ColumnInfo(name = Field.photoId)
        var photoId: Long
) {
    object Field {
        const val url = "url"
        const val size = "size"
        const val photoId = "photo_id"
    }
}