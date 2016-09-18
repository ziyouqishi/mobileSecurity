package utitls;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by 张佳亮 on 2016/3/8.
 */
public class DensityUtitl {

    public static final int WIDTH=0;
    public static final int HEIGHT=1;


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public  int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 通过传入的参数类型得到屏幕的宽度或者高度
     * @param context
     * @param type
     * @return
     */

    public int getScreenParams(Context context,int type){
        WindowManager wm=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        int width=outMetrics.widthPixels;
        int height=outMetrics.heightPixels;
        if(type==DensityUtitl.HEIGHT){
            return height;
        }else if(type==DensityUtitl.WIDTH){
            return  width;
        }else{
            return  0;
        }

    }


}
