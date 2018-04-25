package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by evgeny on 13/03/2018.
 */

interface IView : IBaseView {
    fun askClearConfirmation(): Completable
}

interface IPresenter : IBasePresenter {
    fun filterClicked(type: FilterType)
    fun clearClicked()
    fun simulateLongRunningChanged(long_running: Boolean)
}

interface IModel : IBaseModel {
    fun applyFilter(type: FilterType)
    fun clearHistory()
    fun setLongRunningOps(long_running: Boolean)
}

