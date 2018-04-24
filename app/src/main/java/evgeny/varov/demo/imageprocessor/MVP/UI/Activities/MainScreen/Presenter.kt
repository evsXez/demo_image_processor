package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import evgeny.varov.demo.imageprocessor.MVP.Base.P.RxPresenter

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(view: IView, model: IModel) : RxPresenter<IView, IModel>(view, model), IPresenter {

    override val listen_to_events = false

    override fun viewAttached() {
        //view.testToast("value is: ${model.getNumber()}")
    }
}

