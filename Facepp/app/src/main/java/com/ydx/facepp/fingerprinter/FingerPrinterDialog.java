package com.ydx.facepp.fingerprinter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ydx.facepp.R;
import com.ydx.facepp.ui.BaseDialog;

/**
 * Created by lenovo on 2016/9/23.
 */
public class FingerPrinterDialog extends BaseDialog{
    private AppCompatActivity activity;
    public FingerPrinterDialog(AppCompatActivity activity){
        super(activity);
    }
    TextView printStateTxt;
    @Override
    public void setDialog(AlertDialog.Builder builder,AppCompatActivity activity) {
        View view= LayoutInflater.from(activity).inflate(R.layout.dialog_finger_printer,null);
        printStateTxt= ((TextView) view.findViewById(R.id.fingerprint_status));
        builder.setView(view)
                .setPositiveButton("确定",positiveClick)
                .setNegativeButton("取消",negativeClick);
    }

    @Override
    public void setDialogTouchOutSideCancel(AlertDialog alertDialog) {
        alertDialog.setCanceledOnTouchOutside(false);
    }

    int state;
    public void setPrintState(String txt,int state){
        printStateTxt.setText(txt);
        this.state=state;
    }
    DialogInterface.OnClickListener negativeClick =new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(onDailogClick!=null){
                onDailogClick.onCancel(state);
            }
        }
    };

    DialogInterface.OnClickListener positiveClick =new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            if(onDailogClick!=null){
                onDailogClick.onOK(state);
            }
        }
    };
}
