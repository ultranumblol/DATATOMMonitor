package datatom.com.datatommonitor.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.jude.easyrecyclerview.adapter.BaseViewHolder

import org.w3c.dom.Text

import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.Entity.ProductBeanDao
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.Util.GreenDaoManager

/**
 * Created by wgz on 2017/8/2.
 */

class ProductManagerAdapter(context: Context) : MyRecyclerArrayAdapter<ProductBean>(context) {

    override fun OnCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ProductManagerViewHolder(parent)
    }


    private inner class ProductManagerViewHolder(itemView: ViewGroup) : BaseViewHolder<ProductBean>(itemView, R.layout.item_productlist) {

        private var name: TextView
        private var ip : TextView

        private val delete: TextView



        init {
            name = `$`<TextView>(R.id.id_productname)
            delete = `$`<TextView>(R.id.img_delete)
            ip = `$`<TextView>(R.id.id_productip)


        }

        override fun setData(data: ProductBean?) {

            name.text = data!!.name


            name.text = "HyHive v1.0.0-0000"

            ip.text = "10.0.40.41 | 3节点"

            delete.setOnClickListener {

                var dialog :AlertDialog.Builder = AlertDialog.Builder(context)

                dialog.setMessage("确认删除"+data.name+"?")
                        .setPositiveButton("确认",object :DialogInterface.OnClickListener{
                            override fun onClick(p0: DialogInterface?, p1: Int) {
                                //删除
                                var productdao : ProductBeanDao ?= null

                                productdao = GreenDaoManager.getInstance().session.productBeanDao

                                productdao.delete(data)

                                remove(getPosition())

                                notifyDataSetChanged()

                            }


                        }).setNegativeButton("取消",null)
                        .create()
                        .show()

            }

        }
    }
}
