package com.ydx.facepp.opencv;

import android.util.Log;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Created by lenovo on 2016/9/21.
 */
public class FaceDetector {


    private static FaceDetector faceDetector=new FaceDetector();

    private FaceDetector(){

    }
    public static FaceDetector getInstance(){
        return faceDetector;
    }
    private CascadeClassifier cascadeClassifier;
    public void init(String xmlPath){
        cascadeClassifier=new CascadeClassifier();

        cascadeClassifier.load(xmlPath);
    }


    public FaceMat detector(Mat mat) {

        MatOfRect faceDetections = new MatOfRect();
        Mat grayMat = new Mat();
        Imgproc.cvtColor(mat, grayMat, Imgproc.COLOR_BGRA2GRAY);
        //Mat image,             //输入图像
        //MatOfRect objects,     //检测到的Rect
        //double scaleFactor,    //缩放比例，必须大于1
        //int minNeighbors,      //合并窗口时最小neighbor，每个候选矩阵至少包含的附近元素个数
        //int flags,             //检测标记，只对旧格式的分类器有效，与cvHaarDetectObjects的参数flags相同，默认为0，可能的取值为CV_HAAR_DO_CANNY_PRUNING(CANNY边缘检测)、CV_HAAR_SCALE_IMAGE(缩放图像)、CV_HAAR_FIND_BIGGEST_OBJECT(寻找最大的目标)、CV_HAAR_DO_ROUGH_SEARCH(做粗略搜索)；如果寻找最大的目标就不能缩放图像，也不能CANNY边缘检测
        //Size minSize,          //最小检测目标
       // Size maxSize           //最大检测目标
        cascadeClassifier.detectMultiScale(grayMat, faceDetections,1.1,2,2,new Size(grayMat.height()/5,grayMat.height()/5),new Size());
        Log.i(String.format("Detected %s faces",
                faceDetections.toArray().length), "");

        int faceNum = 0;
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(
                    mat,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(255, 0, 0));
            faceNum++;
        }
//        Bitmap bitmap=Bitmap.createBitmap(mat.width(),mat.height(), Bitmap.Config.ARGB_8888);
//        Utils.matToBitmap(mat,bitmap);
        FaceMat faceMat;
        if(faceNum>0) {
            faceMat = new FaceMat(mat,true);
        }else {
            faceMat = new FaceMat(mat,false);
        }
        return faceMat;


    }


}
