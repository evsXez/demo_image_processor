package evgeny.varov.demo.imageprocessor.MVP.Base.V

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import evgeny.varov.demo.imageprocessor.App
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import javax.inject.Inject

/**
 * Created by evgeny on 16/02/2018.
 */
abstract class BaseActivity<P : IBasePresenter> : AppCompatActivity(), IBaseView
{
    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        daggerInject(appComponent())
        presenter.viewAttached()
    }

    fun app() = application as App
    fun appComponent() = app().appComponent

}