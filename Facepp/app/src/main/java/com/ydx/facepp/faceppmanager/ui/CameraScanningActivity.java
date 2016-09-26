package com.ydx.facepp.faceppmanager.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;

import org.opencv.android.JavaCameraView;

/**
 * Created by lenovo on 2016/9/20.
 */
public class CameraScanningActivity extends JavaCameraView implements SurfaceHolder.Callback{

    public CameraScanningActivity(Context context, int cameraId) {
        super(context, cameraId);
    }

    public CameraScanningActivity(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


}
