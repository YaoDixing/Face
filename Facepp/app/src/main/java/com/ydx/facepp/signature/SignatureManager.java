package com.ydx.facepp.signature;

import android.content.Context;

/**
 * Created by lenovo on 2016/9/23.
 */
public class SignatureManager {

    private static SignatureManager ourInstance = new SignatureManager();

    public static SignatureManager getInstance() {
        return ourInstance;
    }

    private SignatureManager() {
    }

    private Context context;

    public void init(Context context){

    }
}
