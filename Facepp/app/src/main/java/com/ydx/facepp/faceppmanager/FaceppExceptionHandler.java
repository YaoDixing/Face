package com.ydx.facepp.faceppmanager;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facepp.error.FaceppParseException;

/**
 * Created by lenovo on 2016/9/13.
 */
public class FaceppExceptionHandler implements IFaceppCallBack{
    private AppCompatActivity activity;
    private FaceppDialog dialog;
    private Handler handler=new Handler();
    private FacePPManager facePPManager;
    public FaceppExceptionHandler(AppCompatActivity activity){
        this.activity=activity;
        dialog=new FaceppDialog(activity);
       facePPManager= FacePPManager.getInstance();
        facePPManager.initIFaceppCallBack(this);
    }

    public interface INeedDialogClickListener{
        void onNeed(FaceppMethodEnum methodEnum);
    }
    private INeedDialogClickListener iNeedDialogClickListener;
    public void setiNeedDialogClickListener(INeedDialogClickListener iNeedDialogClickListener){
        this.iNeedDialogClickListener=iNeedDialogClickListener;
    }
    @Override
    public void onFaceppFail(final FaceppParseException e, FaceppMethodEnum methodEnum) {
        //NAME_EXIST
        if(e.getMessage().contains("code=1503")){
            // valid is which method
            switch (methodEnum){
                case personCreate:
                    iNeedDialogClickListener.onNeed(methodEnum);
                    setDialog("提示","用户已存在,确定要进入此用户face管理页面吗？", onDialogClick);
                    break;
                case groupCreate:
                    setDialog("提示","群组已存在", null);
                    break;
                case faceSetCreate:
                    iNeedDialogClickListener.onNeed(methodEnum);
                    setDialog("提示","faceSet exist", onDialogClick);
                    break;
            }
        }
        else if(e.getMessage().contains("code=1001")){
            showMsg("INTERNAL_ERROR");
        }
        //INVALID_ARGUMENTS
        else if(e.getMessage().contains("code=1005")){
            switch (methodEnum){
                case personGetInfo:
                    if(e.getMessage().contains("person_name does not exist"))
                        showMsg("用户不存在");
                    break;
            }
        }
    }

    /**
     * 设置弹出提示 dialog
     * @param title
     * @param content
     * @param onDialogClick
     */
    private void setDialog(final String title, final String content , FaceppDialog.OnDialogClick onDialogClick ){
        dialog.setOnDailogClick(onDialogClick);
        handler.post(new Runnable() {
            @Override
            public void run() {
                dialog.setdDialog(title,content);
                dialog.showDialog();
            }
        });
    }

    FaceppDialog.OnDialogClick onDialogClick;
    public void setOnDialogClick(FaceppDialog.OnDialogClick onDialogClick){
        this.onDialogClick=onDialogClick;
    }


    public void showMsg(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
