package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.InfoScreen

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.BaseScreenComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseActivity
import evgeny.varov.demo.imageprocessor.R
import org.jetbrains.anko.longToast
import timber.log.Timber
import kotlin.reflect.KClass
import evgeny.varov.demo.imageprocessor.MVP.UI.Activities.InfoScreen.View as InfoActivity


class View : BaseActivity<IPresenter>(), IView {

    //val blob = ByteArray(20*1024*1024, {100})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        Timber.w("here $this")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.w("here $this")
    }


    override fun daggerInject(appComponent: AppComponent) {
        if (app().screenComponent == null || !(app().screenComponent is DaggerScreenComponent)) {
            app().screenComponent = DaggerScreenComponent
                    .builder()
                    .appComponent(appComponent)
                    .screenModule(ScreenModule())
                    .build()
        }

        DaggerViewComponent
                .builder()
                .screenComponent(app().screenComponent as DaggerScreenComponent)
                .build()
                .inject(this)
    }


}
