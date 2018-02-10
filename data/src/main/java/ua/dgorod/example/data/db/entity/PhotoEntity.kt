package ua.dgorod.example.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ua.dgorod.example.data.db.Tables

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
@Entity(tableName = Tables.photos)
data class PhotoEntity(

        @PrimaryKey
        @ColumnInfo(name = Field.id)
        var id: Long,

        @ColumnInfo(name = Field.name)
        var name: String,

        @ColumnInfo(name = Field.author)
        var author: String,

        @ColumnInfo(name = Field.camera)
        var camera: String?,

        @ColumnInfo(name = Field.lens)
        var lens: String?,

        @ColumnInfo(name = Field.rating)
        var rating: Double,

        @ColumnInfo(name = Field.createdAt)
        var createdAt: Long = System.currentTimeMillis()
) {
    object Field {
        const val id = "id"
        const val name = "name"
        const val author = "author"
        const val camera = "camera"
        const val lens = "lens"
        const val rating = "rating"
        const val createdAt = "createdAt"
    }
}