package evgeny.varov.demo.imageprocessor.MVP.Base.P

import android.annotation.SuppressLint
import android.support.annotation.CallSuper
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView
import evgeny.varov.demo.imageprocessor.MVP.Base.M.IBaseModel
import io.reactivex.disposables.CompositeDisposable
import org.greenrobot.eventbus.EventBus

/**
 * Created by evgeny on 16/02/2018.
 */
abstract class BaseTestPresenter<V : IBaseView, M : IBaseModel>(val model: M) : IBaseTestPresenter {
    val obs = CompositeDisposable()
    var view: V? = null

    @CallSuper
    @SuppressLint("MissingSuperCall")
    override fun viewAttached(view: IBaseView) {
        this.view = view as V
        if (listen_to_events) eventbus.register(this)
    }

    @CallSuper
    @SuppressLint("MissingSuperCall")
    override fun viewDetached() {
        view = null
        if (listen_to_events) eventbus.unregister(this)
        obs.dispose()
    }
}
