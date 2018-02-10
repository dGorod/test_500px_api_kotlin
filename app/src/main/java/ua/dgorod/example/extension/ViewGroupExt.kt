package ua.dgorod.example.extension

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false) : View {
    return context.layoutInflater.inflate(layout, this, attachToRoot)
}