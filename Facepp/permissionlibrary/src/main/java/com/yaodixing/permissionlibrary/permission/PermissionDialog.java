package com.yaodixing.permissionlibrary.permission;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 2016/9/5.
 */
public class PermissionDialog {
    private AppCompatActivity appCompatActivity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public PermissionDialog(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }
    /**
     * 设置权限提示对话框
     * @param title
     * @param content
     */
    public void setRemindDialog(String title,String content){
        builder=new AlertDialog.Builder(appCompatActivity);
        builder.setTitle(title)
                .setCancelable(false)
                .setMessage(content)
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideRemindDialog();
                        releaseRemindDialog();
                        startSetting();
                        if(onPermissonDailogClick!=null){
                            onPermissonDailogClick.onOK();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideRemindDialog();
                        releaseRemindDialog();
                        if(onPermissonDailogClick!=null) {
                            onPermissonDailogClick.onCancel();
                        }
                    }
                });
        alertDialog=builder.create();
    }

    /**
     *  释放
     */
    public void releaseRemindDialog(){
        alertDialog=null;
        builder=null;
    }

    /**
     * 显示
     */
    public void showRemindDialog(){
        if(alertDialog!=null)
            alertDialog.show();
    }

    /**
     * 隐藏
     */
    private void hideRemindDialog(){
        if(alertDialog!=null)
            alertDialog.hide();
    }


    /**
     * 跳转到设置界面
     */
    private void startSetting() {
        try {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    .setData(Uri.parse("package:" + appCompatActivity.getPackageName()));
            appCompatActivity.startActivityForResult(intent, PermissionManager.REQUEST_CODE_SETTING);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                appCompatActivity.startActivityForResult(intent, PermissionManager.REQUEST_CODE_SETTING);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 权限提示
     * @param dangerousPermissionEnum
     * @return
     */
    public String getMsg(DangerousPermissionEnum dangerousPermissionEnum){
        String info="";
        switch (dangerousPermissionEnum.getReqCode()){
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CONTACT:
                info=  "请允许获得联系人信息权限" ;
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CALENDAR:
                info=  "请允许获得日历权限" ;
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_CAMERA:
                info=  "请允许获得相机权限" ;
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_LOCATION:
                info=  "请允许获得定位权限" ;
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_MICROPHONE:
                info=  "请允许获得录音权限" ;
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_PHONE:
                info=  "请允许获得电话权限";
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SENSORS:
                info=  "请允许获得传感器权限";
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_SMS:
                info=  "请允许获得短信权限";
                break;
            case DangerousPermissionReqCode.PERMISSION_REQ_CODE_GROUP_STORAGE:
                info=  "请允许获得数据读写权限";
                break;
        }
        return info;
    }



    public interface OnPermissonDailogClick{
        void onCancel();
        void onOK();
    }

    OnPermissonDailogClick onPermissonDailogClick;
    public void setOnPermissonDailogClick(OnPermissonDailogClick onPermissonDailogClick){
        this.onPermissonDailogClick=onPermissonDailogClick;
    }
}
