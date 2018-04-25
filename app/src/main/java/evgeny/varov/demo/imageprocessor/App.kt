package evgeny.varov.demo.imageprocessor

import android.app.Application
import com.chibatching.kotpref.Kotpref
import timber.log.Timber

/**
 * Created by evgeny on 16/02/2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Kotpref.init(this)
        daggerPrepare()
        initTimber()
    }

    lateinit var appComponent: AppComponent
        private set

    fun daggerPrepare() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build();
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    val tag = "_IP_"
                    return String.format("$tag [L:%s] [M:%s] [C:%s]",
                            element.lineNumber,
                            element.methodName,
                            super.createStackElementTag(element))
                }
            })
        } else {
            Timber.plant(object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}
            })
        }
    }
}

