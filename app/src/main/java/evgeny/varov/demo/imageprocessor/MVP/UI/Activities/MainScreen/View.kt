package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import android.os.Bundle
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseActivity
import evgeny.varov.demo.imageprocessor.R
import org.jetbrains.anko.longToast
import timber.log.Timber


class View : BaseActivity<IPresenter>(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.w("here $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.w("here $this")
    }

    override fun daggerInject(appComponent: AppComponent) {
        DaggerComponent.builder()
                .appComponent(appComponent)
                .module(Module(this))
                .build()
                .inject(this);
    }

    override fun testToast(s: String) { longToast(s) }
}
