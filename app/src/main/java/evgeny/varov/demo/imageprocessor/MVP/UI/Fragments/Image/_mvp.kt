package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Image

import android.content.Context
import android.graphics.Bitmap
import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by evgeny on 13/03/2018.
 */

interface IView : IBaseView {
    fun loadImageFromGallery()
    fun askChangeImage()
    fun useImage(image: ImageModel)
    fun toast(s: String)
    fun showProgress(show: Boolean)
}

interface IPresenter : IBasePresenter {
    fun imageClicked()
    fun useThisImagePath(path: String)
    fun useThisImageBitmap(bmp: Bitmap)
    fun loadImageFromURL(url: String)
}

interface IModel : IBaseModel {
    fun imageLoaded(): Observable<ImageModel>
    fun setImage(image: ImageModel)
    fun loadImage(url: String): Single<ImageModel>
}
