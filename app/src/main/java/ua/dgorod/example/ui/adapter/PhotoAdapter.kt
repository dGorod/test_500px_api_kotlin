package ua.dgorod.example.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import ua.dgorod.example.model.PhotoUiModel
import ua.dgorod.example.ui.adapter.holder.PhotoViewHolder

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class PhotoAdapter(
        val data: LinkedHashSet<PhotoUiModel>,
        private val clickListener: (View) -> Unit
): RecyclerView.Adapter<PhotoViewHolder>() {

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(parent, clickListener)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(data.elementAt(position))
    }
}