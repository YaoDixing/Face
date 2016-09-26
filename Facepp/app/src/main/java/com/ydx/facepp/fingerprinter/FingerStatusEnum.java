package com.ydx.facepp.fingerprinter;

/**
 * Created by lenovo on 2016/9/23.
 */
public enum FingerStatusEnum {
    inRecognition(0),
    success(1),
    fail(2),
    noFinger(3),
    noSupport(4);
    public int value;
    private FingerStatusEnum(int value){
        this.value=value;
    }
}
