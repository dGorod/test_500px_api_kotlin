package ua.dgorod.example.data.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.dgorod.example.data.api.dto.PopularPhotosDto
import ua.dgorod.example.domain.Const

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
interface ApiInterface {

    @GET("photos")
    fun getPhotos(@Query("feature") feature: String,
                  @Query("consumer_key") consumerKey: String,
                  @Query("image_size") imageSize: String,
                  @Query("page") page: Int = 0,
                  @Query("rpp") pageSize: Int = Const.DEFAULT_PAGE_SIZE
    ) : Single<PopularPhotosDto>
}