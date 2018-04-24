package evgeny.varov.demo.imageprocessor.MVP.Base.lists

import android.support.v7.widget.RecyclerView

/**
 * Created by evgeny on 21/03/2018.
 */
abstract class BaseAdapter<T, VH:BaseViewHolder<T>> (val data: List<T>) : RecyclerView.Adapter<VH>() {

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VH, position: Int) { holder.bind(data.get(position), position) }
}