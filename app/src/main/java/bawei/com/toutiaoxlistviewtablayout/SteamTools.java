package bawei.com.toutiaoxlistviewtablayout;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class SteamTools {
    /**
     * 读取SD卡的文件
     * */
    public static String readSdcardFile(String fileName){
        try {
            InputStream is = new FileInputStream(new File(Environment.getExternalStorageDirectory(),fileName));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = is.read(buffer))!=-1){
                baos.write(buffer,0,len);
            }
            return baos.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 写入SD卡的操作
     * */
    public static void WriteToFile(String string,String fileName){
        //先判断是否有SD卡
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(Environment.getExternalStorageDirectory(),fileName))));
                bufferedWriter.write(string);
                bufferedWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
