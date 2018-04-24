package evgeny.varov.demo.imageprocessor.MVP.UI.Activities.MainScreen

import dagger.Component
import dagger.Provides
import evgeny.varov.demo.imageprocessor.ActivityScope
import evgeny.varov.demo.imageprocessor.AppComponent

/**
 * Created by evgeny on 13/03/2018.
 */
@ActivityScope
@Component(modules = arrayOf(Module::class), dependencies = arrayOf(AppComponent::class))
interface Component {
    fun inject(view: View)
}

@dagger.Module
class Module(val view: IView) {
    @Provides
    fun provideModel(): IModel = Model()
    @Provides
    fun provideView(): IView = view
    @Provides
    fun providePresenter(model: IModel): IPresenter = Presenter(view, model)
}