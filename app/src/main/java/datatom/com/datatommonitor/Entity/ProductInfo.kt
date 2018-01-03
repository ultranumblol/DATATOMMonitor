package datatom.com.datatommonitor.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by wgz on 2017/10/30.
 */

class ProductInfo {


    @SerializedName("code")
    @Expose
    var code: Int = 0

    @SerializedName("result")
    @Expose
    var result: ProResult? = null

    override fun toString(): String {
        return "ProductInfo{" +
                "code=" + code +
                ", result=" + result +
                '}'
    }

    inner class ProResult {
        @SerializedName("health")
        @Expose
        var mhealth : health? = null
        @SerializedName("status")
        @Expose
        var mstatus : status? = null
        @SerializedName("load")
        @Expose
        var mload : load? = null


        inner class health {
            @SerializedName("warning")
            @Expose
            var warning: Int? = 0
            @SerializedName("error")
            @Expose
            var error: Int? = 0

            override fun toString(): String {
                return "health{" +
                        "warning='" + warning + '\'' +
                        ", error='" + error + '\'' +
                        '}'
            }
        }

        inner class status {
            @SerializedName("storage")
            @Expose
            var storage: Int? = null
            @SerializedName("cpu")
            @Expose
            var cpu: Int? = null
            @SerializedName("memory")
            @Expose
            var memory: Int? = null

            override fun toString(): String {
                return "status{" +
                        "storage='" + storage + '\'' +
                        ", cpu='" + cpu + '\'' +
                        ", memory='" + memory + '\'' +
                        '}'
            }
        }

        inner class load {
            @SerializedName("low")
            @Expose
            var low: Int = 0
            @SerializedName("medium")
            @Expose
            var medium: Int = 0
            @SerializedName("high")
            @Expose
            var high: Int = 0

            override fun toString(): String {
                return "load{" +
                        "low=" + low +
                        ", medium=" + medium +
                        ", high=" + high +
                        '}'
            }
        }

        override fun toString(): String {
            return "ProResult(mhealth=$mhealth, mstatus=$mstatus, mload=$mload)"
        }


    }


}
