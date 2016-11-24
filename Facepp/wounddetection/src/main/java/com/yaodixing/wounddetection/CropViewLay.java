package com.yaodixing.wounddetection;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by yaodixing on 2016/11/24.
 */
public class CropViewLay extends FrameLayout{
    private Context context;
    private FrameLayout rootView;
    private ImageView srcIv;
    private CropView transparentIv;

    private Canvas canvas;
    private Paint paint ;
    public CropViewLay(Context context) {
        this(context,null);
    }

    public CropViewLay(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public void setOnCropListener(CropView.OnCropWound listener){
        transparentIv.setOnCropWound(listener);
    }

    void init(){
        rootView = ((FrameLayout) LayoutInflater.from(context).inflate(R.layout.crop_view, this));
        srcIv = ((ImageView) rootView.findViewById(R.id.iv_src));
        transparentIv = ((CropView) rootView.findViewById(R.id.iv_transparent));
        srcIv.post(new Runnable() {
            @Override
            public void run() {
                int width = srcIv.getWidth();
                int height = srcIv.getHeight();
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(width,height);
                params.gravity = Gravity.CENTER;
                transparentIv.setLayoutParams(params);
//                initCanvas(width,height);
//                transparentIv.setOnTouchListener(transparentTouchListener);
            }
        });
    }
//    private float startX,startY;
//    private Rect lastRect;
//   OnTouchListener transparentTouchListener = new OnTouchListener() {
//       @Override
//       public boolean onTouch(View view, MotionEvent event) {
//           switch (event.getAction()){
//               case MotionEvent.ACTION_DOWN:
//
//                   startX = event.getX();
//                   startY = event.getY();
//                   break;
//               case MotionEvent.ACTION_MOVE:
//                   float stopX = event.getX();
//                   float stopY = event.getY();
//                   if(!(stopX-startX>=2&&stopY-startY>=2))
//                       break;
//
//                   paint.setStyle(Paint.Style.STROKE);
//                   paint.setColor(Color.RED);
//                   Rect rect = new Rect((int)startX,(int)startY,(int)stopX,(int)stopY);
//                   Rect transparentRect = new Rect((int)startX-2,(int)startY-2,(int)stopX-2,(int)stopY-2);
//                   canvas.restore();
//                   canvas.save();
//                   canvas.drawRect(rect,paint);
////                   paint.setColor(Color.WHITE);
////                   paint.setStyle(Paint.Style.FILL);
////                   canvas.drawRect(transparentRect,paint);
//                   transparentIv.setImageBitmap(transparentBitmap);
////                    startX = stopX;
////                    startY = stopY;
//                   break;
//               case MotionEvent.ACTION_UP:
//                   // TODO: 2016/11/24 show dialog
//                   break;
//           }
//           return true;
//       }
//   };
//    Bitmap transparentBitmap;
//    void initCanvas(int width ,int height){
//        paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStrokeWidth(2);
//        paint.setStyle(Paint.Style.STROKE);
//        transparentBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
//        canvas = new Canvas(transparentBitmap);
//        transparentIv.setImageBitmap(transparentBitmap);
//        canvas.save();
//    }
}
