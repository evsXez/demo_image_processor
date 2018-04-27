package evgeny.varov.demo.imageprocessor.MVP.Base.P

import android.support.annotation.CallSuper
import evgeny.varov.demo.imageprocessor.MVP.Base.IBase
import evgeny.varov.demo.imageprocessor.MVP.Base.V.IBaseView

/**
 * Created by evgeny on 16/02/2018.
 */
interface IBaseTestPresenter : IBase {
    @CallSuper
    fun viewAttached(view: IBaseView)
    @CallSuper
    fun viewDetached()

    val listen_to_events: Boolean
}