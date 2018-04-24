package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Image

import android.graphics.Bitmap
import android.util.Log
import evgeny.varov.demo.imageprocessor.MVP.Base.P.RxPresenter
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(view: IView, model: IModel) : RxPresenter<IView, IModel>(view, model), IPresenter {

    override val listen_to_events = true

    var block_clicks = false

    override fun imageClicked() {
        if (block_clicks) return
        obs += model.imageLoaded()
                .subscribe(
                    {i->view.askChangeImage()},
                    {err->view.loadImageFromGallery()}
                )
    }

    override fun viewAttached() {
        super.viewAttached()
        obs += model.imageLoaded()
                .subscribe({i->view.useImage(i)})
    }

    override fun useThisImageBitmap(bmp: Bitmap) {
        val image = ImageModel(bmp)
        model.setImage(image)
        view.useImage(image)
    }

    override fun useThisImagePath(path: String) {
        Log.d("ip_presenter", "path: $path")
        //TODO - use path
        //DEBUG
        obs += model.imageLoaded()
                .subscribe(
                        {i->view.useImage(i)},
                        {err->view?.toast(err.localizedMessage)}
                )
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(image: ImageModel) {
        view.useImage(image)
    }

    private fun lock() {
        view.showProgress(true)
        block_clicks = true
    }
    private fun unlock() {
        view.showProgress(false)
        block_clicks = false
    }

    override fun loadImageFromURL(url: String) {
        Timber.d("Got URL: $url")
        obs += model.loadImage(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { lock() }
                .doOnEvent { _,_ -> unlock() }
                .subscribe(
                        {view.useImage(it)},
                        {err->view.toast(err.localizedMessage)}
                )
    }
}

