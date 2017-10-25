package datatom.com.datatommonitor.Activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import com.datatom.productmonitor.Activity.BaseActivity
import com.jude.easyrecyclerview.EasyRecyclerView
import datatom.com.datatommonitor.Adapter.ProductManagerAdapter
import datatom.com.datatommonitor.R
import com.jude.easyrecyclerview.decoration.DividerDecoration
import datatom.com.datatommonitor.Entity.DaoSession
import datatom.com.datatommonitor.Entity.ProductBean

import datatom.com.datatommonitor.Util.GreenDaoManager

import org.greenrobot.greendao.query.QueryBuilder

import org.reactivestreams.Subscriber


class ProductManagerAcitvity : BaseActivity() {

    var fab : FloatingActionButton? = null

    var adapter : ProductManagerAdapter? = null

    var rv : EasyRecyclerView? = null

    private var fileRxQuery: QueryBuilder<ProductBean>?= null

    private var mList : ArrayList<ProductBean> ? = null


    override fun initView() {
        setToolbartitle("产品管理")

        fab = findViewById(R.id.fab) as FloatingActionButton

        adapter = ProductManagerAdapter(applicationContext)

        rv = findViewById(R.id.id_pro_listrv) as EasyRecyclerView

        rv!!.setLayoutManager(LinearLayoutManager(this))

        val itemDecoration = DividerDecoration(Color.GRAY, 1,24, 10)//颜色 & 高度 & 左边距 & 右边距
        itemDecoration.setDrawLastItem(true)//有时候你不想让最后一个item有分割线,默认true.
        itemDecoration.setDrawHeaderFooter(false)//是否对Header于Footer有效,默认false.
        rv!!.addItemDecoration(itemDecoration)

        rv!!.adapter = adapter

        val session = GreenDaoManager.getInstance().getSession() as DaoSession

        fileRxQuery = session.productBeanDao.queryBuilder()



        fab!!.setOnClickListener {

             startActivity(Intent(this@ProductManagerAcitvity,AddProductActivity::class.java))

        }




    }

    override fun onResume() {
        super.onResume()
        initdata()


    }



    private fun initdata() {

        adapter!!.clear()

        mList = fileRxQuery!!.list() as ArrayList<ProductBean>?

        adapter!!.addAll(mList)



    }

    override fun ActivityLayout(): Int {
        return R.layout.activity_product_manager_acitvity
    }


}
