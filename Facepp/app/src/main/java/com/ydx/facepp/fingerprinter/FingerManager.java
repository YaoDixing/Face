package com.ydx.facepp.fingerprinter;

import android.content.Context;
import android.os.Build;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;

/**
 * Created by lenovo on 2016/9/23.
 */
public class FingerManager {
    private FingerprintManagerCompat fingerprintManager;
    private static FingerManager ourInstance = new FingerManager();

    public static FingerManager getInstance() {
        return ourInstance;
    }

    private FingerManager() {
    }

    private boolean deviceHasSupport;
    private CryptoObjectHelper cryptoObjectHelper;

    private CancellationSignal cancellationSignal;
    public boolean init(Context context){
        fingerprintManager= FingerprintManagerCompat.from(context);
        if(isOverAndroidM()){
            if(deviceIsSupport()){

                return true;
            }
        }
        return false;
    }

    public boolean isOverAndroidM(){
        if(Build.VERSION.SDK_INT>=23){
            return true;
        }else {
            return false;
        }
    }


    private boolean deviceIsSupport(){
        return fingerprintManager.isHardwareDetected();
    }

    public boolean fingerIsAvailable(){
         return fingerprintManager.hasEnrolledFingerprints();
    }

    public void auth(FingerprintManagerCompat.AuthenticationCallback callback){
        try {
            cryptoObjectHelper = new CryptoObjectHelper();
            cancellationSignal=  new CancellationSignal();
            fingerprintManager.authenticate(cryptoObjectHelper.buildCryptoObject(),0,cancellationSignal,callback,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void stopsFingerPrintListen(){
        cancellationSignal.cancel();
        cancellationSignal=null;
        cryptoObjectHelper=null;
    }

}
