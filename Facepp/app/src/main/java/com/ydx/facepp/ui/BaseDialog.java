package com.ydx.facepp.ui;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 2016/9/23.
 */
public abstract class BaseDialog {

    private AppCompatActivity appCompatActivity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public BaseDialog(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
        init();
    }

    private void init(){
        builder=new AlertDialog.Builder(appCompatActivity);
        setDialog(builder,appCompatActivity);
        alertDialog=builder.create();
        setDialogTouchOutSideCancel(alertDialog);
    }


    public abstract void setDialog(AlertDialog.Builder builder,AppCompatActivity activity);

    public abstract void setDialogTouchOutSideCancel(AlertDialog alertDialog);

    /**
     *  释放
     */
    public void releaseDialog(){
        alertDialog=null;
        builder=null;
    }

    /**
     * 显示
     */
    public void showDialog(){
        if(alertDialog!=null)
            alertDialog.show();
    }

    /**
     * 隐藏
     */
    public void hideDialog(){
        if(alertDialog!=null)
            alertDialog.hide();
    }


    public interface OnDialogClick{
        void onCancel(int state);
        void onOK(int state);
    }

    public OnDialogClick onDailogClick;
    public void setOnDailogClick(OnDialogClick onDailogClick){

        this.onDailogClick=onDailogClick;
    }
}
