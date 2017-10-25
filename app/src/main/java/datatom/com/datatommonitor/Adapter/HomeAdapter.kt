package datatom.com.datatommonitor.Adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.widget.TextView

import com.jude.easyrecyclerview.adapter.BaseViewHolder

import datatom.com.datatommonitor.Activity.HealthInfo
import datatom.com.datatommonitor.Activity.LoadInfoActivity
import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.View.MyProgressBar

/**
 * Created by wgz on 2017/8/2.
 */

class HomeAdapter(context: Context) : MyRecyclerArrayAdapter<ProductBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return HomeViewHolder(parent)
    }

    private inner class HomeViewHolder(parent: ViewGroup) : BaseViewHolder<ProductBean>(parent, R.layout.item_product) {

        private val name: TextView
        private val health: CardView
        private val load: CardView
        private val volume : MyProgressBar
        private val cpu : MyProgressBar
        private val ram : MyProgressBar


        init {
            name = `$`<TextView>(R.id.id_pro_name)
            load = `$`<CardView>(R.id.id_load_info)
            health = `$`<CardView>(R.id.id_health_info)
            volume = `$`<MyProgressBar>(R.id.id_percent_capacity)
            cpu = `$`<MyProgressBar>(R.id.id_percent_cpu)
            ram = `$`<MyProgressBar>(R.id.id_percent_ram)

        }

        override fun setData(data: ProductBean?) {

            name.text = data?.name

            name.text ="HyHive v1.0.0-0000 |10.0.40.41|3节点"

            volume.setProgress(80f)

            cpu.setProgress(100f)

            ram.setProgress(28f)


            load.setOnClickListener { context.startActivity(Intent(context, LoadInfoActivity::class.java)) }

            health.setOnClickListener { context.startActivity(Intent(context, HealthInfo::class.java)) }




        }
    }

}
