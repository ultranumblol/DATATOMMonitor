package com.datatom.productmonitor.Activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import datatom.com.datatommonitor.R


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * Created by wgz on 2017/7/26.
 */

abstract class BaseActivity : AppCompatActivity() {

    internal var toolBar: Toolbar? = null

    private var msubscription: CompositeDisposable? = null//管理所有的订阅
    private var layoutBody: RelativeLayout? = null
    var rootview: RelativeLayout? = null

    var mcontentView : View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.activity_base)

        toolBar = findViewById(R.id.tool_bar) as Toolbar

        msubscription = CompositeDisposable()

        layoutBody = findViewById(R.id.container) as RelativeLayout

        rootview = findViewById(R.id.rl_root_root) as RelativeLayout



        setContentViewId()

        setToolBar()
        this.initView()
    }





    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 此时android.R.id.home即为返回箭头
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }




    abstract fun initView()


    fun ShowSnackLong(str : String){

        layoutBody?.let { Snackbar.make(it,str,Snackbar.LENGTH_LONG).show() }

    }


    /**
     * 设置titlebar
     */
    protected fun setToolBar() {
        setSupportActionBar(toolBar)
        val actionBar = supportActionBar
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false)
            actionBar.setDisplayHomeAsUpEnabled(true)

        }
        toolBar!!.setNavigationOnClickListener { onBackPressed() }
        // toolBar.setNavigationIcon(R.drawable.aaa);
    }

    fun setToolbartitle(title: String) {
        //setSupportActionBar(toolBar);
        toolBar!!.title = title
    }

    fun setContentViewId() {
        mcontentView = layoutInflater.inflate(ActivityLayout(), null)
        if (layoutBody!!.childCount > 0) {
            layoutBody!!.removeAllViews()
        }
        if (mcontentView != null) {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)
            layoutBody!!.addView(mcontentView, params)
        }
    }

    protected abstract fun ActivityLayout(): Int

    fun addsub(disposable : Disposable){
        msubscription?.add(disposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        if (this.msubscription != null && msubscription!!.isDisposed) {
            this.msubscription!!.dispose()
        }

    }
}
