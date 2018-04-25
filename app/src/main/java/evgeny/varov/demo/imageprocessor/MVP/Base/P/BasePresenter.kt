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
abstract class BasePresenter<V : IBaseView, M : IBaseModel>(val view: V, val model: M) : IBasePresenter {
    val obs = CompositeDisposable()

    @CallSuper
    @SuppressLint("MissingSuperCall")
    override fun viewAttached() {
        if (listen_to_events) eventbus.register(this)
    }

    @CallSuper
    @SuppressLint("MissingSuperCall")
    override fun viewDetached() {
        if (listen_to_events) eventbus.unregister(this)
        obs.dispose()
    }
}
