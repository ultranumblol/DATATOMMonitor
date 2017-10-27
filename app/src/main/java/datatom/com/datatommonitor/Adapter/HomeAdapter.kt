package datatom.com.datatommonitor.Adapter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.support.v7.widget.CardView
import android.view.View.LAYER_TYPE_SOFTWARE
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.TextView

import com.jude.easyrecyclerview.adapter.BaseViewHolder

import datatom.com.datatommonitor.Activity.HealthInfo
import datatom.com.datatommonitor.Activity.LoadInfoActivity
import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.Util.RxUtil
import datatom.com.datatommonitor.View.MyProgressBar
import datatom.com.datatommonitor.View.WaveView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_product.view.*
import org.w3c.dom.Text
import java.util.concurrent.TimeUnit

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
        private val waveview : WaveView
        private val numwarn : TextView
        private val numerror : TextView
        private val prostate : TextView

        var  basedur : Long = 18L



        init {
            name = `$`<TextView>(R.id.id_pro_name)
            load = `$`<CardView>(R.id.id_load_info)
            health = `$`<CardView>(R.id.id_health_info)
            volume = `$`<MyProgressBar>(R.id.id_percent_capacity)
            cpu = `$`<MyProgressBar>(R.id.id_percent_cpu)
            ram = `$`<MyProgressBar>(R.id.id_percent_ram)
            waveview = `$`<WaveView>(R.id.waveview)
            numwarn = `$`<TextView>(R.id.num_warn)
            numerror = `$`<TextView>(R.id.num_error) //错误
            prostate = `$`<TextView>(R.id.id_txt_mid)//健康状态



        }

        override fun setData(data: ProductBean?) {

            name.text = data?.name

            name.text ="HyHive v1.0.0-0000 |10.0.40.41|3节点"

            volume.setProgress(80f)

            cpu.setProgress(100f)

            ram.setProgress(28f)


            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(volume, "progress", 0f, 80f)
                        animator.setDuration(80*basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })
            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(cpu, "progress", 0f, 100f)
                        animator.setDuration(100*basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })
            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(ram, "progress", 0f, 28f)
                        animator.setDuration(28*basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })

            waveview.setMode(WaveView.MODE_CIRCLE)

            waveview.setWaveColor(R.color.unhealth)
            prostate.text = "不健康"

            waveview.setSpeed(WaveView.SPEED_SLOW)

            waveview.max =100

            waveview.progress = 60



            load.setOnClickListener { context.startActivity(Intent(context, LoadInfoActivity::class.java)) }

            health.setOnClickListener { context.startActivity(Intent(context, HealthInfo::class.java)) }




        }
    }

}
