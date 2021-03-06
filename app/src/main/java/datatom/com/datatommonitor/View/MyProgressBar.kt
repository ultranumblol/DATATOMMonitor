package datatom.com.datatommonitor.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import datatom.com.datatommonitor.R
import datatom.com.datatommonitor.Util.SomeUtil

/**
 * Created by wgz on 2017/10/24.
 */
class MyProgressBar : View {


    internal var radius = 0f

    internal var arcRectF = RectF()
    internal var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    var procolor = 0

    internal var progress = 0f


    constructor(context: Context) : super(context, null) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        val array = context.obtainStyledAttributes(attrs, R.styleable.MyProgressBar)
        progress = array.getFloat(R.styleable.MyProgressBar_pro, 0f)
        radius = array.getFloat(R.styleable.MyProgressBar_radius, 0f)
        // LogUtil.d("radius" + radius);
        procolor =array.getColor(R.styleable.MyProgressBar_procolor,0)
        radius = SomeUtil.dpToPixel(radius)
        // LogUtil.d("radius after" + radius);
        array.recycle()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {


    }

    fun getProgress(): Float {
        return progress
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
    }

    public override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.textSize = SomeUtil.dpToPixel(radius / 2.6f)
        paint.textAlign = Paint.Align.CENTER


        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()

        // LogUtil.d("width : "+ getWidth());

        paint.color = Color.parseColor("#eeeeee")
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.strokeWidth = 7f
        arcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)


        canvas.drawArc(arcRectF, 0f, 360f, false, paint)

        paint.color = procolor

        canvas.drawArc(arcRectF, 0f, progress * 3.6f, false, paint)

        //paint.color = Color.GRAY
        paint.style = Paint.Style.FILL

        canvas.drawText(progress.toInt().toString() + "%", centerX, centerY - (paint.ascent() + paint.descent()) / 2, paint)
    }


}