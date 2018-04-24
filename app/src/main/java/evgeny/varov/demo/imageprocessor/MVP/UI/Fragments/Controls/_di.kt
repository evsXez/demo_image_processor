package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import dagger.Component
import dagger.Provides
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.AppModule
import evgeny.varov.demo.imageprocessor.FragmentScope
import evgeny.varov.demo.imageprocessor.MVP.Data.Data
import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic

/**
 * Created by evgeny on 13/03/2018.
 */
@FragmentScope
@Component(modules = arrayOf(Module::class), dependencies = arrayOf(AppComponent::class))
interface Component {
    fun inject(view: View)
}

@dagger.Module
class Module(val view: IView) {
    @Provides
    fun provideModel(logic: Logic, data: Data): IModel = Model(logic, data)
    @Provides
    fun provideView(): IView = view
    @Provides
    fun providePresenter(model: IModel): IPresenter = Presenter(view, model)
}