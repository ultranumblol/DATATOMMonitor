package datatom.com.datatommonitor.Util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by wgz on 2017/10/24.
 */

public class SomeUtil {
    public static float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

}
