package com.demo.highcharts.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class Utils {

    public static String readFileToString(String path){
        File file=new File(path);
        StringBuffer sb=new StringBuffer();

        try {
            BufferedReader br=new BufferedReader(new FileReader(file));
            while (true){
                String str=br.readLine();
                if(str==null)
                    break;
                sb.append(str);
            }
            br.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "IOException";
    }

    public static String copyAssetsFile(Context context,String fileName){
        try {
//            String dataPath= getDir("HighChartDataJs", Context.MODE_PRIVATE).getPath();
            String dataPath= Environment.getExternalStorageDirectory().getPath()+"/com.demo.highcharts";
            Log.e("dataPath",dataPath);
            File file=new File(dataPath);
            if(!file.exists()){
                file.mkdirs();
            }
            File jsFile=new File(dataPath+"/"+fileName);
            if(!jsFile.exists())
                jsFile.createNewFile();
            InputStream is= context.getResources().getAssets().open(fileName);
            if(is==null)
                return "no asset file";
            FileOutputStream fileWriter= new FileOutputStream(jsFile);
            byte[] bytes=new byte[1024];
            int a;
            while ((a= is.read(bytes))!=-1){
                fileWriter.write(bytes,0,a);
            }
            is.close();
            fileWriter.flush();
            fileWriter.close();
            return  jsFile.getPath();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "IOException";
    }
}
