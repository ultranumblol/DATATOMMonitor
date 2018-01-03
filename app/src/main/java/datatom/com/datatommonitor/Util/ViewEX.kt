package datatom.com.datatommonitor.Util

import android.view.View

/**
 * Created by wgz on 2017/11/2.
 */
fun View.Show() {
    visibility = View.VISIBLE

}

fun View.hide() {

    visibility = View.GONE


}

fun View.inVisible(){

    visibility = View.INVISIBLE


}


fun  View.onClick(function:() -> Unit ){

    setOnClickListener {
        function
    }


}