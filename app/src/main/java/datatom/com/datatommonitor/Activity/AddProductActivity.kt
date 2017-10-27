package datatom.com.datatommonitor.Activity


import android.view.Menu
import android.view.MenuItem
import android.widget.Spinner
import com.datatom.productmonitor.Activity.BaseActivity
import datatom.com.datatommonitor.R
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import datatom.com.datatommonitor.Entity.ProductBean
import datatom.com.datatommonitor.Entity.ProductBeanDao
import datatom.com.datatommonitor.Util.GreenDaoManager
import datatom.com.datatommonitor.Util.HttpUtil
import datatom.com.datatommonitor.Util.RxUtil
import io.reactivex.observers.DisposableObserver
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import org.greenrobot.greendao.rx.RxDao
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.reactivestreams.Subscriber
import java.util.function.Consumer


class AddProductActivity : BaseActivity() {

    var spinner :Spinner  ?= null

    private var data_list: ArrayList<String>? = null

   // private var productdao: RxDao<ProductBean, Long> ?= null

    private var productdao2: ProductBeanDao ?= null

    private var arr_adapter: ArrayAdapter<String>? = null

    var ip :EditText? = null

    var username :EditText? = null

    var pwd :EditText? = null

    var addbutton : TextView? = null



    override fun initView() {

        setToolbartitle("产品添加")

        val session = GreenDaoManager.getInstance().getSession()

       // productdao = session.productBeanDao.rx()

        productdao2 = session.productBeanDao

        spinner = findViewById(R.id.id_pro_spinner) as Spinner

        ip = findViewById(R.id.edit_ip) as EditText

        username = findViewById(R.id.edit_username) as EditText

        pwd = findViewById(R.id.edit_password) as EditText

        addbutton = find(R.id.button_addpro)

        //数据
        data_list = ArrayList<String>()

        data_list!!.add("产品类型")
        data_list!!.add("1")
        data_list!!.add("2")
        data_list!!.add("3")
        data_list!!.add("4")

        addbutton!!.onClick {
            var isOk = true

            var product : ProductBean? = null
            if (pwd!!.text.toString().equals("")){
                ShowSnackLong("请输入密码")
                isOk = false

            }
            if (username!!.text.toString().equals("")){

                ShowSnackLong("请输入用户名")

                //toast("请输入用户名")
                isOk = false
            }

            if (ip!!.text.toString().equals("")){

                ShowSnackLong("请输入ip")
                isOk = false

            }



            //测试
            if (isOk){

                product = ProductBean()

                product!!.ip = ip!!.text.toString()

                product!!.name = username!!.text.toString()

                product!!.pwd = pwd!!.text.toString()

                productdao2!!.insert(product)



                finish()

            }


            //暂不联网
            /*RetrofitUrlManager.getInstance().putDomain("addpro", "https://"+ip!!.text.toString())

            HttpUtil.instance.apiService().addProduction(username!!.text.toString(),
                    pwd!!.text.toString())
                    .compose(RxUtil.applySchedulers())
                    .subscribe(object : DisposableObserver<String>(){

                        override fun onError(p0: Throwable) {
                            toast("无法连接目标产品，请检查ip 端口输入是否正确")
                        }

                        override fun onNext(p0: String) {


                        }

                        override fun onComplete() {
                            if (isOk){

                                product = ProductBean()

                                product!!.ip = ip!!.text.toString()

                                product!!.name = username!!.text.toString()

                                product!!.pwd = pwd!!.text.toString()

                                productdao2!!.insert(product)



                                finish()

                            }

                        }


                    })*/





        }

        //适配器
        arr_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, data_list)
        //设置样式
        arr_adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //加载适配器
        spinner!!.setAdapter(arr_adapter)

        spinner!!.requestFocus()
    }

    override fun ActivityLayout(): Int {

        return R.layout.activity_add_product
    }

   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.addproduct, menu)

        return true
    }*/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        if (id == R.id.action_addproduct) {

            var isOk = true

            var product : ProductBean? = null
            if (pwd!!.text.toString().equals("")){
                ShowSnackLong("请输入密码")
                isOk = false

            }
            if (username!!.text.toString().equals("")){

                ShowSnackLong("请输入用户名")

                //toast("请输入用户名")
                isOk = false
            }

            if (ip!!.text.toString().equals("")){

                ShowSnackLong("请输入ip")
               isOk = false

            }



        //测试
            if (isOk){

                product = ProductBean()

                product!!.ip = ip!!.text.toString()

                product!!.name = username!!.text.toString()

                product!!.pwd = pwd!!.text.toString()

                productdao2!!.insert(product)



                finish()

            }


            //暂不联网
            /*RetrofitUrlManager.getInstance().putDomain("addpro", "https://"+ip!!.text.toString())

            HttpUtil.instance.apiService().addProduction(username!!.text.toString(),
                    pwd!!.text.toString())
                    .compose(RxUtil.applySchedulers())
                    .subscribe(object : DisposableObserver<String>(){

                        override fun onError(p0: Throwable) {
                            toast("无法连接目标产品，请检查ip 端口输入是否正确")
                        }

                        override fun onNext(p0: String) {


                        }

                        override fun onComplete() {
                            if (isOk){

                                product = ProductBean()

                                product!!.ip = ip!!.text.toString()

                                product!!.name = username!!.text.toString()

                                product!!.pwd = pwd!!.text.toString()

                                productdao2!!.insert(product)



                                finish()

                            }

                        }


                    })*/






        }

        return super.onOptionsItemSelected(item)
    }

}
