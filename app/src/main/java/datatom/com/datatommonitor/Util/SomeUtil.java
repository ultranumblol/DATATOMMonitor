package datatom.com.datatommonitor.Util;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;

/**
 * Created by wgz on 2017/10/24.
 */

public class SomeUtil {
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    //获取相对时间
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public static String getRelativeDays(long time){

        return String.valueOf(DateUtils.getRelativeTimeSpanString(time));
    }
}
