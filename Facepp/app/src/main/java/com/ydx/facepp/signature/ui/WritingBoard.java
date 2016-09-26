package com.ydx.facepp.signature.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.ydx.facepp.util.ImageUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/23.
 */
public class WritingBoard extends View implements View.OnTouchListener{
    private Context context;
    private boolean isOnWordCutMode;
    WritingBoard writingBoard;
    private List<Bitmap> allBitmaps=new ArrayList<>();
    public WritingBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    public void setIsOnWordCutMode(boolean isOnWordCutMode){
        this.isOnWordCutMode=isOnWordCutMode;
    }

    private Paint paint;
    private File signature;
    private void init(){
        writingBoard=this;
        paint=new Paint();
        setOnTouchListener(this);
        initFile();
    }

    private void initFile(){
        String path= Environment.getExternalStorageDirectory().getPath()+"/"+context.getPackageName()+"/signature";
        File filePath=new File(path);
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        File signature=new File(path+"/signature.jpg");
        if(!signature.exists()){
            try {
                signature.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.signature=signature;
    }
    public void setPaintColor(int color){
        paint.setColor(color);
    }

    public void setStrokeWidth(int size){
        paint.setStrokeWidth(size);
    }

    private Canvas canvas;

    public void addPoint(MyPoint point){
        p.add(point);

    }
    List<MyPoint> p=new ArrayList<>();


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        MyPoint lastPoint=null;
        for(MyPoint point:p) {
            if(point.isDownPoint){
                canvas.drawPoint(point.point.x, point.point.y, paint);
                lastPoint=point;
            }else {
                canvas.drawLine(lastPoint.point.x,lastPoint.point.y,point.point.x,point.point.y,paint);
                lastPoint=point;
            }
        }

    }
    public List<TimeThread> threads=new ArrayList<>();
    int i=0;
    private int currentThreadTag;
    private void startTime(){
      final TimeThread thread= new TimeThread(timeRunnable,i,true);
        currentThreadTag=i;
        i++;
        threads.add(thread);
        thread.start();
        Log.i("start thread:",currentThreadTag+"");
    }



    Runnable timeRunnable= new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                if(Thread.currentThread() instanceof TimeThread){
                    TimeThread timeThread= ((TimeThread) Thread.currentThread());
                    onSecondPast(timeThread);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private OnOneWordCut onOneWordCut;
    public interface OnOneWordCut{
        void onOnWordCut(Bitmap bitmap);
        void onCompositeWord(Bitmap bitmap);
    }
    public void setOnOneWordCut(OnOneWordCut onOneWordCut){
        this.onOneWordCut=onOneWordCut;
    }


    private List<Bitmap> oneWordBitmaps=new ArrayList<>();

    public void onSecondPast(final TimeThread timeThread) {
        this.post(new Runnable() {
            @Override
            public void run() {
                if(isOnWordCutMode&&timeThread.isEffective){
                    if(onOneWordCut!=null){
                        Bitmap bitmap=cut();
                        oneWordBitmaps.add(bitmap);
                        onOneWordCut.onOnWordCut(bitmap);
                        p.clear();
                        postInvalidate();
                    }
                }
            }
        });

    }

    public void compositeWord(){
        int count=oneWordBitmaps.size();
        if(count>0) {
            int newWidth=0;
            for (Bitmap b:oneWordBitmaps){
                newWidth+=b.getWidth();
            }
            Bitmap bitmap = Bitmap.createBitmap(newWidth, this.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            int left=0;
            for (int i = 0; i < oneWordBitmaps.size(); i++) {
                canvas.drawBitmap(oneWordBitmaps.get(i),left,0, null);
                left+=oneWordBitmaps.get(i).getWidth();
            }
            onOneWordCut.onCompositeWord(bitmap);
            allBitmaps.add(bitmap);
            //保存到本地
            ImageUtil.saveBitmapToFile(bitmap,signature, Bitmap.CompressFormat.JPEG,80);
            releaseOneWordBitmaps();
        }
    }

    private void releaseOneWordBitmaps(){
        for(Bitmap bitmap:oneWordBitmaps){
           if(bitmap!=null&&!bitmap.isRecycled()){
               bitmap.recycle();
           }
        }
        oneWordBitmaps.clear();
        System.gc();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Log.d("MotionEvent:",event.getAction()+"");
        Point point=new Point((int) event.getX(), (int) event.getY());
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(isOnWordCutMode){
                    if(threads.size()>0){
                        Log.i("stop thread:",currentThreadTag+"");
                        TimeThread timeThread=threads.get(currentThreadTag);
                        if(timeThread!=null) {
                            timeThread.isEffective = false;
                        }
                    }
                }
                addPoint(new MyPoint(point,true));
                break;
            case MotionEvent.ACTION_MOVE:
                addPoint(new MyPoint(point,false));
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                addPoint(new MyPoint(point,false));
                postInvalidate();
                if(isOnWordCutMode){
                    startTime();
                }
                break;
        }
        return true;
    }

    public Bitmap cut(){
        return convertViewToBitmap(this);
    }
    public Bitmap convertViewToBitmap(View view){
        if(p.size()<0){
            return null;
        }
        //遍历每一个点的  x  坐标  空白= x右极限 -x左极限
        int maxX;
        int minX;
        int[] x=new int[p.size()];
        for(int i=0;i<x.length;i++){
            x[i]=p.get(i).point.x;
        }
        sortN(x);
        minX=x[0];
        maxX=x[x.length-1];
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        //利用bitmap生成画布
        Canvas canvas = new Canvas(bitmap);

        //把view中的内容绘制在画布上
        view.draw(canvas);

        Bitmap newBitmap=Bitmap.createBitmap(maxX-minX,view.getHeight(), Bitmap.Config.ARGB_8888);
        //去掉左右空白
        canvas=new Canvas(newBitmap);
        Rect src = new Rect(minX,0,maxX,view.getHeight());
        Rect dst = new Rect(0,0,maxX-minX,view.getHeight());
        canvas.drawBitmap(bitmap,src,dst,null);
        allBitmaps.add(bitmap);
        allBitmaps.add(newBitmap);
        //保存到本地
        ImageUtil.saveBitmapToFile(newBitmap,signature, Bitmap.CompressFormat.JPEG,80);
        return newBitmap;
    }

    private void sortN(int [] n){
        for(int j=0;j<n.length-1;j++) {
            for (int i = 0; i < n.length - 1; i++) {
                if (n[i + 1] - n[i] < 0) {
                    int m = n[i + 1];
                    n[i + 1] = n[i];
                    n[i] = m;
                }
            }
        }
    }

    public void reSet(){
        p.clear();
        invalidate();
        for(Bitmap bitmap:allBitmaps){
            if(bitmap!=null&&!bitmap.isRecycled()){
                bitmap.recycle();
            }
        }
        allBitmaps.clear();
        System.gc();
    }

    class MyPoint{
        public Point point;
        public boolean isDownPoint;

        public MyPoint(Point point, boolean isDownPoint) {
            this.point = point;
            this.isDownPoint = isDownPoint;
        }

    }

    class TimeThread extends Thread{
        public int tag;
        public boolean isEffective;
        public TimeThread(Runnable runnable, int tag,boolean isEffective) {
            super(runnable);
            this.tag = tag;
            this.isEffective=isEffective;
        }
    }
}
