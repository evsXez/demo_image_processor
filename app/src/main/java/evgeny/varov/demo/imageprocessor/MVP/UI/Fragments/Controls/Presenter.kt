package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import evgeny.varov.demo.imageprocessor.MVP.Base.P.RxPresenter
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(view: IView, model: IModel) : RxPresenter<IView, IModel>(view, model), IPresenter {

    override val listen_to_events = false

    override fun filterClicked(type: FilterType) {
        model.applyFilter(type)
    }

    override fun clearClicked() {
        view.askClearConfirmation()
                .subscribe({model.clearHistory()})
    }
}

