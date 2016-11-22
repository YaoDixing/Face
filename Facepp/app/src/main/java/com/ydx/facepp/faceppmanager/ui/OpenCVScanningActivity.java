package com.ydx.facepp.faceppmanager.ui;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.Config;
import com.ydx.facepp.R;
import com.ydx.facepp.faceppmanager.FacePPManager;
import com.ydx.facepp.opencv.DetectionStateEnum;
import com.ydx.facepp.opencv.FaceDetector;
import com.ydx.facepp.opencv.FaceMat;
import com.ydx.facepp.permission.DangerousPermissionEnum;
import com.ydx.facepp.phone_status.PhoneStatusManager;
import com.ydx.facepp.util.ImageUtil;
import com.ydx.facepp.util.PreferenceUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by lenovo on 2016/9/18.
 */
public class OpenCVScanningActivity extends BaseActivity {
    public enum ScanningType{
        trainPerson(1),
        validPerson(2),
        addFaceToFaceSet(3),
        recognitionInSet(4),
        recognitionInGroup(5),
        findPerson(6);
        int value;
        private  ScanningType(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
        public static ScanningType getByValue(int value){
            switch (value){
                case 1:
                    return trainPerson;
                case 2:
                    return validPerson;
                case 3:
                    return addFaceToFaceSet;
                case 4:
                    return recognitionInSet;
                case 5:
                    return recognitionInGroup;
                case 6:
                    return findPerson;
                default:
                    return null;
            }
        }
    }
    TextView tv;
    File train1;
    File check;
    private PreferenceUtil pf;
    private JavaCameraView cameraGLSurfaceView;
    private ScanningType type;
    private String faceSetName;
    private FaceDetector faceDetector;
    private String xmlFilePath;
    private Switch switchCamera;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opencv_scanning);
        FacePPManager.getInstance().initRequest();
        chkPhoneStatePermission();


    }

    private void chkPhoneStatePermission(){
        if(permissionManager.isOverAndroidM()){
            permissionManager.checkPermission(DangerousPermissionEnum.READ_PHONE_STATE,DangerousPermissionEnum.READ_PHONE_STATE.getReqCode());
        }else {
            initData();
            findView();
            initPhoneInfo();
        }
    }

    private String phoneInfo;
    private void initPhoneInfo(){
        phoneInfo =  PhoneStatusManager.getInstance().getPhoneInfo(this);
        Log.i("phoneInfo:",phoneInfo);
    }
    private void initData(){
        faceDetector=FaceDetector.getInstance();
        type=ScanningType.getByValue(getIntent().getIntExtra(Config.PF_KEY_SCANNING_TYPE,1));
        pf=new PreferenceUtil(this,"facepp",MODE_PRIVATE);
        faceSetName=pf.getString(Config.PF_KEY_FACE_SET_NAME,"");
        xmlFilePath=pf.getString(Config.PF_KEY_HAARCASCADE_FRONTALFACE_ALT2,"");
        xmlFilePath= Utils.exportResource(this,R.raw.haarcascade_frontalface_alt2);
        faceDetector.init(xmlFilePath);
        Log.i("xmlFilePath",xmlFilePath);
        File faceDir =new File(Environment.getExternalStorageDirectory().getPath()+"/"+getPackageName()+"/face");
        if(!faceDir.exists())
            faceDir.mkdirs();
        String path=faceDir.getPath();
        train1=new File(path+"/train1.jpg");
        check=new File(path+"/check.jpg");
        try {
            if(!train1.exists()){
                train1.createNewFile();
            }
            if(!check.exists()){
                check.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LinearLayout similarPersonLay;
    private Button reSetBtn;
    private Button clearConsole;
    private ScrollView scroll;
    private void findView() {
        initCamera();
        reSetBtn = ((Button) findViewById(R.id.reSet));
        reSetBtn.setOnClickListener((view) -> reSet());
        clearConsole = ((Button) findViewById(R.id.clearConsole));
        clearConsole.setOnClickListener((view) -> clearConsole());
        scroll = ((ScrollView) findViewById(R.id.scroll));
        similarPersonLay = ((LinearLayout) findViewById(R.id.similar_person_lay));
        tv = (TextView) findViewById(R.id.title);
        switchCamera = ((Switch) findViewById(R.id.switch1));
        if (cameraIsFront) {
            switchCamera.setChecked(true);
        } else {
            switchCamera.setChecked(false);
        }
        switchCamera.setOnCheckedChangeListener(
                (CompoundButton buttonView, boolean isChecked) ->
                {
                    cameraIsFront = isChecked;
                    cameraSwitch(isChecked);
                }

        );
    }


    public enum CameraIndexEnum{
        any(-1),
        front(98),
        back(99);
        private int value;
        private CameraIndexEnum(int value){
            this.value=value;
        }


    }
    private void cameraSwitch(boolean isFront){
        reSet();
        if(isFront){
            cameraGLSurfaceView.setCameraIndex(CameraIndexEnum.front.value);

        }else {
            cameraGLSurfaceView.setCameraIndex(CameraIndexEnum.back.value);

        }
        cameraGLSurfaceView.disableView();
        cameraGLSurfaceView.enableView();
    }
    private boolean cameraIsFront;
    private void initCamera(){
        cameraGLSurfaceView= ((JavaCameraView) findViewById(R.id.cv_surface_view));
        int cameraId=cameraGLSurfaceView.getCameraIndex();
        if(cameraId==CameraIndexEnum.any.value||cameraId==CameraIndexEnum.back.value){
            cameraIsFront=false;
        }else {
            cameraIsFront=true;
        }
        if(Build.VERSION.SDK_INT>=21){
            cameraGLSurfaceView.setCvCameraViewListener(cvCameraViewListener2);
        }else {
            cameraGLSurfaceView.setCvCameraViewListener(cvCameraViewListener);
        }
    }

    private Mat transposeMat(Mat mat){
        Mat mat1 = new Mat();
        Mat dst  = new Mat();
        Mat dst1 = new Mat();
        //转置
        Core.transpose(mat,mat1);
        if(cameraIsFront) {
            Core.flip(mat1, dst, 0); //flipCode=0  x
            Core.flip(dst, dst1, 1); //flipCode>0  y
            return dst1;
        }else {
            if(phoneInfo.contains("Nexus 5X")){
                Core.flip(mat1, dst, 0);
                return dst;
            }else {
                Core.flip(mat1, dst, 1);
                return dst;
            }

        }
    }

    CameraBridgeViewBase.CvCameraViewListener2 cvCameraViewListener2=new CameraBridgeViewBase.CvCameraViewListener2(){

        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
            Mat mat = inputFrame.rgba();
            Mat dst = transposeMat(mat);
            FaceMat faceMat = faceDetector.detector(dst);
            if(faceMat.isHasFace()&&detectionStateEnum==DetectionStateEnum.toBeDetected) {
                detectionStateEnum=DetectionStateEnum.detecting;
                scanning(dst);
            }
            return dst ;
        }
    };

    private static DetectionStateEnum detectionStateEnum=DetectionStateEnum.toBeDetected;
    CameraBridgeViewBase.CvCameraViewListener cvCameraViewListener=new CameraBridgeViewBase.CvCameraViewListener() {
        @Override
        public void onCameraViewStarted(int width, int height) {

        }

        @Override
        public void onCameraViewStopped() {

        }

        @Override
        public Mat onCameraFrame(Mat inputFrame) {
            Mat dst = transposeMat(inputFrame);
            FaceMat faceMat = faceDetector.detector(dst);
            if(faceMat.isHasFace()&&detectionStateEnum==DetectionStateEnum.toBeDetected) {
                detectionStateEnum=DetectionStateEnum.detecting;
                scanning(dst);
            }
            return dst;

        }
    };



    @Override
    protected void onResume() {
        super.onResume();
        reSet();
        if(cameraGLSurfaceView!=null){
            cameraGLSurfaceView.enableView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraGLSurfaceView!=null) {
            cameraGLSurfaceView.disableView();
        }
    }

    @Override
    public void onGranted(int requestCode) {
        if(requestCode==DangerousPermissionEnum.READ_PHONE_STATE.getReqCode()){
            initData();
            findView();
            initPhoneInfo();
        }
    }

    @Override
    public void onRefused(int requestCode) {
        if(requestCode==DangerousPermissionEnum.READ_PHONE_STATE.getReqCode()){
            finish();
        }
    }

    private void scanning(final Mat mat){
        if(type==ScanningType.trainPerson){
            new Thread( () -> checkFace(mat,train1) ).start();
        }else if(type==ScanningType.validPerson){
            new Thread( () ->  checkFace(mat,check) ).start();
        }else if(type==ScanningType.addFaceToFaceSet){
            new Thread( () -> checkFace(mat,train1) ).start();
        }else if(type==ScanningType.recognitionInSet){
            new Thread( () ->  checkFace(mat,check) ).start();
        }else if(type==ScanningType.recognitionInGroup){
            new Thread( () ->  checkFace(mat,check) ).start();
        }else if(type==ScanningType.findPerson){
            new Thread( () ->  checkFace(mat,check) ).start();
        }


    }
    private ArrayList<String > trainFaceIds=new ArrayList<>();

    private void reSet(){
        detectionStateEnum=DetectionStateEnum.toBeDetected;
        trainFaceIds.clear();
        searchSetCount=0;
        findPersonCount=0;
    }

    private void clearConsole(){
        similarPersonLay.post( () -> similarPersonLay.removeAllViews());
    }

    private void checkFace(Mat mat,File file){
        Bitmap bitmap = Bitmap.createBitmap(mat.cols(),mat.rows(),Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat,bitmap);
        ImageUtil.saveBitmapToFile(bitmap,file, Bitmap.CompressFormat.JPEG,60);
        String faceId="";
        ArrayList<String> faceIds=null;
        if(type==ScanningType.recognitionInSet||type==ScanningType.findPerson){
            faceIds = FacePPManager.getInstance().getFaceIds(file);
            if(faceIds!=null&&faceIds.size()>0) {
                trainFaceIds = faceIds;
                detectionStateEnum=DetectionStateEnum.detected;
                addSetSimlaryMsg("本次检测到"+faceIds.size()+"张脸");
            }else {
                reSet();
                return;
            }

        }else {
            faceId = FacePPManager.getInstance().getFaceId(file);
            if(faceId!=null&&!faceId.isEmpty()){
                trainFaceIds.add(faceId);
                detectionStateEnum=DetectionStateEnum.detected;
                addSetSimlaryMsg("本次检测到"+trainFaceIds.size()+"张脸");
            }else {
                reSet();
                return;
            }
        }

        if(type==ScanningType.trainPerson) {
            addFaceToPerson();
        }else if(type==ScanningType.validPerson) {
            validPerson();
        }else if(type==ScanningType.addFaceToFaceSet){
            addFaceToFaceSet(faceSetName);
        }else if(type==ScanningType.recognitionInSet){
            searchInFaceSet();
        }else if(type==ScanningType.recognitionInGroup){
            searchInGroup();
        }else if(type==ScanningType.findPerson){
            findPerson();
        }
    }
    private void addSetSimlaryMsg(final String msg){
        getHandler().post( () -> {
            TextView textView = new TextView(OpenCVScanningActivity.this);
            textView.setText(msg);
            similarPersonLay.addView(textView);
            int off = similarPersonLay.getMeasuredHeight() - scroll.getHeight();
            if (off > 0) {
                scroll.scrollTo(0, off);
            }
        });
    }

    /**
     * add face to person
     */
    private void addFaceToPerson(){
        if(!trainFaceIds.isEmpty()){
            JSONObject result= FacePPManager.getInstance().addFaceToPerson(pf.getString(Config.PF_KEY_PERSON_NAME, ""),trainFaceIds);
            try {
                if(result.getBoolean("success")){
                    addFaceToFaceSet("faceSet1");
                    trainPerson();
                }else {
                    addFaceToPerson();
                }
            }catch (JSONException e){
                e.printStackTrace();
                showMsg(e.getMessage());
            }

        }
    }

    private void addFaceToFaceSet(String faceSetName){
        if(!trainFaceIds.isEmpty()){
            JSONObject result=  FacePPManager.getInstance().addFaceToFaceSet(faceSetName,trainFaceIds);
            try {
                if(result.getBoolean("success")){
                    addSetSimlaryMsg("成功添加到"+faceSetName+" "+result.getInt("added")+"张face");
                    trainFaceSet();
                }else {
                    addFaceToFaceSet(faceSetName);
                }
            }catch (JSONException e){
                e.printStackTrace();
                showMsg(e.getMessage());
            }

        }

    }

    private void trainPerson(){
        String sessionId= FacePPManager.getInstance().trainVerify(pf.getString(Config.PF_KEY_PERSON_NAME,""));
        JSONObject result=FacePPManager.getInstance().getResultBySessionId(sessionId);
        try {
            addSetSimlaryMsg("Person改动后训练结果："+result.getJSONObject("result").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void trainFaceSet(){
        String sessionId= FacePPManager.getInstance().trainSearch(faceSetName);
        JSONObject result=FacePPManager.getInstance().getResultBySessionId(sessionId);
        try {
            addSetSimlaryMsg("FaceSet改动后训练结果："+result.getJSONObject("result").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void validPerson(){
        if(trainFaceIds.size()>0){
            JSONObject result= FacePPManager.getInstance().checIsSamePerson(trainFaceIds.get(0),pf.getString(Config.PF_KEY_PERSON_NAME, ""));
            try {
                if(result.getBoolean("is_same_person")){
                    addSetSimlaryMsg("is the same person");
                }else {
                    addSetSimlaryMsg("is not the same person");
                }
            }catch (JSONException e){
                e.printStackTrace();
                showMsg(e.getMessage());
            }

        }
    }

    int searchSetCount=0;
    private void searchInFaceSet(){
        searchSetCount++;
        if(!trainFaceIds.isEmpty()){
            addSetSimlaryMsg("正在识别第"+searchSetCount+"张脸:");
            JSONObject result= FacePPManager.getInstance().recognitionFromFaceSet(trainFaceIds.get(searchSetCount-1),faceSetName,1);
            try {
                final JSONArray array=result.getJSONArray("candidate");
                if(array!=null&&array.length()>0) {
                    addSetSimlaryMsg("返回" + array.length() + "张精确度最高的照片");
                }else {
                    addSetSimlaryMsg("匹配到" +0 + "张照片");
                    return;
                }

                for (int i=0;i<array.length();i++){
                    String faceId= array.getJSONObject(i).getString("face_id");
                    String similarty= array.getJSONObject(i).getString("similarity");
                    addSetSimlaryMsg("第"+(i+1)+"张相似度："+similarty);
                }

                String faceId = array.getJSONObject(0).getString("face_id");
                ArrayList<String> faceIds = new ArrayList<>();
                faceIds.add(faceId);
                JSONObject obj = FacePPManager.getInstance().getFaceInfo(faceIds);
                JSONArray faceInfo = obj.getJSONArray("face_info");
                if (faceInfo != null && faceInfo.length() > 0) {
                    final JSONArray persons = faceInfo.getJSONObject(0).getJSONArray("person");
                    if (persons != null && persons.length() > 0) {
                        for (int i = 0; i < persons.length(); i++) {
                            addSetSimlaryMsg("是：" + persons.getJSONObject(i).getString("person_name"));

                        }
                    }else {
                        // 此faceid 无对应 person 所以 从faceset中移除
                        addSetSimlaryMsg("相似度最高的 face 无对应 person 所以从FaceSet中移除");
                       JSONObject removeResult= FacePPManager.getInstance().removeFaceOfFaceSet(faceSetName,faceIds);
                        if(removeResult!=null){
                            addSetSimlaryMsg("删除了"+removeResult.getString("removed")+"个face，状态："+removeResult.getString("success"));
                        }
                    }
                }
                if(searchSetCount<trainFaceIds.size()){
                    searchInFaceSet();
                }else {
                    reSet();
                }
            }catch (JSONException e){
                e.printStackTrace();
                showMsg(e.getMessage());
            }

        }
    }

    int findPersonCount=0;
    private void findPerson(){
        findPersonCount++;
        addSetSimlaryMsg("正在识别第"+findPersonCount+"张脸");
        JSONObject result= FacePPManager.getInstance().checIsSamePerson(trainFaceIds.get(findPersonCount-1),pf.getString(Config.PF_KEY_PERSON_NAME,""));
        try {
            if(result.getBoolean("is_same_person")){
                addSetSimlaryMsg("是这个人");
                findPersonCount=0;
            }else {
                if(findPersonCount<trainFaceIds.size()) {
                    addSetSimlaryMsg("不是这个人");
                    findPerson();
                }else {
                    addSetSimlaryMsg("不是这个人");
                    reSet();
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
            showMsg(e.getMessage());
        }
    }

    private void searchInGroup(){

    }

}
