package ua.dgorod.example.domain.repository

import io.reactivex.Flowable
import io.reactivex.Maybe
import ua.dgorod.example.domain.model.Photo

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
interface PhotosRepository {

    fun getAll(page: Int): Flowable<List<Photo>>

    fun get(id: Long): Maybe<Photo>
}