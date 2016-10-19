package com.ydx.facepp.fingerprinter;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pass.Spass;
import com.samsung.android.sdk.pass.SpassFingerprint;

/**
 * Created by yaodixing on 2016/10/19.
 */
public class SamsungFingerPrinter {

    private static SamsungFingerPrinter  instance= new SamsungFingerPrinter();
    private SamsungFingerPrinter(){

    }
    public static SamsungFingerPrinter getInstance(){
        return instance;
    }

    private Context context;
    private boolean isSupport;
    private SpassFingerprint spassFingerprint;
    private boolean needRetryIdentify = false;

    public interface OnIdentifyResult{
        void onSuccess();
        void onFailed(String msg);
    }
    private OnIdentifyResult onIdentifyResult;
    public void setOnIdentifyResult(OnIdentifyResult onIdentifyResult){
        this.onIdentifyResult=onIdentifyResult;
    }

    //指纹监听
    private SpassFingerprint.IdentifyListener  listener= new SpassFingerprint.IdentifyListener(){

        @Override
        public void onFinished(int eventStatus) {
            // called when fingerprint identification is finished
            if(eventStatus==SpassFingerprint.STATUS_AUTHENTIFICATION_SUCCESS){
                //fingerprint success
                spassFingerprint.setDialogTitle("认证成功", 0xff0000);
                onIdentifyResult.onSuccess();
            }else if(eventStatus==SpassFingerprint.STATUS_AUTHENTIFICATION_PASSWORD_SUCCESS){
                // native password success
                spassFingerprint.setDialogTitle("认证成功", 0xff0000);
                onIdentifyResult.onSuccess();
            }else {
                if(eventStatus==SpassFingerprint.STATUS_TIMEOUT_FAILED) {
                    spassFingerprint.setDialogTitle("认证超时", 0xff0000);
//                    onIdentifyResult.onFailed("identify time out");
                }else if(eventStatus==SpassFingerprint.STATUS_USER_CANCELLED||eventStatus==SpassFingerprint.STATUS_USER_CANCELLED_BY_TOUCH_OUTSIDE){
                    spassFingerprint.setDialogTitle("取消认证", 0xff0000);
//                    onIdentifyResult.onFailed("User canceled");
                } else if (!spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT_AVAILABLE_PASSWORD)) {
                    if (eventStatus == SpassFingerprint.STATUS_BUTTON_PRESSED) {
                        Toast.makeText(context, "Please connect own Backup Menu", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    spassFingerprint.setDialogTitle("认证失败", 0xff0000);
                    onIdentifyResult.onFailed("Authentification Fail for identify");
                }

            }

        }

        @Override
        public void onReady() {
            spassFingerprint.setDialogTitle("请触摸指纹传感器...", 0xff0000);
            //called when the fingerprint sensor is in the ready state
        }

        @Override
        public void onStarted() {
            //called when the user touches the fingerprint sensor and starts to swipe their finger
            spassFingerprint.setDialogTitle("认证中...", 0xff0000);
        }

        @Override
        public void onCompleted() {
            //called when the identify request is completed

        }
    };
    // 添加指纹
//    private SpassFingerprint.RegisterListener  registerListener=() -> {
//        if(checkExistsFinger()){
//
//        }else {
//
//        }
//    };
    private  Spass spass;
    public String  init(Context context){
        this.context=context;
        spass=new Spass();
        try {
            spass.initialize(context);
        } catch (SsdkUnsupportedException e) {
            e.printStackTrace();
            if(e.getMessage().contains("This is not Samsung device")){
                return "This is not Samsung device";
            }
            switch (e.getType()){
                case 0: return "VENDOR_NOT_SUPPORTED";
                case 1: return "DEVICE_NOT_SUPPORTED";
                case 2: return "LIBRARY_NOT_INSTALLED";
                case 3: return "LIBRARY_UPDATE_IS_REQUIRED";
                case 4: return "LIBRARY_UPDATE_IS_RECOMMENDED";
            }
        }
        isSupport =spass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);
        if(!isSupport){
            return "Fingerprint Service is not supported in the device";
        }
        spassFingerprint= new SpassFingerprint(context);
        spassFingerprint.setDialogIcon("ic_fp_40px");
        spassFingerprint.setDialogTitle("指纹认证", 0xff0000);
        if(checkExistsFinger())
            return "";
        else
            return "请先添加指纹！";
    }

    /**
     * 添加指纹
     */
//    public void registFinger(){
//        spassFingerprint.registerFinger(context,registerListener);
//    }

    /**
     * 检查是否存在指纹
     */
    public boolean checkExistsFinger(){
        return  spassFingerprint.hasRegisteredFinger();
    }

    public void startIdentification(){
        spassFingerprint.startIdentifyWithDialog(context,listener,true);
    }
}
