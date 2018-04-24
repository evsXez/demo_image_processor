package evgeny.varov.demo.imageprocessor.MVP.Base.lists

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by evgeny on 21/03/2018.
 */
abstract class BaseViewHolder<T> (val view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, position: Int)
}