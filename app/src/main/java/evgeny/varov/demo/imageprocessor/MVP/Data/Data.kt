package evgeny.varov.demo.imageprocessor.MVP.Data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.media.Image
import android.util.Log
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import evgeny.varov.demo.imageprocessor.R
import io.reactivex.Observable
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

/**
 * Created by evgeny on 14/03/2018.
 */
class Data(val context: Context) {

    //TODO: use repos (files/network/etc)
    private var tmp: ImageModel? = null

    fun imageLoaded(): Observable<ImageModel> {
        Log.d("Data", "Data: ${this.hashCode()}")
        return Observable.just(tmp ?: ImageModel(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher_round)))
    }

    fun save(image: ImageModel): ImageRefModel {
        tmp = image
        val ref = generateRef()
        return saveToFile(image, ref)
    }

    fun history(): ArrayList<ImageRefModel> {
        val res = ArrayList<ImageRefModel>()
        val folder = context.filesDir.absolutePath + "/"
        for (s in context.filesDir.list()) {res.add(ImageRefModel(folder + s))}
        res.sortWith(compareByDescending({it.ref}))
        return res

//        return Observable.fromArray(*context.filesDir.list())
//                .map { s->ImageRefModel(s) }
//                .toList()
//                .blockingGet();
    }


    private fun generateRef() = "${context.filesDir}/${System.currentTimeMillis()}"

    private fun saveToFile(image: ImageModel, ref: String): ImageRefModel {
        FileOutputStream(ref).use {image.bmp.compress(Bitmap.CompressFormat.PNG, 100, it)}
        return ImageRefModel(ref)
    }

    private fun loadFromFile(ref: ImageRefModel): ImageModel {
        FileInputStream(ref.ref).use { return ImageModel(BitmapFactory.decodeFile(ref.ref)) }
    }

    fun clearHistory() {
        Observable.fromArray(*context.filesDir.listFiles())
                .doOnComplete({EventBus.getDefault().post(ImageRefModel(""))})
                .subscribe() { it.delete() }
    }

    fun setImage(ref: ImageRefModel): ImageModel {
        tmp = loadFromFile(ref)
        return tmp!!
    }

    fun setImage(image: ImageModel) {
        tmp = image
    }

}