package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.InfoScreen

import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.BaseScreenComponent
import evgeny.varov.demo.imageprocessor.PerScreen
import evgeny.varov.demo.imageprocessor.PerView
import javax.inject.Inject
import javax.inject.Singleton

//------------------------------------------------------------------
// SCREEN
//------------------------------------------------------------------
@PerScreen
@Component(modules = arrayOf(ScreenModule::class), dependencies = arrayOf(AppComponent::class))
interface ScreenComponent : BaseScreenComponent {
    fun providePresenter(): IPresenter
}


@Module
class ScreenModule() {
    @Provides @PerScreen
    fun provideModel(): IModel = Model()
    @Provides @PerScreen
    fun providePresenter(model: IModel): IPresenter = Presenter(model)
}

//------------------------------------------------------------------
// VIEW
//------------------------------------------------------------------
@PerView
@Component(dependencies = arrayOf(ScreenComponent::class))
interface ViewComponent {
    fun inject(view: View)
}

