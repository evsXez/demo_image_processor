package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import com.jakewharton.rxbinding2.view.clicks
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseFragment
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType.*
import evgeny.varov.demo.imageprocessor.R
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_controls.*
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.yesButton


class View : BaseFragment<IPresenter>(), IView {

    override fun daggerInject(appComponent: AppComponent) {
        DaggerComponent.builder()
                .appComponent(appComponent)
                .module(Module(this))
                .build()
                .inject(this);
    }

    override fun getLayoutId() = R.layout.fragment_controls

    override fun init() {
        button_rotate.clicks().subscribe({ presenter.filterClicked(ROTATE) })
        button_invert.clicks().subscribe({ presenter.filterClicked(INVERT) })
        button_mirror.clicks().subscribe({ presenter.filterClicked(MIRROR) })
        button_clear.clicks().subscribe({ presenter.clearClicked() })
    }

    override fun askClearConfirmation(): Completable {
        return Completable.create { c->
            alert {
                title = getString(R.string.ask_clear)
                yesButton { c.onComplete() }
                cancelButton { }
            }.show()
        }
    }
}
