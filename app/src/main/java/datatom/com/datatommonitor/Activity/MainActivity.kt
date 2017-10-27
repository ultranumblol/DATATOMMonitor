package datatom.com.datatommonitor.Activity

import android.content.Intent
import android.os.Handler
import android.support.v4.widget.SwipeRefreshLayout
import datatom.com.datatommonitor.Adapter.HomeAdapter

import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem

import com.datatom.productmonitor.Activity.BaseActivity
import com.example.xrecyclerview.XRecyclerView
import com.jude.easyrecyclerview.EasyRecyclerView
import datatom.com.datatommonitor.Entity.DaoSession
import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.Util.GreenDaoManager
import org.greenrobot.greendao.query.QueryBuilder


class MainActivity : BaseActivity(), SwipeRefreshLayout.OnRefreshListener {


    var adapter: HomeAdapter? = null

    var handler : Handler?=null

   // var rv: EasyRecyclerView? = null
    var rv: XRecyclerView? = null

    private var mList : ArrayList<ProductBean> ? = null

    private var fileRxQuery: QueryBuilder<ProductBean>?= null

    override fun initView() {

        setToolbartitle("统一运维平台")

        toolBar!!.setNavigationIcon(R.drawable.ic_clear_white_24dp)

        toolBar!!.setNavigationOnClickListener { finish() }

        handler =Handler();

        // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
        // RetrofitUrlManager.getInstance().putDomain("douban", "https://api.douban.com");

        // BaseUrl configured in the Domain-Name header will override BaseUrl in the global setting
        // RetrofitUrlManager.getInstance().setGlobalDomain("your BaseUrl");

        adapter = HomeAdapter(applicationContext)

       // rv = findViewById(R.id.id_home_rv) as EasyRecyclerView
        rv = findViewById(R.id.id_home_rv) as XRecyclerView

        rv!!.setLayoutManager(LinearLayoutManager(this))

        rv!!.adapter = adapter

       // rv!!.setRefreshListener(this)
        rv!!.setLoadingMoreEnabled(false)


        rv!!.setLoadingListener(object  : XRecyclerView.LoadingListener{
            override fun onRefresh() {
                handler!!.postDelayed({
                    adapter!!.clear()
                    initdata()
                },1500)

            }

            override fun onLoadMore() {

            }


        })



        val session = GreenDaoManager.getInstance().getSession() as DaoSession

        fileRxQuery = session.productBeanDao.queryBuilder()




       // initdata()

    }

    private fun initdata() {

        adapter!!.clear()

        mList = fileRxQuery!!.list() as ArrayList<ProductBean>?

        adapter!!.addAll(mList)
        rv!!.refreshComplete()
        adapter!!.notifyDataSetChanged()


    }

    override fun onResume() {
        super.onResume()

        initdata()
    }

    override fun ActivityLayout(): Int {
        return R.layout.activity_main
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.mainactivity, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_gl) {

            startActivity(Intent(this@MainActivity, ProductManagerAcitvity::class.java))

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        handler!!.postDelayed({
            adapter!!.clear()
            initdata()
        },1500)



    }

}
