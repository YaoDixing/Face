package com.ydx.facepp.fingerprinter;

import android.util.Log;

import com.fingerprints.service.FingerprintManager;

/**
 * Created by yaodixing on 2016/10/18.
 */
public class MeizuFingerPrinter {
    private String TAG="MeizuFingerPrinter";
    private static MeizuFingerPrinter ourInstance = new MeizuFingerPrinter();

    public static MeizuFingerPrinter getInstance() {
        return ourInstance;
    }

    private MeizuFingerPrinter() {
    }
    FingerprintManager mFM;
    FingerprintManager.IdentifyCallback mIdentifyCallback ;


    private void initFingPrintManager() {
        if (mFM == null) {
            mFM = FingerprintManager.open(); //调用open方法得到FingerprintManager
        }
    }

    public void startVerify() {
        Log.d(TAG, "startVerify");
        initFingPrintManager(); //得到FingerprintManager实例
        if (mFM.getIds() == null) { //得到系统中已经录入的指纹个数
            Log.d(TAG, "no fingerprints enrolled");
            return;
        }
        if (mIdentifyCallback == null) {
            mIdentifyCallback = createIdentifyCallback(); //创建指纹认证回调函数
        }

        mFM.startIdentify(mIdentifyCallback, mFM.getIds()); //调用指纹认证接口
    }

    private FingerprintManager.IdentifyCallback createIdentifyCallback() {
        return new FingerprintManager.IdentifyCallback() {

            @Override
            public void onIdentified(int id, boolean updated) { //认证成功
                Log.d(TAG, "onIdentified!, fingerId:" + id);
                mFM.release(); //认证成功后release, 需要注意的是在不使用指纹功能后必须要调用release, 也就是说open和release严格配对
                //否则会造成mBack不能使用, 因为只有调用release之后才能从指纹模式切换到back模式
            }

            @Override
            public void onNoMatch() { //认证失败
                Log.d(TAG, "onNoMatch! ");
                startVerify(); //一次认证失败后重新再次发起认证
            }
        };
    }
}
