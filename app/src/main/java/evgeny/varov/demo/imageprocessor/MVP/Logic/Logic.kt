package evgeny.varov.demo.imageprocessor.MVP.Logic

import android.content.Context
import android.graphics.BitmapFactory
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType
import evgeny.varov.demo.imageprocessor.MVP.Data.FilterType.*
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import org.jetbrains.anko.toast
import java.util.logging.Filter
import javax.inject.Inject

/**
 * Created by evgeny on 14/03/2018.
 */
class Logic(val context: Context) {

    fun applyFilter(type: FilterType, image: ImageModel): ImageModel {
        with(image) {
            return ImageModel(
                    when (type) {
                        ROTATE -> Filters.rotate(bmp)
                        INVERT -> Filters.invert(bmp)
                        MIRROR -> Filters.mirror(bmp)
                    })
        }
    }
}