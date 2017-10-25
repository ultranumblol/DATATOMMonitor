package datatom.com.datatommonitor.Entity

import com.google.gson.annotations.SerializedName

/**
 * Created by wgz on 2017/7/25.
 */

class BaseEntity<E> {

    @SerializedName("code")
    var code: Int = 0
    @SerializedName("msg")
    var msg: String? = null
    @SerializedName("data")
    var data: E? = null

    val isSuccess: Boolean
        get() = code == 0
}

