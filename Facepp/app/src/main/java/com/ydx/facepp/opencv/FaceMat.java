package com.ydx.facepp.opencv;

import org.opencv.core.Mat;

/**
 * Created by lenovo on 2016/9/26.
 */
public class FaceMat {
    private Mat mat;
    private boolean hasFace;

    public FaceMat(Mat mat, boolean hasFace) {
        this.mat = mat;
        this.hasFace = hasFace;
    }

    public boolean isHasFace() {
        return hasFace;
    }

    public void setHasFace(boolean hasFace) {
        this.hasFace = hasFace;
    }

    public Mat getMat() {
        return mat;
    }

    public void setMat(Mat mat) {
        this.mat = mat;
    }
}
