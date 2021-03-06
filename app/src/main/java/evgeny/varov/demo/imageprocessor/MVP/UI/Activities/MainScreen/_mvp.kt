package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBaseTestPresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView

/**
 * Created by evgeny on 13/03/2018.
 */

interface IView : IBaseView {
    fun testToast(s: String)
    fun navigateToInfo()
}

interface IPresenter : IBaseTestPresenter {
    fun menuInfoSelected()
}

interface IModel : IBaseModel {
    fun getNumber(): Int
}