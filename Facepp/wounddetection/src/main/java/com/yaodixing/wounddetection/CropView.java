package com.yaodixing.wounddetection;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yaodixing on 2016/11/24.
 */
public class CropView  extends View{
    public CropView(Context context) {
        this(context,null);
    }

    public CropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    android.support.v7.app.AlertDialog dialog;
    Paint paint;
    Rect rect;
    int startX=0;
    int startY=0;
    void init(Context context){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
        builder.setTitle("截取伤口")
                .setMessage("截取伤口确认无误？")
                .setCancelable(false)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        rect.set(0,0,0,0);
                        invalidate();
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if(onCropWound!=null){
                    onCropWound.onCropWound(rect);
                }
            }
        });
        dialog = builder.create();
        paint = new Paint();
        rect = new Rect();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.RED);
        this.setBackgroundColor(Color.parseColor("#00000000"));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                int stopX,stopY;
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX = (int) event.getX();
                        startY = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        stopX = (int) event.getX();
                        stopY = (int) event.getY();
                        if(stopX>=startX&& stopY >= startX) {
                            rect.set(startX, startY, stopX, stopY);
                        }else if(stopX>=startX && stopY <= startY){
                            rect.set( startX,stopY, stopX, startY);
                        }else if(stopX <= startX && stopY >= startY){
                            rect.set(stopX,startY,startX,stopY);
                        }else if(stopX<= startX && stopY <= startY){
                            rect.set(stopX,stopY,startX,startY);
                        }
//                        Log.i("xy",startX+","+startY+","+stopX+","+stopY);
                        invalidate();
                        break;
                    case MotionEvent.ACTION_UP:
                        if(dialog!=null)
                            dialog.show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(rect,paint);
        super.onDraw(canvas);
    }
    public interface OnCropWound{
        void onCropWound(Rect rect);
    }
    private OnCropWound onCropWound;

    public void setOnCropWound(OnCropWound onCropWound){
        this.onCropWound = onCropWound;
    }
}
