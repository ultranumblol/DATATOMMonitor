package datatom.com.datatommonitor.Api

import android.app.Service
import android.content.Intent
import android.os.IBinder
import datatom.com.datatommonitor.Entity.DaoSession
import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.Util.GreenDaoManager
import datatom.com.datatommonitor.Util.HttpUtil
import datatom.com.datatommonitor.Util.RxUtil

import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import org.greenrobot.greendao.query.QueryBuilder

/**
 * Created by wgz on 2017/8/8.
 */

class BGService : Service() {

    private var mList : ArrayList<ProductBean> ? = null

    private var fileRxQuery: QueryBuilder<ProductBean>?= null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val session = GreenDaoManager.getInstance().getSession() as DaoSession

        fileRxQuery = session.productBeanDao.queryBuilder()

        mList = fileRxQuery!!.list() as ArrayList<ProductBean>?

        for (i in 0..mList!!.size - 1) {
            getmsg()
        }




    }
    // You can change BaseUrl at any time while App is running (The interface that declared the Domain-Name header)
    // RetrofitUrlManager.getInstance().putDomain("douban", "https://api.douban.com");

    // BaseUrl configured in the Domain-Name header will override BaseUrl in the global setting
    // RetrofitUrlManager.getInstance().setGlobalDomain("your BaseUrl");

    private fun getmsg() {
        Observable.interval(20, TimeUnit.SECONDS)
                .subscribe(object : Observer<Long> {
                    override fun onNext(p0: Long) {

                        HttpUtil.instance.apiService().getPushMsg(1)
                                .compose(RxUtil.applySchedulers())
                                .subscribe(object : Observer<ResponseBody>{
                                    override fun onSubscribe(p0: Disposable) {

                                    }

                                    override fun onError(p0: Throwable) {

                                    }

                                    override fun onComplete() {

                                    }

                                    override fun onNext(p0: ResponseBody) {

                                    }


                                })



                    }

                    override fun onSubscribe(disposable: Disposable) {

                    }



                    override fun onError(throwable: Throwable) {

                    }

                    override fun onComplete() {

                    }
                })

    }
}
