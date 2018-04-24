package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Image

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import evgeny.varov.demo.imageprocessor.MVP.Base.M.BaseModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Data
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import org.jetbrains.anko.dimen
import timber.log.Timber
import kotlin.math.log

/**
 * Created by evgeny on 16/02/2018.
 */
class Model(logic: Logic, data: Data) : BaseModel(logic, data), IModel {

    val MAX_SIZE = 1000

    override fun imageLoaded() = data.imageLoaded()

    override fun setImage(image: ImageModel) {
        data.setImage(image)
    }

    override fun loadImage(url: String): Single<ImageModel> {
        return Single.create({emitter: SingleEmitter<Bitmap> ->
                    Glide.with(logic.context)
                            .asBitmap()
                            .load(url)
                            .listener(object : RequestListener<Bitmap> {
                                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                                    Timber.e("error loading: $e")
                                    emitter.onError(NullPointerException(e.toString()))
                                    return true
                                }

                                override fun onResourceReady(resource: Bitmap?, model: Any?, target: Target<Bitmap>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                                    Timber.e("loading successful")
                                    resource?.let {
                                        emitter.onSuccess(it)
                                        return true
                                    }
                                    emitter.onError(NullPointerException())
                                    return true
                                }
                            })
                            .submit(MAX_SIZE, MAX_SIZE)
                            .get()

                })
                .map { b->ImageModel(b) }
                .doOnEvent({image,err->data.save(image)})
    }
}