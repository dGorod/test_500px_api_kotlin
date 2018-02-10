package ua.dgorod.example.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.dgorod.example.data.db.MyDatabase
import ua.dgorod.example.data.db.entity.ImageEntity
import ua.dgorod.example.data.db.entity.PhotoEntity

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 14-Dec-17.
 */
@RunWith(AndroidJUnit4::class)
class SimplePhotoDaoTest {

    private val testPhotoId = 100L
    private val testName = "some tester"
    private val testImageUrl = "http://www.something.com"

    private lateinit var database: MyDatabase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Throws(Exception::class)
    @Before
    fun initDb() {
        val context = InstrumentationRegistry.getContext()
        val dbClass = MyDatabase::class.java
        database = Room.inMemoryDatabaseBuilder(context, dbClass).allowMainThreadQueries().build()
    }

    @Throws(Exception::class)
    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAndGetPhotoById() {
        val testPhoto = PhotoEntity(testPhotoId, testName, "dgorod", "Sony", null, 1.0)
        val testImage = ImageEntity(testImageUrl, PhotoSizes.SMALL, testPhotoId)

        database.photos().insert(testPhoto)
        database.images().insert(testImage)

        database.photos()
                .get(testPhotoId)
                .test()
                .assertValue { testPhotoEntity(it.photo) && testImageEntity(it.links) }
    }

    private fun testPhotoEntity(result: PhotoEntity): Boolean {
        return result.id == testPhotoId && result.name == testName
    }

    private fun testImageEntity(result: List<ImageEntity>): Boolean {
        return result.size == 1 && result[0].url == testImageUrl && result[0].photoId == testPhotoId
    }
}