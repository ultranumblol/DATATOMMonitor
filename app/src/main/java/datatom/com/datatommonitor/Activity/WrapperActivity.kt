package datatom.com.datatommonitor.Activity

import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.CallSuper
import android.support.annotation.RequiresApi
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import com.datatom.productmonitor.Activity.BaseActivity
import com.jakewharton.scalpel.ScalpelFrameLayout
import datatom.com.datatommonitor.R

/**
 * Created by wgz on 2017/11/1.
 */
abstract class WrapperActivity : AppCompatActivity() {

    private var mDrawableBackUp: Drawable? = null
    private var mScalpelEnable = false
    private var mScalpelMenu: MenuItem? = null
    private var mScalpelLayout: ScalpelFrameLayout? = null



    override fun setContentView(layoutResID: Int) {
        mScalpelLayout = ScalpelFrameLayout(this)

        mScalpelLayout!!.setLayerInteractionEnabled(false)
        mScalpelLayout!!.setDrawViews(true)
        mScalpelLayout!!.setDrawIds(true)
        mScalpelLayout!!.setChromeColor(ContextCompat.getColor(this, R.color.`white`))
        mScalpelLayout!!.setChromeShadowColor(ContextCompat.getColor(this, R.color.`red`))

        LayoutInflater.from(this).inflate(layoutResID, mScalpelLayout, true)

        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

        super.setContentView(mScalpelLayout, lp)


    }




    @CallSuper
   override fun onCreateOptionsMenu(menu: Menu): Boolean {
        mScalpelMenu = menu.add(Menu.NONE, R.id.scalpel_menu, Menu.NONE, "Enable Scalpel")

        return true
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    @CallSuper
    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.scalpel_menu) {

            mScalpelEnable = !mScalpelEnable

            if (mScalpelEnable) {
                mDrawableBackUp = window.decorView.background
                window.decorView.setBackgroundResource(R.color.`black`)

                mScalpelLayout!!.setLayerInteractionEnabled(true)
                mScalpelMenu!!.setTitle("Disable Scalpel")
            } else {
                window.decorView.background = mDrawableBackUp

                mScalpelLayout!!.setLayerInteractionEnabled(false)
                mScalpelMenu!!.setTitle("Enable Scalpel")
            }

            Toast.makeText(this, "Submit change", Toast.LENGTH_SHORT)
                    .show()
        }

        return super.onOptionsItemSelected(item)
    }

}

