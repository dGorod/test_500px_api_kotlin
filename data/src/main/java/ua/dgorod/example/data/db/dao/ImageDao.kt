package ua.dgorod.example.data.db.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import ua.dgorod.example.data.db.Tables
import ua.dgorod.example.data.db.entity.ImageEntity

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
@Dao
interface ImageDao {

    @Query("SELECT * FROM ${Tables.images} WHERE ${ImageEntity.Field.photoId} = :photoId")
    fun getAll(photoId: Long): Flowable<List<ImageEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(images: List<ImageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(image: ImageEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(image: ImageEntity)

    @Query("DELETE FROM ${Tables.images} WHERE ${ImageEntity.Field.photoId} = :photoId")
    fun delete(photoId: Long)
}