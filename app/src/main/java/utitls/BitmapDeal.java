package utitls;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by 张佳亮 on 2016/3/7.
 */
public class BitmapDeal {

    /**
     * 将图片资源转换为bitmap，此方法使用JNI进行转化，而不是java的createBitmap（）
     * 更加节省内存，避免OOM错误
     * @param context
     * @param resId
     * @return
     */
    public Bitmap readBitmap(Context context, int resId) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Bitmap.Config.RGB_565;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opts);
    }


    /**
     * 根据Image的大小来加载图片
     * @param res
     * @param resid
     * @param reqWidth
     * @param reqHeight
     * @return
     */

    public static Bitmap decodeBitmapFromResource(Resources res,int resid,int reqWidth,int reqHeight){

        BitmapFactory.Options options=new BitmapFactory.Options();
        /**
         * 设置为true后，BitmapFactory并不会正真的去加载图片。
         *
         */
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeResource(res,resid,options);

        /**
         * 得到原始图片的宽和高
         */
        int height=options.outHeight;
        int width=options.outWidth;
        /**
         * 根据需要的宽和高以及原图的宽和高，计算采样率。
         *
         */

        int scaleFctor=Math.max(width/reqWidth,height/reqHeight);
        /**
         * 将计算出的采样率赋值给options.inSampleSize
         */
        options.inSampleSize=scaleFctor;

        /**
         * 设为false，则正真开始加载图片。
         */
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res, resid, options);
    }


    /**
     * 将Bitmap保存到本地
     * 并且返回路径
     * @param canvasBitmap
     */

    public static String saveBitmap(Bitmap canvasBitmap){

        try{
            File file=new File(Environment.getExternalStorageDirectory(),"微笑园");
            if(!file.exists()){
                file.mkdir();
            }
            File file2=new File(file,System.currentTimeMillis()+".jpg");
            file2.createNewFile();
            String path=file2.getAbsolutePath();
            FileOutputStream fileOutputStream=new FileOutputStream(file2);
            /**
             * 第一个参数表示压缩的格式，有三个值可以选择：JPEG，PNG，WEBP
             * 第二个参数表示压缩的质量，取值为0~100。其中100是压缩后图片质量做好的。
             * 最后一个参数是输出流，即将Bitmap保存的位置。
             */

            canvasBitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
            fileOutputStream.close();
            return path;

           // Toast.makeText(this, "图片保存成功", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e){
           // Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();

            Log.i("liang",e.getMessage());
            return  null;

        }
    }


    /**
     * 对Bitmap进行缩放
     * @param bitmap
     * @param reqWidth
     * @param reqHeight
     * @return
     */

    public static Bitmap shrink (Bitmap bitmap,float reqWidth,float reqHeight) {
        Matrix matrix = new Matrix();
        matrix.postScale(reqWidth,reqHeight); //长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return resizeBmp;
    }



}
