package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import evgeny.varov.demo.imageprocessor.MVP.Base.M.BaseModel
import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic
import evgeny.varov.demo.imageprocessor.MVP.Data.Data
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import io.reactivex.Observable
import org.greenrobot.eventbus.EventBus

/**
 * Created by evgeny on 16/02/2018.
 */
class Model(logic: Logic, data: Data) : BaseModel(logic, data), IModel {

    override fun applyFilter(type: FilterType) {
        data.imageLoaded()
                .map { logic.applyFilter(type, it) }
                .subscribe(
                        {
                            EventBus.getDefault().post(it)
                            EventBus.getDefault().post(data.save(it))
                        }
                )
    }

    override fun clearHistory() {
        data.clearHistory()
    }
}