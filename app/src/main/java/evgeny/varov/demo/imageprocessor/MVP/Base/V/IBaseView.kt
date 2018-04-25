package evgeny.varov.demo.imageprocessor.MVP.Base.V

import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.IBase
import evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen.IPresenter
import javax.inject.Inject

/**
 * Created by evgeny on 16/02/2018.
 */
interface IBaseView : IBase {
    fun daggerInject(appComponent: AppComponent)
}