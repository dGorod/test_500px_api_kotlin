package ua.dgorod.example.ui.adapter.holder

import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*
import org.jetbrains.anko.dimen
import org.jetbrains.anko.padding
import ua.dgorod.example.R
import ua.dgorod.example.data.PhotoSizes
import ua.dgorod.example.model.PhotoUiModel
import ua.dgorod.example.extension.inflate

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class PhotoViewHolder(
        parent: ViewGroup,
        clickListener: (View) -> Unit
): RecyclerView.ViewHolder(parent.inflate(R.layout.item_photo)) {

    init {
        itemView.setOnClickListener(clickListener)
    }

    fun bind(data: PhotoUiModel) {
        with(itemView) {
            ViewCompat.setTransitionName(vPhoto, data.id.toString())

            vName.text = data.name
            vAuthor.text = data.author

            Picasso.with(context)
                    .load(data.images[PhotoSizes.SMALL] ?: "invalid")
                    .error(R.drawable.ic_cloud_off_24dp)
                    .into(vPhoto, photoLoadingCallback)
        }
    }

    private val photoLoadingCallback = object: Callback {
        override fun onSuccess() {
            itemView.vPhoto.scaleType = ImageView.ScaleType.CENTER_CROP
            itemView.vPhoto.padding = 0
        }
        override fun onError() {
            itemView.vPhoto.scaleType = ImageView.ScaleType.FIT_CENTER
            itemView.vPhoto.padding = itemView.dimen(R.dimen.indent_l)
        }
    }
}