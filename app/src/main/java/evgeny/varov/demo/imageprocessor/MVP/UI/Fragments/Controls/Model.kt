package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Controls

import evgeny.varov.demo.imageprocessor.MVP.Base.M.BaseModel
import evgeny.varov.demo.imageprocessor.MVP.Logic.Logic
import evgeny.varov.demo.imageprocessor.MVP.Data.Data
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.FilteringModel
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

/**
 * Created by evgeny on 16/02/2018.
 */
class Model(logic: Logic, data: Data) : BaseModel(logic, data), IModel {

    override fun applyFilter(type: FilterType) {
        val ref = data.newRef()
        data.imageLoaded()
                .flatMap { logic.applyFilter(type, it, data.getLongRunningOps(), ref) }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe( {postPercentAndSave(it)} )
    }

    private fun postPercentAndSave(m: FilteringModel) {
        if (m.ref.percent.full) {
            m.image?.let {
                EventBus.getDefault().post(it)
                EventBus.getDefault().post(data.save(it, m.ref))
            }
        } else {
            EventBus.getDefault().post(m.ref)
        }
    }

    override fun clearHistory() {
        data.clearHistory()
    }

    override fun setLongRunningOps(long_running: Boolean) {
        data.setLongRunningOps(long_running)
    }
}