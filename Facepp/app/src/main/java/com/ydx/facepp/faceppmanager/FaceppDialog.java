package com.ydx.facepp.faceppmanager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;

import com.facepp.error.FaceppParseException;

/**
 * Created by lenovo on 2016/9/5.
 */
public class FaceppDialog {
    private AppCompatActivity appCompatActivity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public FaceppDialog(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
    }
    /**
     * 设置用户已存在对话框
     * @param title
     * @param content
     */
    public void setdDialog(String title,String content){
        builder=new AlertDialog.Builder(appCompatActivity);
        builder.setTitle(title)
                .setCancelable(false)
                .setMessage(content)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideDialog();
                        releaseDialog();
                        if(onDailogClick!=null){
                            onDailogClick.onOK();
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hideDialog();
                        releaseDialog();
                        if(onDailogClick!=null) {
                            onDailogClick.onCancel();
                        }
                    }
                });
        alertDialog=builder.create();
    }

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
    private void hideDialog(){
        if(alertDialog!=null)
            alertDialog.hide();
    }




    /**
     * 权限提示
     * @param e
     * @return
     */
    public String getMsg(FaceppParseException e){
        return e.getErrorMessage();
    }



    public interface OnDialogClick{
        void onCancel();
        void onOK();
    }

    public OnDialogClick onDailogClick;
    public void setOnDailogClick(OnDialogClick onDailogClick){

        this.onDailogClick=onDailogClick;
    }
}
