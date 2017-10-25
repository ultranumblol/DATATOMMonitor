package datatom.com.datatommonitor.Adapter

import android.content.Context
import android.view.ViewGroup

import com.jude.easyrecyclerview.adapter.BaseViewHolder

import datatom.com.datatommonitor.R

/**
 * Created by wgz on 2017/8/8.
 */

class HealthAdapter(context: Context) : MyRecyclerArrayAdapter<Any>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return HealthViewholder(parent)
    }

    private inner class HealthViewholder(itemView: ViewGroup) : BaseViewHolder<Any>(itemView, R.layout.item_health_info)
}
