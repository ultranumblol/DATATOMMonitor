package datatom.com.datatommonitor.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.datatom.productmonitor.Activity.BaseActivity
import com.jude.easyrecyclerview.EasyRecyclerView
import datatom.com.datatommonitor.Adapter.FullyLinearLayoutManager
import datatom.com.datatommonitor.Adapter.HealthAdapter
import datatom.com.datatommonitor.R
import org.jetbrains.anko.find

class HealthInfo : BaseActivity() {

    var errorAdapter : HealthAdapter ?= null

    var warningAdapter : HealthAdapter ?= null

   /* var errorRv : EasyRecyclerView? = null

    var warningRv : EasyRecyclerView? = null*/

    var errorRv : RecyclerView? = null

    var warningRv : RecyclerView? = null

    var ll_nodata : LinearLayout? = null

    var ll_showdata : LinearLayout? = null


    override fun initView() {
        setToolbartitle("健康详情")

        errorRv = findViewById(R.id.id_error_rv) as RecyclerView

        warningRv = findViewById(R.id.id_warning_rv) as RecyclerView

        ll_nodata = find(R.id.ll_nodata)

        ll_showdata = find(R.id.ll_showdata)

        errorAdapter = HealthAdapter(applicationContext)

        warningAdapter = HealthAdapter(applicationContext)

        errorRv!!.setLayoutManager(FullyLinearLayoutManager(this))

        warningRv!!.setLayoutManager(FullyLinearLayoutManager(this))

        errorRv!!.adapter = errorAdapter

        warningRv!!.adapter = warningAdapter

        initdata()


    }

    private fun initdata() {

        errorAdapter!!.add("1")
        errorAdapter!!.add("1")
        errorAdapter!!.add("1")

        warningAdapter!!.add("1")
        warningAdapter!!.add("1")
        warningAdapter!!.add("1")


    }

    override fun ActivityLayout(): Int {
        return R.layout.activity_health_info
    }


}
