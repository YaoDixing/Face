package com.yaodixing.permissionlibrary.permission;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 需要检查权限 或 请求权限的页面 继承此类
 * 运行时权限请求基类
 * Created by ydx on 16-8-11.
 */
public  class PermissionManager {
    public static int REQUEST_CODE_SETTING=211;
    private static int REQUEST_CODE_WRITE_SETTING=212;
    private static PermissionManager permissionManager=new PermissionManager();
    private List<DangerousPermissionEnum> list;
    private AppCompatActivity activity;
    private int currentPos;
    private DangerousPermissionEnum currentReqPermission;
    private PermissionDialog permissionDialog;
    private IPermissionState IPermissionState;
    private int requestCode;

    public boolean isNeedInit(AppCompatActivity activity){
        if(Build.VERSION.SDK_INT>=23){
            init((activity));
            return true;
        }
        return false;
    }

    public boolean isOverAndroidM(){
        if(Build.VERSION.SDK_INT>=23)
            return true;
        return false;
    }

    public void checkWriteSetting(){
        if(Build.VERSION.SDK_INT>=23) {
            if (!Settings.System.canWrite(activity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                        Uri.parse("package:" + activity.getPackageName()));
                activity.startActivityForResult(intent, REQUEST_CODE_WRITE_SETTING);
            }
        }
    }


    public void setIPermissionState(IPermissionState IPermissionState){
        this.IPermissionState=IPermissionState;
    }

    /**
     * onDestroy 调用
     */
    public void release(){
        if(permissionDialog!=null) {
            permissionDialog.releaseRemindDialog();
        }
    }

    public static PermissionManager getInstance(){
        return  permissionManager;
    }

    private void init(AppCompatActivity activity){
        this.activity=activity;
        permissionDialog=new PermissionDialog(activity);
        permissionDialog.setOnPermissonDailogClick(new PermissionDialog.OnPermissonDailogClick() {
            @Override
            public void onCancel() {
                if(IPermissionState!=null){
                    IPermissionState.onRefuse(requestCode);
                }
            }

            @Override
            public void onOK() {

            }
        });
    }

    /**
     * 一个页面只检查一个权限的调用
     * @param dangerousPermissionEnum
     */
    public void checkPermission(DangerousPermissionEnum dangerousPermissionEnum,int requestCode){
        List<DangerousPermissionEnum> list=new ArrayList<>();
        list.add(dangerousPermissionEnum);
        this.list=list;
        this.requestCode=requestCode;
        currentPos=0;
        check(dangerousPermissionEnum);
    }

    /**
     * 检查权限
     * @param list
     */
    public void checkPermission(List<DangerousPermissionEnum> list,int requestCode){
        this.list=list;
        this.requestCode=requestCode;
        //请求第一个权限
        if(list!=null&&list.size()>0){
            currentPos=0;
            check(list.get(0));
        }
//        check(list,0x11);

    }



    /**
     * 检查权限
     * @param dangerousPermissionEnum
     */
    private void check(DangerousPermissionEnum dangerousPermissionEnum){
        currentReqPermission=dangerousPermissionEnum;
        int state= ActivityCompat.checkSelfPermission(activity,dangerousPermissionEnum.getValue());
        if(state==PackageManager.PERMISSION_GRANTED){
            checkNext();
        }else {
            reqPermission(dangerousPermissionEnum);
        }
    }

    /**
     * 检查权限列表中的下一个权限
     */
    private void checkNext(){

        currentPos++;
        if(currentPos==list.size()){
          if(IPermissionState!=null){
              IPermissionState.onGrant(requestCode);
          }
        }else {
            currentReqPermission = list.get(currentPos);
            check(currentReqPermission);
        }
    }

    /**
     * 请求权限
     * @param dangerousPermissionEnum
     */
    private void reqPermission(DangerousPermissionEnum dangerousPermissionEnum){
        ActivityCompat.requestPermissions(activity,new String[]{dangerousPermissionEnum.getValue()},dangerousPermissionEnum.getReqCode());
    }


    /**
     * 请求权限结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults==null||grantResults.length==0)
            return;
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
            checkNext();
        }else {
            permissionDialog.setRemindDialog("权限申请",permissionDialog.getMsg(currentReqPermission));
            permissionDialog.showRemindDialog();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_SETTING){
            check(currentReqPermission);
        } else if (requestCode == REQUEST_CODE_WRITE_SETTING) {
            if(Build.VERSION.SDK_INT>=23) {
                if (Settings.System.canWrite(activity)) {
                    //检查返回结果
//                    Toast.makeText(activity, "WRITE_SETTINGS permission granted", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(activity, "WRITE_SETTINGS permission not granted", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
