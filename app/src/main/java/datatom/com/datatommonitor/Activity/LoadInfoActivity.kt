package datatom.com.datatommonitor.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.datatom.productmonitor.Activity.BaseActivity
import datatom.com.datatommonitor.Adapter.FullyLinearLayoutManager
import datatom.com.datatommonitor.Adapter.LoadinfoAdapter
import datatom.com.datatommonitor.R

class LoadInfoActivity : BaseActivity() {

    var highrv :RecyclerView? =null

    var midrv : RecyclerView? = null

    var lowrv : RecyclerView? = null

    var highadapter : LoadinfoAdapter? = null

    var midadapter : LoadinfoAdapter? = null

    var lowadapter : LoadinfoAdapter? = null



    override fun initView() {
        setToolbartitle("负载详情")

        highrv = findViewById(R.id.id_load_highrv) as RecyclerView
        midrv = findViewById(R.id.id_load_midrv) as RecyclerView
        lowrv = findViewById(R.id.id_load_lowrv) as RecyclerView

        highrv!!.layoutManager = FullyLinearLayoutManager(applicationContext)
        midrv!!.layoutManager = FullyLinearLayoutManager(applicationContext)
        lowrv!!.layoutManager = FullyLinearLayoutManager(applicationContext)

        highadapter = LoadinfoAdapter(applicationContext)
        midadapter = LoadinfoAdapter(applicationContext)
        lowadapter = LoadinfoAdapter(applicationContext)

        highrv!!.adapter = highadapter
        midrv!!.adapter = midadapter
        lowrv!!.adapter = lowadapter

        initdata()



    }

    private fun initdata() {
        highadapter!!.add("1")
        highadapter!!.add("1")
        highadapter!!.add("1")

        midadapter!!.add("1")
        midadapter!!.add("1")
        midadapter!!.add("1")

        lowadapter!!.add("1")
        lowadapter!!.add("1")
        lowadapter!!.add("1")

    }

    override fun ActivityLayout(): Int {
        return R.layout.activity_load_info
    }


}
