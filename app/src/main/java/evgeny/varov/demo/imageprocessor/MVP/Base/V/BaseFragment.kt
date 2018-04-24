package evgeny.varov.demo.imageprocessor.MVP.Base.V

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import evgeny.varov.demo.imageprocessor.App
import evgeny.varov.demo.imageprocessor.MVP.Base.P.IBasePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import org.jetbrains.anko.*

/**
 * Created by evgeny on 16/02/2018.
 */
abstract class BaseFragment<P : IBasePresenter> : Fragment(), IBaseView
{
    @Inject
    lateinit var presenter: P

    lateinit var root: View

    val disposables = CompositeDisposable()


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        daggerInject(appComponent())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.viewAttached()
        init()
    }

    fun app() = activity?.application as App
    fun appComponent() = app().appComponent

    override fun onDetach() {
        super.onDetach()
    }

    abstract fun init()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //if (inflater != null) {
            root = inflater.inflate(getLayoutId(), container, false)
        //}
        return root
    }

    abstract fun getLayoutId(): Int


}