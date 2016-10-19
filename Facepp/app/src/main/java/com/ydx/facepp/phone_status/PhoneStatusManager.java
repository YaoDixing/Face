package com.ydx.facepp.phone_status;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * Created by lenovo on 2016/9/26.
 */
public class PhoneStatusManager {
    private static PhoneStatusManager ourInstance = new PhoneStatusManager();

    public static PhoneStatusManager getInstance() {
        return ourInstance;
    }

    private PhoneStatusManager() {
    }

    public String getPhoneInfo(Context context){
        TelephonyManager telephonyManager= ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        StringBuilder sb=new StringBuilder();
        sb.append("型号:");
        sb.append(Build.MODEL+",");
        sb.append("厂商:");
        sb.append(Build.MANUFACTURER+",");
        return sb.toString();
    }
}
