package datatom.com.datatommonitor.Activity


import android.support.v7.app.AppCompatActivity
import android.os.Bundle


import org.jetbrains.anko.*



class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   TestUI().setContentView(this)


      verticalLayout {
            var a= 1
          var b = a+2
          padding = dip(24)
                textView("a =  "+a +"\nb="+b){
                    textSize=14f


                }
            a=3
          textView("a =  "+a +"\nb="+b){
              textSize=14f


          }
        }



    }

/*  inner class TestUI : AnkoComponent<TestActivity> {
        override fun createView(ui: AnkoContext<TestActivity>)=ui.apply {

            verticalLayout {
                toolbar{
                    title="test"
                    setBackgroundColor(Color.parseColor("#00bfff"))

                  setNavigationOnClickListener { onBackPressed() }



                }

                val name = editText()
                button("Say Hello") {
                    onClick { ctx.toast("Hello, ${name.text}!") }
                }
            }
        }.view
    }*/


}
