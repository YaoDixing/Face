package com.yaodixing.wounddetection;

import android.app.Application;

import org.opencv.android.OpenCVLoader;

/**
 * Created by yaodixing on 2016/11/22.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OpenCVLoader.initDebug(true);
    }
}
