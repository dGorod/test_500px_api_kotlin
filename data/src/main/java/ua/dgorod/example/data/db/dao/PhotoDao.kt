package ua.dgorod.example.data.db.dao

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import ua.dgorod.example.data.db.Tables
import ua.dgorod.example.data.db.entity.PhotoEntity
import ua.dgorod.example.data.db.entity.PhotoWithLinks

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
@Dao
interface PhotoDao {

    @Query("SELECT * FROM ${Tables.photos} LIMIT :limit OFFSET :offset")
    fun getAll(limit: Int, offset: Int): Flowable<List<PhotoWithLinks>>

    @Query("SELECT * FROM ${Tables.photos} WHERE ${PhotoEntity.Field.id} = :id")
    fun get(id: Long): Maybe<PhotoWithLinks>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photos: List<PhotoEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(photo: PhotoEntity)

    @Query("DELETE FROM ${Tables.photos}")
    fun deleteAll()
}