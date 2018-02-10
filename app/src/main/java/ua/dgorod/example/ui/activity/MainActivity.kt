package ua.dgorod.example.ui.activity

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.longToast
import org.koin.android.architecture.ext.viewModel
import ua.dgorod.example.R
import ua.dgorod.example.model.PhotoUiModel
import ua.dgorod.example.ui.adapter.PhotoAdapter
import ua.dgorod.example.util.EndlessRecyclerOnScrollListener
import ua.dgorod.example.viewmodel.MainViewModel
import ua.dgorod.example.viewmodel.Parcel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val layoutManager = GridLayoutManager(this, 2)

    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(viewModel) {
            getPhotos(0, true)
            loadingStatus.observe(this@MainActivity, loadingObserver)
            photosList.observe(this@MainActivity, photosListObserver)
        }
    }

    override fun onContentChanged() {
        super.onContentChanged()

        adapter = PhotoAdapter(linkedSetOf(), itemClickListener)

        vPhotos.layoutManager = layoutManager
        vPhotos.addOnScrollListener(photosScrollListener)
        vPhotos.adapter = adapter
    }

    private val loadingObserver = object : Observer<Boolean> {
        override fun onChanged(status: Boolean?) {
            vProgress.visibility = if (status == true) View.VISIBLE else View.INVISIBLE
        }
    }

    private val photosListObserver = object : Observer<Parcel<List<PhotoUiModel>>> {
        override fun onChanged(data: Parcel<List<PhotoUiModel>>?) {
            when (data?.status) {
                Parcel.Status.SUCCESS -> {
                    data.content?.let {
                        adapter.data.addAll(it)
                        adapter.notifyDataSetChanged() //TODO: optimize for proper notifications
                        vEmpty.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
                Parcel.Status.ERROR -> longToast(R.string.error_general)
            }
        }
    }

    private val photosScrollListener = object : EndlessRecyclerOnScrollListener(layoutManager) {
        override fun onLoadMore(page: Int) {
            viewModel.getPhotos(page)
        }
    }

    private val itemClickListener: (View) -> Unit = {
        val index = vPhotos.getChildAdapterPosition(it)
        val photoView = it.find<ImageView>(R.id.vPhoto)
        DetailsActivity.start(this, adapter.data.elementAt(index), photoView)
    }
}
