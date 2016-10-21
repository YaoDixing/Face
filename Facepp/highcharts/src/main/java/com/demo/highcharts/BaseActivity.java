package com.demo.highcharts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;


import com.yaodixing.permissionlibrary.permission.DangerousPermissionEnum;
import com.yaodixing.permissionlibrary.permission.IPermissionState;
import com.yaodixing.permissionlibrary.permission.PermissionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.0以上会初始化权限管理器
 * Created by lenovo on 2016/9/9.
 */

public abstract class BaseActivity extends AppCompatActivity implements IPermissionState {
//    public PreferenceUtil pf;
    public String TAG="";
    private Context mContext;
    public static PermissionManager permissionManager;//权限管理器，做有关危险权限操作前，必须先checkPermission
    private static Handler handler=new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
//        pf=new PreferenceUtil(this,"facepp",MODE_PRIVATE);
        initPermission();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(permissionManager!=null) {
            permissionManager.release();
        }
    }

    private void initPermission(){
        if(permissionManager==null) {
            permissionManager = PermissionManager.getInstance();
        }
        if(permissionManager.isNeedInit(this)){
            permissionManager.setIPermissionState(this);
        }

    }
    //子类实现权限赋予后的操作
    public abstract void onGranted(int requestCode);
    //子类实现权限拒绝后的操作
    public abstract void onRefused(int requestCode);
    @Override
    public void onGrant(int requestCode) {
        onGranted(requestCode);

    }

    @Override
    public void onRefuse(int requestCode) {
        onRefused(requestCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        permissionManager.onActivityResult(requestCode,resultCode,data);
    }

    public Handler getHandler(){
        return handler;
    }
    public void showMsg(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void hideActionBar(){
        getSupportActionBar().hide();
    }

    public List<DangerousPermissionEnum> getAppNeedPermission(){
        List<DangerousPermissionEnum> list =new ArrayList<>();
        list.add(DangerousPermissionEnum.READ_PHONE_STATE);
        list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
        return  list;

    }

    public void setBtnEnable(final Button button, final boolean flag){
        getHandler().post(new Runnable() {
            @Override
            public void run() {
                button.setEnabled(flag);
            }
        });
    }

}
