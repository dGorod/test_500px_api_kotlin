package ua.dgorod.example.viewmodel

/**
 * Container for data with state and error description support.
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class Parcel<T> private constructor(val status: Status, val content: T?, val error: Throwable?) {

    companion object {
        fun <T> success (data: T) = Parcel(Status.SUCCESS, data, null)
        fun <T> error(error: Throwable) = Parcel<T>(Status.ERROR, null, error)
    }

    enum class Status {
        SUCCESS, ERROR
    }
}