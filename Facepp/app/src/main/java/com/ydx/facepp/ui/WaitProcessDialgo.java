package com.ydx.facepp.ui;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facepp.error.FaceppParseException;
import com.ydx.facepp.R;

/**
 * Created by lenovo on 2016/9/21.
 */
public class WaitProcessDialgo {

    private AppCompatActivity appCompatActivity;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private View progressView;
    private TextView process;
    private ProgressBar progressBar;

    public WaitProcessDialgo(AppCompatActivity appCompatActivity){
        this.appCompatActivity=appCompatActivity;
        progressView= LayoutInflater.from(appCompatActivity).inflate(R.layout.view_dialog_wait_process,null);
        process= ((TextView) progressView.findViewById(R.id.tv_process));
        progressBar= ((ProgressBar) progressView.findViewById(R.id.progressBar));
    }
    /**
     * @param title
     */
    public void setdDialog(String title){
        builder=new AlertDialog.Builder(appCompatActivity);
        builder.setTitle(title)
                .setCancelable(false)
                .setView(progressView);
        alertDialog=builder.create();
    }
    float sum=0;
    private int max;
    public void updateProcess(int current,String content){
        sum=sum+current;
        float f=sum/max;
        process.setText(content+ sum);
        progressBar.setProgress(((int) sum));
    }
    
    public void setMaxProcess(int max){
        progressBar.setMax(max);
        this.max=max;
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
    public void hideDialog(){
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
