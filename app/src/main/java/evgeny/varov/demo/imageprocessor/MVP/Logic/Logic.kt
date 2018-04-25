package evgeny.varov.demo.imageprocessor.MVP.Logic

import android.content.Context
import android.graphics.BitmapFactory
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType.*
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.FilteringModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageRefModel
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.PercentModel
import io.reactivex.Observable
import org.jetbrains.anko.toast
import java.util.Random
import java.util.logging.Filter
import javax.inject.Inject

/**
 * Created by evgeny on 14/03/2018.
 */
class Logic(val context: Context) {

    fun applyFilter(type: FilterType, image: ImageModel, long_running_simulation: Boolean, ref: ImageRefModel): Observable<FilteringModel> {
        return Observable.create( { e->
            if (long_running_simulation) {
                val sleep_sec = 5 + Random().nextInt(10)
                val elapsed_interval_ms = 100
                var elapsed = 0
                val total = sleep_sec*1000
                while (elapsed < total) {
                    elapsed += elapsed_interval_ms
                    val next = ref.copy(percent = PercentModel(elapsed.toFloat(), total.toFloat()))
                    e.onNext(FilteringModel(next, null))
                    Thread.sleep(elapsed_interval_ms.toLong())
                }
            }

            with(image) {
                val result = ImageModel(
                        when (type) {
                            ROTATE -> Filters.rotate(bmp)
                            INVERT -> Filters.invert(bmp)
                            MIRROR -> Filters.mirror(bmp)
                        })
                val next = ref.copy(percent = PercentModel())
                e.onNext(FilteringModel(next, result))
                e.onComplete()
            }
        })

    }
}