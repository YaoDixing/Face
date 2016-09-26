package com.ydx.facepp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.opencv.android.OpenCVLoader;

/**
 * Created by lenovo on 2016/9/18.
 */
public class FaceApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
//        LoaderCallbackInterface loaderCallbackInterface=new LoaderCallbackInterface() {
//            @Override
//            public void onManagerConnected(int status) {
//                Log.i("opencv init status:",""+status);
//            }
//
//            @Override
//            public void onPackageInstall(int operation, InstallCallbackInterface callback) {
//                // install openCV
//                callback.install();
//            }
//        };


        /**
         * 初始化openCV
         */
//        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0,this,loaderCallbackInterface);


        OpenCVLoader.initDebug(true);
    }
}
