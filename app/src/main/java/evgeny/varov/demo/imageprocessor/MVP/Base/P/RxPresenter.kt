package evgeny.varov.demo.imageprocessor.MVP.Base.P

import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by evgeny on 14/03/2018.
 */
abstract class RxPresenter<V: IBaseView, M: IBaseModel>(val _view: V, val _model: M) : BasePresenter<V, M>(_view, _model) {
    val obs = CompositeDisposable()

    private fun dispose() = obs.dispose()

    override fun viewDetached() {
        super.viewDetached()
        obs.dispose()
    }

}