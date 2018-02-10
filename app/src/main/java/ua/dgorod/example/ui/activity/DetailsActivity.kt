package ua.dgorod.example.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import ua.dgorod.example.R
import ua.dgorod.example.data.PhotoSizes
import ua.dgorod.example.model.PhotoUiModel

/**
 *
 * @author Dmytro Gorodnytskyi
 * on 13-Dec-17.
 */
class DetailsActivity : AppCompatActivity() {

    companion object {
        private val EXTRA_PHOTO = "extra_photo"
        private val EXTRA_TRANSITION_NAME = "extra_transition_name"

        fun start(activity: AppCompatActivity, photo: PhotoUiModel, transitionView: View) {
            val transitionName = ViewCompat.getTransitionName(transitionView)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity, transitionView, transitionName)

            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra(EXTRA_PHOTO, photo)
            intent.putExtra(EXTRA_TRANSITION_NAME, transitionName)

            activity.startActivity(intent, options.toBundle())
        }
    }

    private var photo = PhotoUiModel(-1L, "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onContentChanged() {
        super.onContentChanged()

        intent.getStringExtra(EXTRA_TRANSITION_NAME)?.let { vPhoto.transitionName = it }

        photo = intent.getParcelableExtra(EXTRA_PHOTO)

        fillData()
        setListeners()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun fillData() {
        with(photo) {
            vName.text = name
            vAuthor.text = String.format(getString(R.string.author), author)
            
            val devInfo = if (hasDeviceInfo()) "$camera $lens" else getString(R.string.n_a)
            vDevice.text = String.format(getString(R.string.device), devInfo)
    
            Picasso.with(this@DetailsActivity)
                    .load(images[PhotoSizes.SMALL] ?: "invalid")
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .error(R.drawable.ic_cloud_off_24dp)
                    .into(vPhoto, photoLoadingCallback)
        }
    }

    private fun setListeners() {
        vShare.setOnClickListener {
            Intent(Intent.ACTION_SEND).run {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.photo_url))
                putExtra(Intent.EXTRA_TEXT, photo.images[PhotoSizes.BIG])
                startActivity(Intent.createChooser(this, getString(R.string.share_this)))
            }
        }
    }

    private fun loadFullImage() {
        // after fast thumbnail loading, allow finish screen transition
        supportStartPostponedEnterTransition()

        Picasso.with(this)
                .load(photo.images[PhotoSizes.BIG] ?: "invalid")
                .noPlaceholder()
                .into(vPhoto)
    }

    private val photoLoadingCallback = object: Callback {
        override fun onSuccess() { loadFullImage() }
        override fun onError() { loadFullImage() }
    }
}