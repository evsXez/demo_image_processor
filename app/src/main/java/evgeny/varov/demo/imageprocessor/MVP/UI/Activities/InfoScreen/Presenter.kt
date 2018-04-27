package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.InfoScreen

import evgeny.varov.demo.imageprocessor.MVP.Base.P.BasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.P.BaseTestPresenter
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import timber.log.Timber

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(model: IModel) : BaseTestPresenter<IView, IModel>(model), IPresenter {


    override val listen_to_events = false

    override fun viewAttached(view: IBaseView) {
        super.viewAttached(view)
        Timber.w("here $this")
    }

    override fun viewDetached() {
        super.viewDetached()
        Timber.w("here $this")
    }

}

