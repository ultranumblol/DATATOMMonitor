package datatom.com.datatommonitor.Util

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * Created by wgz on 2017/9/12.
 */
class RequestWrapper {

    var url:String? = null

    var method:String? = null

    var body: RequestBody? = null

    var timeout:Long = 10

    internal var _success: (String) -> Unit = { }
    internal var _fail: (Throwable) -> Unit = {}

    fun onSuccess(onSuccess: (String) -> Unit) {
        _success = onSuccess
    }

    fun onFail(onError: (Throwable) -> Unit) {
        _fail = onError
    }
}

fun http(init: RequestWrapper.() -> Unit) {
    val wrap = RequestWrapper()

    wrap.init()

    executeForResult(wrap)
}

private fun executeForResult(wrap:RequestWrapper) {

    Flowable.create<Response>({
        e -> e.onNext(onExecute(wrap)!!)
    }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
            .subscribe(
                    { resp ->
                        wrap._success(resp.body()!!.string())
                    },

                    { e -> wrap._fail(e) })
}

private fun onExecute(wrap:RequestWrapper): Response? {

    var req: Request? = null
    when(wrap.method) {

        "get","Get","GET" -> req =Request.Builder().url(wrap.url).build()
        "post","Post","POST" -> req = Request.Builder().url(wrap.url).post(wrap.body).build()
        "put","Put","PUT" -> req = Request.Builder().url(wrap.url).put(wrap.body).build()
        "delete","Delete","DELETE" -> req = Request.Builder().url(wrap.url).delete(wrap.body).build()
    }

    val http = Builder().connectTimeout(wrap.timeout, TimeUnit.SECONDS).build()
    val resp = http.newCall(req).execute()
    return resp
}