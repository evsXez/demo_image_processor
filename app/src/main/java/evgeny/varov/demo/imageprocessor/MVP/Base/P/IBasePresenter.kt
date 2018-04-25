package evgeny.varov.demo.imageprocessor.MVP.Base.P

import android.support.annotation.CallSuper
import evgeny.varov.demo.imageprocessor.MVP.Base.IBase

/**
 * Created by evgeny on 16/02/2018.
 */
interface IBasePresenter : IBase {
    @CallSuper
    fun viewAttached()
    @CallSuper
    fun viewDetached()

    val listen_to_events: Boolean
}