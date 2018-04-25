package evgeny.varov.demo.imageprocessor

import android.content.Context
import dagger.Component
import dagger.Module
import dagger.Provides
import evgeny.varov.demo.imageprocessor.MVP.Data.Data
import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic
import javax.inject.Scope
import javax.inject.Singleton

/**
 * Created by evgeny on 13/03/2018.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun provideLogic(): Logic
    fun provideData(): Data
}

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun provideLogic(context: Context): Logic = Logic(context)

    @Provides
    @Singleton
    fun provideData(context: Context): Data = Data(context)

    @Provides
    fun provideContext(): Context = context
}

@Scope
annotation class PerScreen

@Scope
annotation class PerActivity

@Scope
annotation class PerFragment