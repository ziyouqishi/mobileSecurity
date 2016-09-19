package utitls;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by liang on 2016/9/18.
 */

public class MD5Utils {
    public static  String encoder(String password){
        StringBuffer buffer;
        /**
         * 1，信息摘要器
         */
        MessageDigest digest= null;
        try {
            digest = MessageDigest.getInstance("md5");
            /**
             * 2，变成byte数组
             */
            byte bytes[]=digest.digest(password.getBytes());
            buffer=new StringBuffer();
            /**
             * 3，每个byte位与8个二进制数做与运算
             */
            for(byte b:bytes){
                int number=b & 0xff;
                //int 类型转换为十六进制
                String numberStr=Integer.toHexString(number);

                if(numberStr.length()==1){
                    buffer.append("0");
                }

                buffer.append(numberStr);

            }
            return  buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
}
