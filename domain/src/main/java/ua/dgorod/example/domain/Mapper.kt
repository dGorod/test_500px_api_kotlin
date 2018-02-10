package ua.dgorod.example.domain

/**
 * Mapper interface that should be used for all models transformations between architecture layers.
 *
 * @author Dmytro Gorodnytskyi
 * on 12-Dec-17.
 */
interface Mapper<F, T> {

    fun map(from: F): T

    fun map(from: List<F>): List<T>
}