package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import android.os.Bundle
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseActivity
import evgeny.varov.demo.imageprocessor.R
import org.jetbrains.anko.longToast


class View : BaseActivity<IPresenter>(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
