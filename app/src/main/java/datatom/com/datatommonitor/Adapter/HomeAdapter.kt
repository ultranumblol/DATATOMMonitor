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
import datatom.com.datatommonitor.Entity.ProductInfo
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.Util.RxUtil
import datatom.com.datatommonitor.View.MyProgressBar
import datatom.com.datatommonitor.View.WaveView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_product.view.*
import org.w3c.dom.Text
import wgz.datatom.com.utillibrary.util.LogUtil
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
        private val volume: MyProgressBar
        private val cpu: MyProgressBar
        private val ram: MyProgressBar
        private val waveview: WaveView
        private val numwarn: TextView
        private val numerror: TextView
        private val prostate: TextView

        var basedur: Long = 18L


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

            //name.text = data?.name


            name.text = "HyHive v1.0.0-0000 |10.0.40.41|3节点"


            //todo 网络获取数据

           // RetrofitUrlManager.getInstance().putDomain("douban", "https://api.douban.com");
            //测试数据
            var productinfo: ProductInfo.ProResult? = null

            productinfo = ProductInfo().ProResult()



            productinfo!!.apply {

                mhealth = health()
                mhealth!!.error = 0
                mhealth!!.warning = 0

                mstatus = status()
                mstatus!!.cpu = 78
                mstatus!!.storage = 34
                mstatus!!.memory = 98

                mload = load()
                mload!!.high = 1
                mload!!.medium = 2
                mload!!.low = 2


            }

            LogUtil.d("product :  "+ productinfo.toString())





            volume.setProgress(productinfo.mstatus!!.storage!! + 0f)

            cpu.setProgress(productinfo.mstatus!!.cpu!! + 0f)

            ram.setProgress(productinfo.mstatus!!.memory!! + 0f)

            numerror.text = "${productinfo.mhealth!!.error}条错误"
            numwarn.text = "${productinfo.mhealth!!.warning}条警告"


            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(volume, "progress", 0f, productinfo!!.mstatus!!.storage!!+0f)
                        animator.setDuration(productinfo!!.mstatus!!.storage!! * basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })
            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(cpu, "progress", 0f, productinfo!!.mstatus!!.cpu!!+0f)
                        animator.setDuration(productinfo!!.mstatus!!.cpu!! * basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })
            Observable.just(1)
                    .delay(300, TimeUnit.MILLISECONDS)
                    .compose(RxUtil.applySchedulers())
                    .subscribe({ integer ->
                        val animator = ObjectAnimator.ofFloat(ram, "progress", 0f, productinfo!!.mstatus!!.memory!!+0f)
                        animator.setDuration(productinfo!!.mstatus!!.memory!! * basedur)
                        animator.setInterpolator(FastOutSlowInInterpolator())
                        animator.start()

                    })


            waveview.apply {
                setMode(WaveView.MODE_CIRCLE)
                setSpeed(WaveView.SPEED_SLOW)
                max = 100
                progress = 60


            }



              if(productinfo.mhealth!!.error==0) {
                    waveview.setWaveColor(context.resources.getColor(R.color.unhealth))
                    prostate.text = "亚健康"

                    if (productinfo.mhealth!!.warning == 0) {
                        waveview.setWaveColor(context.resources.getColor(R.color.healthy))
                        prostate.text = "健康"
                    }


                }

                else  {
                    waveview.setWaveColor(context.resources.getColor(R.color.proerror))
                    prostate.text = "不健康"
                }







            load.setOnClickListener { context.startActivity(Intent(context, LoadInfoActivity::class.java)) }

            health.setOnClickListener { context.startActivity(Intent(context, HealthInfo::class.java)) }


        }
    }

}
