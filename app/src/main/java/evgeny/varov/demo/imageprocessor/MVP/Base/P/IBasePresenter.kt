package evgeny.varov.demo.imageprocessor.MVP.Base.P

import android.support.annotation.CallSuper

/**
 * Created by evgeny on 16/02/2018.
 */
interface IBasePresenter {
    @CallSuper
    fun viewAttached()
    @CallSuper
    fun viewDetached()

    val listen_to_events: Boolean
}