package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.History

import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseFragment
import evgeny.varov.demo.imageprocessor.MVP.Base.lists.BaseAdapter
import evgeny.varov.demo.imageprocessor.MVP.Base.lists.BaseViewHolder
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.R
import kotlinx.android.synthetic.main.fragment_history.*
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.jakewharton.rxbinding2.view.touches
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import io.reactivex.Completable
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton


class View : BaseFragment<IPresenter>(), IView {

    override fun daggerInject(appComponent: AppComponent) {
        DaggerComponent.builder()
                .appComponent(appComponent)
                .module(evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.History.Module(this))
                .build()
                .inject(this);
    }

    override fun getLayoutId() = R.layout.fragment_history

    override fun init() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.itemAnimator = DefaultItemAnimator()
//        recyclerView.hasFixedSize()
    }

    override fun prepareWithData(data: ArrayList<ImageRefModel>) {
        recyclerView.adapter = HistoryAdapter(data)
    }
    override fun dataUpdated() {
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun dataInserted() {
        recyclerView.adapter.notifyItemInserted(0)
        recyclerView.scrollToPosition(0)
    }

    inner class HistoryAdapter(data: ArrayList<ImageRefModel>) : BaseAdapter<ImageRefModel, HistoryViewHolder>(data) {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int) = HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.history_item, parent, false))
    }

    inner class HistoryViewHolder(view: View) : BaseViewHolder<ImageRefModel>(view) {
        val image: ImageView
        val progress: ProgressBar
        val info: TextView
        val lay: View
        //var pos = 0
        init {
            image = view.findViewById(R.id.image)
            progress = view.findViewById(R.id.progress)
            info = view.findViewById(R.id.info)
            lay = view.findViewById(R.id.lay)

            lay.clicks().subscribe({presenter.clickOnItem(adapterPosition)})
        }

        fun View.hide() {this.visibility = View.GONE}
        fun View.show() {this.visibility = View.VISIBLE}

        override fun bind(item: ImageRefModel, position: Int) {

            //view.setBackgroundColor(if (position % 2 == 0) Color.TRANSPARENT else Color.LTGRAY)
            image.show()
            progress.show()
            //info is always visible

//            image.setImageBitmap(BitmapFactory.decodeFile(item.ref))
            Glide.with(app())
                    .load(item.ref)
                    .into(image)
            info.text = item.ref //"INFO HERE"
        }
    }

    override fun askUseThisItem(): Completable {
        return Completable.create({c->
            alert{
                title = getString(R.string.ask_use_item)
                yesButton { c.onComplete() }
                noButton {  }
            }.show()
        })
    }
}
