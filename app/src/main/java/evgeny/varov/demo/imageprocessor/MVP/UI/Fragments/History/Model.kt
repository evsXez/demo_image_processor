package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.History

import android.util.Log
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

    override fun history(): ArrayList<ImageRefModel> {
        return data.history()
    }

    override fun setImage(ref: ImageRefModel) {
        Log.d("model_", "setImage: ref = $ref")
        EventBus.getDefault().post(data.setImage(ref))
    }
}