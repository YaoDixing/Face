package com.ydx.facepp.faceppmanager;

import com.facepp.error.FaceppParseException;

/**
 * Created by lenovo on 2016/9/13.
 */
public interface IFaceppCallBack {
    void onFaceppFail(FaceppParseException e,FaceppMethodEnum methodEnum);
}
