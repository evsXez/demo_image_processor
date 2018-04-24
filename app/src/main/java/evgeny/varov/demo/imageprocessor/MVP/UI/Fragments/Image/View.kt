package evgeny.varov.demo.imageprocessor.MVP.UI.Fragments.Image

import android.app.Activity.RESULT_OK
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import com.jakewharton.rxbinding2.view.clicks
import evgeny.varov.demo.imageprocessor.AppComponent
import evgeny.varov.demo.imageprocessor.MVP.Base.V.BaseFragment
import evgeny.varov.demo.imageprocessor.MVP.Data.Models.ImageModel
import evgeny.varov.demo.imageprocessor.R
import kotlinx.android.synthetic.main.fragment_image.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import android.graphics.Bitmap
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.dip
import org.jetbrains.anko.support.v4.toast
import android.view.View.VISIBLE
import android.view.View.GONE
import android.content.Context.CLIPBOARD_SERVICE
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit


class View : BaseFragment<IPresenter>(), IView {

    val REQUEST_IMAGE = 1;
    val REQUEST_CAMERA = 2;

    var ask_dialog: DialogInterface? = null

    override fun daggerInject(appComponent: AppComponent) {
        DaggerComponent.builder()
                .appComponent(appComponent)
                .module(Module(this))
                .build()
                .inject(this);
    }

    override fun getLayoutId() = R.layout.fragment_image

    override fun init() {
        progress.visibility = GONE
        image.clicks().subscribe({ presenter.imageClicked() }) //TODO: unsibscribe later (correct RxFragment)
    }

    override fun loadImageFromGallery() {
        startActivityForResult(
                Intent().setAction(Intent.ACTION_PICK)
                        .setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                        .setType("image/*"),
                REQUEST_IMAGE
        )
    }

    fun loadImageFromURL() {
        alert(R.string.enter_url) {
            var e: EditText? = null
            customView {
                frameLayout() {
                    padding = dip(16)
                    e = editText {
                        hint = getString(R.string.url)
                    }
                }
//                button(R.string.paste) {
//                    //backgroundColor = Color.TRANSPARENT
//                    onClick { e?.setText(clipboardText()) }
//                }
            }
            positiveButton(R.string.ok, { presenter.loadImageFromURL(e?.text.toString()) })
            negativeButton(R.string.paste, {
                val text = clipboardText()
                e?.setText(text)
                presenter.loadImageFromURL(text)
            })
        }.show()
    }

    private fun clipboardText(): String {
        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard.hasPrimaryClip() && clipboard.primaryClipDescription.hasMimeType(MIMETYPE_TEXT_PLAIN)) {
            val item = clipboard.primaryClip.getItemAt(0)
            item.text.let { return it.toString() }
            item.uri.let { return it.toString() }
        }
        return ""
    }

    fun loadImageFromCamera() {
        try {
            startActivityForResult(
                    Intent().setAction(MediaStore.ACTION_IMAGE_CAPTURE),
                    REQUEST_CAMERA
            )
        } catch (e: Exception) {
            toast(R.string.cannot_connect_to_camera)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                REQUEST_IMAGE   -> {presenter.useThisImagePath(data.data.toString())}
                REQUEST_CAMERA  -> {
                    val imageBitmap = data.extras.get("data") as Bitmap
                    presenter.useThisImageBitmap(imageBitmap)
                }
            }
        } else
            toast("Bad code")
    }

    override fun askChangeImage() {

        ask_dialog = alert(message = R.string.ask_change_image) {
            customView {
                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER
                    button(R.string.url) { onClick {  loadImageFromURL(); dismissAskDialog()  } }
                    button(R.string.gallery) { onClick { loadImageFromGallery() ; dismissAskDialog() } }
                    button(R.string.camera) { onClick { loadImageFromCamera() ; dismissAskDialog() } }
                }.applyRecursively {
                    view -> when (view) {
                        is Button -> {
                            view.backgroundColor = Color.TRANSPARENT
                            view.textColor = Color.BLUE
                        }
                    }
                }
            }
        }.show()
    }

    private fun dismissAskDialog() { ask_dialog?.dismiss() }

    override fun useImage(img: ImageModel) {
        image.setImageBitmap(img.bmp)
    }

    override fun toast(s: String) { activity?.toast(s) }

    override fun showProgress(show: Boolean) { progress.visibility = if (show) VISIBLE else GONE }
}
