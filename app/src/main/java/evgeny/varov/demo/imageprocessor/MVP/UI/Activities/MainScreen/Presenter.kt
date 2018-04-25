package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import evgeny.varov.demo.imageprocessor.MVP.Base.P.BasePresenter
import timber.log.Timber

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(view: IView, model: IModel) : BasePresenter<IView, IModel>(view, model), IPresenter {

    override val listen_to_events = false

    override fun viewAttached() {
        super.viewAttached()
        Timber.w("here $this")
    }

    override fun viewDetached() {
        super.viewDetached()
        Timber.w("here $this")
    }
}

