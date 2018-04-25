package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.History

import android.util.Log
import evgeny.varov.demo.imageprocessor.MVP.Base.P.BasePresenter
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import io.reactivex.rxkotlin.plusAssign
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by evgeny on 16/02/2018.
 */
class Presenter(view: IView, model: IModel) : BasePresenter<IView, IModel>(view, model), IPresenter {

    lateinit var data: ArrayList<ImageRefModel>

    override val listen_to_events = true

    override fun viewAttached() {
        super.viewAttached()

        data = model.history()
        view.prepareWithData(data)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun event(image: ImageRefModel) {
        if (image.ref.length == 0) {
            data.clear()
            view.dataUpdated()
        } else {
            val ref = data.find { it.ref == image.ref }
            if (ref != null) {
                ref.percent = image.percent
                view.dataUpdated(data.indexOf(ref))
            } else {
                data.add(0, image)
                view.dataInserted()
            }
        }

    }

    override fun clickOnItem(pos: Int) {
        model.setImage(data.get(pos))
//        view.askUseThisItem()
//                .subscribe({model.setImage(data.get(pos))})
    }
}

