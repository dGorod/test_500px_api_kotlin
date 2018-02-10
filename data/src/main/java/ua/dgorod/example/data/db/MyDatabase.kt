package ua.dgorod.example.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import ua.dgorod.example.data.db.dao.ImageDao
import ua.dgorod.example.data.db.dao.PhotoDao
import ua.dgorod.example.data.db.entity.ImageEntity
import ua.dgorod.example.data.db.entity.PhotoEntity

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */

@Database(entities = [PhotoEntity::class, ImageEntity::class], version = 2)
abstract class MyDatabase: RoomDatabase() {

    companion object {

        private const val dbName = "Sample.db"

        @Volatile
        private var instance: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, MyDatabase::class.java, dbName)
                        .build()
                        .also { instance = it }
            }
        }
    }

    abstract fun photos(): PhotoDao
    abstract fun images(): ImageDao
}

object Tables {
    const val photos = "photos"
    const val images = "images"
}