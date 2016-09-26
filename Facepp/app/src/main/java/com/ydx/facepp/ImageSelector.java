package com.ydx.facepp;

import android.app.Activity;
import android.support.v4.app.Fragment;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * Created by lenovo on 2016/9/13.
 */
public class ImageSelector {

    public static void selectSingleImage(Activity activity, int reqCode){
        MultiImageSelector.create()
                .showCamera(true)
                .single()
                .start(activity,reqCode);
    }
    public static void selectSingleImage(Fragment fragment, int reqCode){
        MultiImageSelector.create()
                .showCamera(true)
                .single()
                .start(fragment,reqCode);
    }


    public static void selectMultiImages(Activity activity,int reqCode,int maxNum){
        MultiImageSelector.create()
                .showCamera(true)
                .count(maxNum)
                .multi()
                .start(activity,reqCode);
    }
    public static void selectMultiImages(Fragment fragment, int reqCode, int maxNum){
        MultiImageSelector.create()
                .showCamera(true)
                .count(maxNum)
                .multi()
                .start(fragment,reqCode);
    }

    public static void selectMultiImagesWithHistory(Activity activity, int reqCode, int maxNum, ArrayList<String> his){
        MultiImageSelector.create()
                .showCamera(true)
                .count(maxNum)
                .multi()
                .origin(his)
                .start(activity,reqCode);
    }
    public static void selectMultiImagesWithHistory(Fragment fragment, int reqCode, int maxNum, ArrayList<String> his){
        MultiImageSelector.create()
                .showCamera(true)
                .count(maxNum)
                .multi()
                .origin(his)
                .start(fragment,reqCode);
    }
}
