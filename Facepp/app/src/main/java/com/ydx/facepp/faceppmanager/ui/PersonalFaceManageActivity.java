package com.ydx.facepp.faceppmanager.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.Config;
import com.ydx.facepp.ImageSelector;
import com.ydx.facepp.R;
import com.ydx.facepp.faceppmanager.FacePPManager;
import com.ydx.facepp.permission.DangerousPermissionEnum;
import com.ydx.facepp.util.PreferenceUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * Created by lenovo on 2016/9/13.
 */
public class PersonalFaceManageActivity  extends BaseActivity implements View.OnClickListener{

    private Button takeFaceBtn;
    private Button check;
    private Button scan;
    private Button chk;
    private Button valid;
    private PreferenceUtil pf;
    private String personName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_personal);
        takeFaceBtn= ((Button) findViewById(R.id.take_face_pic));
        check= ((Button) findViewById(R.id.check));
        scan= ((Button) findViewById(R.id.scanUpload));
        chk= ((Button) findViewById(R.id.chk));
        valid= ((Button) findViewById(R.id.scanCheck));
        chk.setOnClickListener(this);
        check.setOnClickListener(this);
        takeFaceBtn.setOnClickListener(this);
        scan.setOnClickListener(this);
        valid.setOnClickListener(this);
        pf=new PreferenceUtil(this,"facepp",MODE_PRIVATE);
        personName=pf.getString("personName","");

    }



    @Override
    public void onGranted(int requestCode) {
        switch (requestCode){
            case Config.REQ_CODE_ADD_PERSON_FACE_IMAGE:
            case Config.REQ_CODE_CHECK_PERSON_FACE_IMAGE:
                ImageSelector.selectSingleImage(this,requestCode);
                break;
            case Config.REQ_CODE_SCAN_FACE:
                goScaningFace();
                break;
            case Config.REQ_CODE_valid_FACE:
                goValidFace();
                break;
        }

    }

    @Override
    public void onRefused(int requestCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.take_face_pic:
                if(permissionManager.isOverAndroidM()){
                    List<DangerousPermissionEnum> list=new ArrayList<>();
                    list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
                    list.add(DangerousPermissionEnum.CAMEAR);
                    permissionManager.checkPermission(list, Config.REQ_CODE_ADD_PERSON_FACE_IMAGE);
                }else {
                    ImageSelector.selectSingleImage(this,Config.REQ_CODE_ADD_PERSON_FACE_IMAGE);
                }

                break;
            case R.id.check:
                if(permissionManager.isOverAndroidM()){
                    List<DangerousPermissionEnum> list=new ArrayList<>();
                    list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
                    list.add(DangerousPermissionEnum.CAMEAR);
                    permissionManager.checkPermission(list,Config.REQ_CODE_CHECK_PERSON_FACE_IMAGE);
                }else {
                    ImageSelector.selectSingleImage(this,Config.REQ_CODE_CHECK_PERSON_FACE_IMAGE);
                }
                break;
            case R.id.scanUpload:
                if(permissionManager.isOverAndroidM()){
                    List<DangerousPermissionEnum> list=new ArrayList<>();
                    list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
                    list.add(DangerousPermissionEnum.CAMEAR);
                    permissionManager.checkPermission(list,Config.REQ_CODE_SCAN_FACE);
                }else {
                   goScaningFace();
                }
                break;
            case R.id.scanCheck:
                if(permissionManager.isOverAndroidM()){
                    List<DangerousPermissionEnum> list=new ArrayList<>();
                    list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
                    list.add(DangerousPermissionEnum.CAMEAR);
                    permissionManager.checkPermission(list,Config.REQ_CODE_valid_FACE);
                }else {
                    goValidFace();
                }
                break;
            case R.id.chk:
                String path= Environment.getExternalStorageDirectory().getPath();
                path=path+"/"+getPackageName();
                final File ff=new File(path+"/a"+8+""+8+".jpg");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FacePPManager.getInstance().checkFace(ff);
                    }
                }).start();

                break;
        }
    }

    private void goScaningFace(){
        Intent intent=new Intent();
        intent.putExtra(Config.PF_KEY_SCANNING_TYPE, OpenCVScanningActivity.ScanningType.trainPerson.getValue());
        intent.setClass(this,OpenCVScanningActivity.class);
        startActivity(intent);
    }

    private void goValidFace(){
        Intent intent=new Intent();
        intent.putExtra(Config.PF_KEY_SCANNING_TYPE, OpenCVScanningActivity.ScanningType.validPerson.getValue());
        intent.setClass(this,OpenCVScanningActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode==Config.REQ_CODE_ADD_PERSON_FACE_IMAGE){
            final byte[] bytes= praseImg(data);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String faceId= getFaceId(bytes);
                    JSONObject add= addFaceIdtoPerson(faceId);
                    try {
                        showMsg("added:"+add.getInt("added"));
                        showMsg("success:"+add.getBoolean("success"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        if(requestCode==Config.REQ_CODE_CHECK_PERSON_FACE_IMAGE){
            final byte[] bytes= praseImg(data);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String faceId= getFaceId(bytes);
                    JSONObject check= checkIsSamePerson(faceId);
                    try {
                        showMsg("confidence:"+check.getString("confidence"));
                        showMsg("is_same_person:"+check.getBoolean("is_same_person"));
                        train();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }
    }


    private byte[] praseImg(Intent data){
        List<String> paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
        if(paths!=null){
            for (String path:paths){
                BitmapFactory.Options options=new BitmapFactory.Options();
                options.inJustDecodeBounds=true;
                options.inSampleSize=4;
                BitmapFactory.decodeFile(path,options);
                options.inJustDecodeBounds=false;
                final Bitmap bitmap=BitmapFactory.decodeFile(path,options);
                return  Bitmap2Bytes(bitmap);
            }
        }
        return new byte[]{};
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return baos.toByteArray();
    }

    private String getFaceId(final byte[] bytes){

        JSONObject jsonObject= FacePPManager.getInstance().checkFace(bytes);
        try {
            String faceId=  jsonObject.getJSONArray("face").getJSONObject(0).getString("face_id");
            return faceId;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
    private JSONObject addFaceIdtoPerson(String faceId){
        ArrayList<String> ids=new ArrayList<>();
        ids.add(faceId);
        return FacePPManager.getInstance().addFaceToPerson(personName,ids);
    }

    private JSONObject checkIsSamePerson(String faceId){
        return FacePPManager.getInstance().checIsSamePerson(faceId,personName);
    }


    private void train(){
        String sessionId= FacePPManager.getInstance().trainVerify(personName);
        FacePPManager.getInstance().getResultBySessionId(sessionId);

    }

}
