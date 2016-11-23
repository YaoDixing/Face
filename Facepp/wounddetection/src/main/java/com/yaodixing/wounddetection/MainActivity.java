package com.yaodixing.wounddetection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView sourceIv;
    ImageView newIv;
    private Canvas canvas;
    Paint paint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initSourceImg(R.drawable.wound1);

//        Mat dst = detectOutLineByCanny(R.drawable.pen);
//        Mat dst = detectOutLineBySobel(R.drawable.pen);
//        findWoundContour(dst);
//        showArea();
    }

    void findView(){
        sourceIv =  ((ImageView) findViewById(R.id.iv_source));
        newIv = ((ImageView) findViewById(R.id.iv_new));
        sourceIv.setOnTouchListener(onTouchListener);
    }
    Bitmap srcBitmap;
    int scaleW,scaleH;
    void initCanvas(int rsId){
        sourceIv.setImageResource(rsId);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.outWidth = sourceIv.getWidth();
//        options.outHeight = sourceIv.getHeight();
//        options.inMutable = true;
//        srcBitmap = BitmapFactory.decodeResource(getResources(),rsId,options);
//        scaleW = srcBitmap.getWidth() - sourceIv.getWidth();
//        scaleH = srcBitmap.getHeight() - sourceIv.getHeight();

        srcBitmap = Bitmap.createBitmap(sourceIv.getWidth(), sourceIv.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        canvas = new Canvas(srcBitmap);

        //把view中的内容绘制在画布上
        sourceIv.draw(canvas);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        sourceIv.setImageBitmap(srcBitmap);

    }

    void initSourceImg(final int rsId){
        sourceIv.post(new Runnable() {
            @Override
            public void run() {
                initCanvas(rsId);
//              detectOutLineByCanny(rsId);
               Mat mat =  detectOutLineBySobel(rsId);
//                detectHoughCircles(mat);
            }
        });

    }
    List<MatOfPoint> contourList;
    /**
     * canny检测边缘
     * @param rsId
     */
    Mat detectOutLineByCanny(int rsId){
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),rsId);
            Mat mat = new Mat();
            Utils.bitmapToMat(srcBitmap,mat);
            Mat grayMat = new Mat();
            Mat cannyEdges = new Mat();
            Mat hierarchy = new Mat();
            /* 保存所有轮廓列表 */
            contourList = new ArrayList<>();
            /* 将图像转换为灰度 */
            Imgproc.cvtColor(mat,grayMat,Imgproc.COLOR_BGR2GRAY);

            Imgproc.Canny(grayMat,cannyEdges,10,100);

            /* 找出轮廓 */
            Imgproc.findContours(cannyEdges,contourList,hierarchy,Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);

            /* 在新的图像上绘制 */
            Mat contours = new Mat();
            contours.create(cannyEdges.rows(),cannyEdges.cols(), CvType.CV_8UC3);
            Random random = new Random();
            for(int i =0;i<contourList.size();i++){
                Imgproc.drawContours(contours,contourList,i,new Scalar(random.nextInt(255),random.nextInt(255),random.nextInt(255)),-1);
            }
            Bitmap newBitmap = Bitmap.createBitmap(contours.width(),contours.height(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(contours,newBitmap);
            newIv.setImageBitmap(newBitmap);
        return contours;
    }

    /**
     * sobel 检测边缘
     * @param rsId
     * @return
     */
    Mat detectOutLineBySobel(int rsId){
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),rsId);
        Mat src = new Mat();
        Utils.bitmapToMat(srcBitmap,src);

        Mat grayMat = new Mat();
        Mat sobel = new Mat();

        /* 保存梯度 和 绝对梯度 的mat */
        Mat gradX = new Mat();
        Mat absGradX = new Mat();
        Mat gradY = new Mat();
        Mat absGradY = new Mat();
        //转灰度图
        Imgproc.cvtColor(src,grayMat,Imgproc.COLOR_BGR2GRAY);
        //计算水平梯度
        Imgproc.Sobel(grayMat,gradX,CvType.CV_16S,1,0,3,1,0);
        //计算垂直梯度
        Imgproc.Sobel(grayMat,gradY,CvType.CV_16S,0,1,3,1,0);
        //计算绝对梯度
        Core.convertScaleAbs(gradX,absGradX);
        Core.convertScaleAbs(gradY,absGradY);
        //计算结果梯度
        Core.addWeighted(absGradX,0.5,absGradY,0.5,1,sobel);
        Bitmap newBitmap = Bitmap.createBitmap(sobel.width(),sobel.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(sobel,newBitmap);
        newIv.setImageBitmap(newBitmap);
        return sobel;
    }

    /**
     * harris 角点检测
     */
    void detectHarrisCorner(){
        Mat src = new Mat();
        Utils.bitmapToMat(srcBitmap,src);
        Mat grayMat = new Mat();
        Mat corners = new Mat();

        Imgproc.cvtColor(src,grayMat,Imgproc.COLOR_BGR2GRAY);

        Mat tempDst = new Mat();
        Imgproc.cornerHarris(grayMat,tempDst,2,3,0.04);

        Mat tempDstNorm = new Mat();
        Core.normalize(tempDst,tempDstNorm,0,255,Core.NORM_MINMAX);
        Core.convertScaleAbs(tempDstNorm,corners);
        Random r = new Random();
        for(int i=0;i<tempDstNorm.cols();i++){
            for(int j=0;j<tempDstNorm.rows();j++){
                double [] value = tempDstNorm.get(j,i);
                if(value[0] >150)
                    Imgproc.circle(corners,new Point(i,j),5,new Scalar(r.nextInt(255)),2);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(src.width(),src.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(corners,bitmap);
        newIv.setImageBitmap(bitmap);
    }

    /**
     * 检测霍夫圆
     */
    void detectHoughCircles(){
        Mat src = new Mat();
        Utils.bitmapToMat(srcBitmap,src);
        Mat grayMat = new Mat();
        Mat cannyEdges = new Mat();
        Mat circles = new Mat();

        Imgproc.cvtColor(src,grayMat,Imgproc.COLOR_BGR2GRAY);
        Imgproc.Canny(grayMat,cannyEdges,10,100);
        Imgproc.HoughCircles(cannyEdges,circles,Imgproc.CV_HOUGH_GRADIENT,1,cannyEdges.rows()/15);

        Mat houghCircles = new Mat();
        houghCircles.create(cannyEdges.rows(),cannyEdges.cols(),CvType.CV_8UC1);
        for(int i = 0;i<circles.cols();i++){
            double [] parameters = circles.get(0,i);
            double x, y;
            int r;
            x = parameters[0];
            y = parameters[1];
            r = ((int) parameters[2]);

            Point center = new Point(x,y);
            Imgproc.circle(houghCircles,center,r,new Scalar(255,0,0),1);
        }
        Bitmap bitmap = Bitmap.createBitmap(src.width(),src.height(), Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(houghCircles,bitmap);
        newIv.setImageBitmap(bitmap);
    }

    void findWoundContour(Mat src){
        List<MatOfPoint> list = new ArrayList<>();
        Mat hir = new Mat();
        MatOfPoint matOfPoint;
        Imgproc.findContours(src,list,hir,Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        matOfPoint = list.get(0);
    }

    void showArea(){
        int size = contourList.size();
        double area = 0;
        for(int i =0;i<size;i++){

            area += Imgproc.contourArea(contourList.get(i));
        }

        Toast.makeText(this,area+",aa",Toast.LENGTH_SHORT).show();
    }

    private float startX,startY;
    private Rect lastRect;
    View.OnTouchListener onTouchListener =new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    startX = event.getX();
                    startY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float stopX = event.getX();
                    float stopY = event.getY();
                    if(lastRect!=null){
                        paint;
                    }
                    Rect rect = new Rect((int)startX,(int)startY,(int)stopX,(int)stopY);
                    canvas.drawRect(rect,paint);
                    sourceIv.setImageBitmap(srcBitmap);
                    lastRect = rect;
//                    startX = stopX;
//                    startY = stopY;
                    break;
            }
            return true;
        }
    };



}
