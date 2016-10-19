package com.ydx.facepp.home.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.Config;
import com.ydx.facepp.R;
import com.ydx.facepp.faceppmanager.FacePPManager;
import com.ydx.facepp.faceppmanager.FaceppDialog;
import com.ydx.facepp.faceppmanager.FaceppExceptionHandler;
import com.ydx.facepp.faceppmanager.FaceppMethodEnum;
import com.ydx.facepp.faceppmanager.ui.PersonalFaceManageActivity;
import com.ydx.facepp.faceset.FaceSetManagerActivity;
import com.ydx.facepp.fingerprinter.FingerManager;
import com.ydx.facepp.fingerprinter.FingerPrinterDialog;
import com.ydx.facepp.fingerprinter.DeviceTypeEnum;
import com.ydx.facepp.fingerprinter.FingerStatusEnum;
import com.ydx.facepp.fingerprinter.MeizuFingerPrinter;
import com.ydx.facepp.fingerprinter.SamsungFingerPrinter;
import com.ydx.facepp.permission.DangerousPermissionEnum;
import com.ydx.facepp.phone_status.PhoneStatusManager;
import com.ydx.facepp.signature.ui.SignatureActivity;
import com.ydx.facepp.ui.BaseDialog;
import com.ydx.facepp.ui.WaitProcessDialgo;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private FaceppExceptionHandler exceptionHandler;
    private Button faceSet1Btn;
    private Button finger;
    private Button signature;
    private FacePPManager facePPManager;
    WaitProcessDialgo waitProcessDialgo;
    FingerPrinterDialog fingerPrinterDialog;
    Handler progressHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x111:
                    int max=msg.arg1;
                    waitProcessDialgo.setMaxProcess(max);
                    break;
                case 0x112:
                    waitProcessDialgo.updateProcess(msg.arg1,"初始化检测文件中");
                    break;
                case 0x113:
                    waitProcessDialgo.hideDialog();
                    waitProcessDialgo.releaseDialog();
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        facePPManager=FacePPManager.getInstance();
        facePPManager.initRequest();
        faceSet1Btn= ((Button) findViewById(R.id.face_set_1));
        finger= ((Button) findViewById(R.id.finger));
        signature= ((Button) findViewById(R.id.signature));
        finger.setOnClickListener(this);
        signature.setOnClickListener(this);
        faceSet1Btn.setOnClickListener(this);
        fingerPrinterDialog=new FingerPrinterDialog(this);
        fingerPrinterDialog.setOnDailogClick(fingerDialogClick);

        if(!pf.getBoolean(Config.PF_KEY_IS_OK_HAARCASCADE_FRONTALFACE_ALT2,false)){

        }
        if(permissionManager.isOverAndroidM()){
            List<DangerousPermissionEnum> list=new ArrayList<>();
            list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
            list.add(DangerousPermissionEnum.READ_PHONE_STATE);
            permissionManager.checkPermission(list,0x233);
        }else {
            initFinger();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        initExceptionHandler();
    }

    /**
     * 从 raw中读出  xml 写入本地
     */
    private void initDetectorXMLFile(){

        final InputStream in= getResources().openRawResource(R.raw.haarcascade_frontalface_alt2);

        try {
            Message message=new Message();
            message.arg1=in.available();
            message.what=0x111;
            progressHandler.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String path=  "/data/data/"+getPackageName()+"/files/DetectorXml";
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath=path+"/haarcascade_frontalface_alt2.xml";
        File xmlFile=new File(filePath);
        if(!xmlFile.exists()){
            try {
                xmlFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileOutputStream out=new FileOutputStream(xmlFile);
            int byteCount;
            byte [] bytes=new byte[1024];
            while ((byteCount=in.read(bytes))!=-1){
                out.write(bytes);
                updateDailog(byteCount);
                Log.i("bytecount:",byteCount+"");
            }
            out.flush();
            releaseDialog();
            pf.put(Config.PF_KEY_IS_OK_HAARCASCADE_FRONTALFACE_ALT2,true);
            pf.commit();

        } catch (IOException e) {
            e.printStackTrace();
        }
        pf.put(Config.PF_KEY_HAARCASCADE_FRONTALFACE_ALT2,xmlFile.getPath());
        pf.commit();
    }
    private void releaseDialog(){
        Message message=new Message();
        message.what=0x113;
        progressHandler.sendMessage(message);


    }
    int process;
    private void updateDailog(final int b){
        Message message=new Message();
        message.arg1=b;
        message.what=0x112;
        progressHandler.sendMessage(message);
    };

    private void initExceptionHandler(){
        exceptionHandler= new FaceppExceptionHandler(this);
        exceptionHandler.setiNeedDialogClickListener(new FaceppExceptionHandler.INeedDialogClickListener() {
            @Override
            public void onNeed(FaceppMethodEnum methodEnum) {
                switch (methodEnum){
                    case personCreate:
                        exceptionHandler.setOnDialogClick(new FaceppDialog.OnDialogClick() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onOK() {
                                Intent intent=new Intent();
                                intent.setClass(MainActivity.this, PersonalFaceManageActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                    case faceSetCreate:
                        exceptionHandler.setOnDialogClick(new FaceppDialog.OnDialogClick() {
                            @Override
                            public void onCancel() {

                            }

                            @Override
                            public void onOK() {
                                Intent intent = new Intent();
                                pf.put(Config.PF_KEY_FACE_SET_NAME,"faceSet1");
                                pf.commit();
                                intent.setClass(MainActivity.this, FaceSetManagerActivity.class);
                                startActivity(intent);
                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    public void onGranted(int requestCode) {
        if(requestCode==0x233){
            initFinger();
        }
    }

    @Override
    public void onRefused(int requestCode) {
        if(requestCode==0x233){
            android.os.Process.killProcess(Process.myPid());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.face_set_1:
                createFaceSet1();
                break;
            case R.id.finger:
                identify();
                break;
            case R.id.signature:
                goSignature();
                break;

        }
    }

    private void createFaceSet1(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject result=facePPManager.createFaceSet("faceSet1",null,null);
                if(result!=null){
                    showMsg("create faceSet success");
                    Intent intent=new Intent();
                    pf.put(Config.PF_KEY_FACE_SET_NAME,"faceSet1");
                    pf.commit();
                    intent.setClass(MainActivity.this, FaceSetManagerActivity.class);
                    startActivity(intent);
                }
            }
        }).start();

    }
    private DeviceTypeEnum deviceTypeEnum;
    FingerManager fingerManager;
    private boolean fingerAvailable;
    private void initFinger(){
        String phoneInfo =  PhoneStatusManager.getInstance().getPhoneInfo(this);
        Log.i("phoneInfo:",phoneInfo);
        if(isSamsungDevice()) {
            showMsg(phoneInfo);
            deviceTypeEnum= DeviceTypeEnum.samsung;
        }else if(Build.MANUFACTURER.equals("Xiaomi")){
            showMsg(phoneInfo);
            deviceTypeEnum= DeviceTypeEnum.xiaomi;
        }else if(Build.BRAND.equals("Meizu")&&!fingerManager.isOverAndroidM()){
            deviceTypeEnum= DeviceTypeEnum.meizu;
        }
        else {
            deviceTypeEnum= DeviceTypeEnum.other;
            initMFingerPrinter();
        }


    }

    private void useMeizuPrinter(){
        MeizuFingerPrinter.getInstance().startVerify();
    }

    private void initMFingerPrinter(){
        fingerManager= FingerManager.getInstance();
        if(fingerManager.init(this)){
            if(fingerManager.fingerIsAvailable()){
                //有可用的指纹
                fingerAvailable=true;
            }else {
                //设备没有添加指纹
                fingerPrinterDialog.setPrintState("设备没有添加指纹", FingerStatusEnum.noFinger.value);
                fingerPrinterDialog.showDialog();
            }
        }else {
            fingerPrinterDialog.setPrintState("系统版本或设备不支持指纹功能", FingerStatusEnum.noFinger.value);
            fingerPrinterDialog.showDialog();
        }
    }
    private void identify(){

            if(fingerAvailable){
                switch (deviceTypeEnum){
                    case samsung:
                        if(SAMSUNG_STATE.equals("")) {
                            SamsungFingerPrinter.getInstance().startIdentification();
                        }else {
                            fingerPrinterDialog.setPrintState(SAMSUNG_STATE, FingerStatusEnum.noFinger.value);
                            fingerPrinterDialog.showDialog();
                        }
                        break;
                    case meizu:
                        useMeizuPrinter();
                        break;
                    case xiaomi:
                        break;
                    case other:
                        fingerManager.auth(callback);
                        fingerPrinterDialog.setPrintState("请触摸指纹传感器",FingerStatusEnum.inRecognition.value);
                        fingerPrinterDialog.showDialog();
                        break;
                }

            }



    }
    private String SAMSUNG_STATE;
    private boolean isSamsungDevice(){
        SAMSUNG_STATE= SamsungFingerPrinter.getInstance().init(this);
        if(SAMSUNG_STATE.equals("This is not Samsung device"))
            return false;
        SamsungFingerPrinter.getInstance().setOnIdentifyResult(new SamsungFingerPrinter.OnIdentifyResult() {
            @Override
            public void onSuccess() {
                showMsg("认证成功");
            }

            @Override
            public void onFailed(String msg) {
                showMsg(msg);
            }
        });
        return true;
    }


    FingerprintManagerCompat.AuthenticationCallback callback=new FingerprintManagerCompat.AuthenticationCallback() {
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            super.onAuthenticationError(errMsgId, errString);
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            super.onAuthenticationHelp(helpMsgId, helpString);
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            fingerPrinterDialog.setPrintState("识别成功", FingerStatusEnum.success.value);
            fingerManager.stopsFingerPrintListen();
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            fingerPrinterDialog.setPrintState("识别失败", FingerStatusEnum.fail.value);
            fingerManager.stopsFingerPrintListen();
        }
    };



    BaseDialog.OnDialogClick fingerDialogClick=new BaseDialog.OnDialogClick() {
        @Override
        public void onCancel(int state) {
            fingerPrinterDialog.hideDialog();
        }

        @Override
        public void onOK(int state) {
            if(state==FingerStatusEnum.inRecognition.value){
                fingerPrinterDialog.hideDialog();
            }else if(state==FingerStatusEnum.success.value){
                fingerPrinterDialog.hideDialog();
            }else if(state==FingerStatusEnum.fail.value){
                fingerPrinterDialog.hideDialog();
            }else if(state==FingerStatusEnum.noSupport.value){
                fingerPrinterDialog.hideDialog();
            }else if(state==FingerStatusEnum.noFinger.value){
                fingerPrinterDialog.hideDialog();
            }
        }
    };


    private void goSignature(){
        Intent intent=new Intent();
        intent.setClass(this, SignatureActivity.class);
        startActivity(intent);
    }
}
