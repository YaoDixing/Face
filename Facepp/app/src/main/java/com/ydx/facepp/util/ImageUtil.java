package com.ydx.facepp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lenovo on 2016/9/14.
 */
public class ImageUtil {

    /**
     * 将bitmap 存入指定文件中
     * @param bitmap  源
     * @param file    目标文件
     * @param format  图片格式
     * @param q       质量
     */
    public static void saveBitmapToFile(Bitmap bitmap,File file,Bitmap.CompressFormat format,int q){
        if(!file.exists())
            return;
        FileOutputStream fileOutputStream=null;
        try {
            fileOutputStream=new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(format,q,fileOutputStream);
        try {
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap fileToBitmap(File file){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=false;
        options.inSampleSize=2;
        return BitmapFactory.decodeFile(file.getPath());
    }


    public static byte[] Bitmap2Bytes(Bitmap bm,int q) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, q, out);
        return out.toByteArray();
    }


    /*
   * 图片旋转
   */
   public static Bitmap rotaingBitmap(int degree,Bitmap bitmap){
       Matrix matrix=new Matrix();
       matrix.postRotate(degree);
       return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
   }

    public static byte[] rotaingBitmap(int degree,byte[] bytes){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        Matrix matrix=new Matrix();
        matrix.postRotate(degree);
        bitmap= Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
        return Bitmap2Bytes(bitmap,80);
    }


    /**
     * 旋转mat
     * @param src
     * @return
     */
  public static   Mat rotation(Mat src){
        int SCALE=1;
        Mat dst=new Mat();

        int angle = 90;//旋转角度(正值表示逆时针旋转)

        int length;//输出图像的宽度或高度
        //为了保证无论如何旋转都能放下，输出图像的长和宽都设为输入图像对角线长度乘以SCALE
        //但如果是缩小(SCALE<=1)，这样会导致临时图像中放不下原图，所以对于所有缩小的情况，输出图像和临时图像的长宽都设为原图的对角线长度
        if(SCALE <= 1)
            length = (int)Math.sqrt(src.cols()*src.cols() + src.rows()*src.rows());
        else
            length = (int)Math.sqrt(src.cols()*src.cols() + src.rows()*src.rows()) * SCALE;
        //建立临时图像，长宽都是源图像的对角线长度，将源图像复制到临时图像的中心后再变换
        Mat tempImg=new Mat(length,length,src.type());//临时图像，大小和输出图像一样大
        int ROI_x = length/2 - src.cols()/2;//ROI矩形左上角的x坐标
        int ROI_y = length/2 - src.rows()/2;//ROI矩形左上角的y坐标
        Rect ROIRect =new Rect(ROI_x,ROI_y,src.cols(),src.rows());//ROI矩形
        Mat tempImgROI2=new Mat(tempImg,ROIRect);//tempImg的中间部分
        src.copyTo(tempImgROI2);//将原图复制到tempImg的中心

        Point center=new Point(length/2,length/2);//旋转中心
        Mat M = Imgproc.getRotationMatrix2D(center,angle,SCALE);//计算旋转的仿射变换矩阵

        //输出看看算出的矩阵是什么
//        cout<<"变换矩阵："<<endl;
//        cout<<M.at<double>(0,0)<<","<<M.at<double>(0,1)<<","<<M.at<double>(0,2)<<","<<endl;
//        cout<<M.at<double>(1,0)<<","<<M.at<double>(1,1)<<","<<M.at<double>(1,2)<<","<<endl;

        Imgproc.warpAffine(tempImg,dst,M,new Size(length,length));//仿射变换

        return dst;
    }

}
