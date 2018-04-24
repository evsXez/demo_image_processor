package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.History

import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Created by evgeny on 13/03/2018.
 */

interface IView : IBaseView {
    fun prepareWithData(data: ArrayList<ImageRefModel>)
    fun dataUpdated()
    fun dataInserted()
    fun askUseThisItem(): Completable
}

interface IPresenter : IBasePresenter {
    fun clickOnItem(pos: Int)
}

interface IModel : IBaseModel {
    fun history(): ArrayList<ImageRefModel>
    fun setImage(ref: ImageRefModel)
}

