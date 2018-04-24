package evgeny.varov.demo.imageprocessor.MVP.Logic

import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import kotlin.math.max


/**
 * Created by evgeny on 14/03/2018.
 */
internal class Filters {

    companion object {

        val Bitmap.cx: Float get() = this.width/2f
        val Bitmap.cy: Float get() = this.height/2f
        fun Canvas.clear() = this.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR)


        fun rotate(original: Bitmap): Bitmap {
            val res = Bitmap.createBitmap(original.height, original.width, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(res)
//            canvas.rotate(90f)
//            canvas.drawBitmap(original, 0f, -original.height.toFloat(), null)
            canvas.rotate(-90f)
            canvas.drawBitmap(original, -original.width.toFloat(), 0f, null)
            return res

        }

        fun mirror(original: Bitmap): Bitmap {
            val res = original.copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(res)
            canvas.clear()
            canvas.scale(-1f, 1f, res.cx, res.cy)
            canvas.drawBitmap(original, 0f, 0f, null)
            return res
        }

        fun invert(original: Bitmap): Bitmap {
            val res = original.copy(Bitmap.Config.ARGB_8888, true)
            val canvas = Canvas(res)
            canvas.clear()
            val paint = Paint()
            paint.colorFilter = ColorMatrixColorFilter(ColorMatrix(
                    floatArrayOf(
                        -1.0f, 0.0f, 0.0f, 0.0f, 255.0f,
                        0.0f, -1.0f, 0.0f, 0.0f, 255.0f,
                        0.0f, 0.0f, -1.0f, 0.0f, 255.0f,
                        0.0f, 0.0f, 0.0f, +1.0f, 0.0f
                    )
            ))
            canvas.drawBitmap(original, 0.0f, 0.0f, paint)
            return res
        }
    }
}