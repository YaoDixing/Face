package com.ydx.facepp.faceset;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.Config;
import com.ydx.facepp.R;
import com.ydx.facepp.faceppmanager.FacePPManager;
import com.ydx.facepp.faceppmanager.FaceppDialog;
import com.ydx.facepp.faceppmanager.FaceppExceptionHandler;
import com.ydx.facepp.faceppmanager.FaceppMethodEnum;
import com.ydx.facepp.faceppmanager.ui.OpenCVScanningActivity;
import com.ydx.facepp.faceppmanager.ui.PersonalFaceManageActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/9/20.
 */
public class FaceSetManagerActivity extends BaseActivity implements View.OnClickListener{
    private String faceSetName;
    private FaceppExceptionHandler exceptionHandler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_set_manager);
        getIntentData();
        findView();
        setOnclickListener();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initExceptionHandler();
    }

    private void getIntentData(){
        faceSetName=pf.getString(Config.PF_KEY_FACE_SET_NAME,"");
    }

    private Button scanAddToFaceSetBtn;
    private Button showFacesBtn;
    private Button searchInSetBtn;
    private EditText editText;
    private EditText validEdit;
    private Button validSure;
    private Button sure;
    private void findView(){
        scanAddToFaceSetBtn= ((Button) findViewById(R.id.scan_and_add_to_face_set));
        showFacesBtn= ((Button) findViewById(R.id.show_faces));
        searchInSetBtn= ((Button) findViewById(R.id.search_in_set));

        validEdit = ((EditText) findViewById(R.id.valid_edit));
        validSure = ((Button) findViewById(R.id.valid_sure));

        editText= ((EditText) findViewById(R.id.editText));
        sure= ((Button) findViewById(R.id.sure));

    }

    private void setOnclickListener(){
        scanAddToFaceSetBtn.setOnClickListener(this);
        showFacesBtn.setOnClickListener(this);
        searchInSetBtn.setOnClickListener(this);
        sure.setOnClickListener(this);
        validSure.setOnClickListener(this);
    }



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
                                intent.setClass(FaceSetManagerActivity.this, PersonalFaceManageActivity.class);
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

    }

    @Override
    public void onRefused(int requestCode) {

    }

    private String findName,personName;

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.valid_sure:
                setBtnEnable(validSure,false);
                if(validEditText(validEdit)){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            findName=validEdit.getText().toString();
                            FacePPManager.getInstance().getPersonInfo(validEdit.getText().toString());
                            pf.put("personName", findName);
                            pf.commit();
                            Intent intent=new Intent(FaceSetManagerActivity.this,OpenCVScanningActivity.class);
                            intent.putExtra(Config.PF_KEY_SCANNING_TYPE, OpenCVScanningActivity.ScanningType.findPerson.getValue());
                            startActivity(intent);
                            setBtnEnable(validSure,true);
                        }
                    }).start();
                }else {
                    setBtnEnable(validSure,true);
                }

                break;
            case R.id.sure:
                setBtnEnable(sure,false);
                if(validEditText(editText)) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            personName = editText.getText().toString();
                            pf.put("personName", personName);
                            pf.commit();
                            JSONObject o = FacePPManager.getInstance().createPreson(personName);
                            if (o != null) {
                                showMsg("创建用户成功");
                                Intent intent = new Intent();
                                intent.setClass(FaceSetManagerActivity.this, PersonalFaceManageActivity.class);
                                startActivity(intent);
                            }
                            setBtnEnable(sure,true);
                        }
                    }).start();
                }else {
                    setBtnEnable(sure,true);
                }
                break;
            case R.id.scan_and_add_to_face_set:
                Intent intent=new Intent();
                intent.putExtra(Config.PF_KEY_SCANNING_TYPE, OpenCVScanningActivity.ScanningType.addFaceToFaceSet.getValue());
                intent.setClass(this, OpenCVScanningActivity.class);
                startActivity(intent);
                break;

            case R.id.show_faces:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getFaceSetInfo();
                    }
                }).start();

                break;
            case R.id.search_in_set:
                Intent intentSearchInSet=new Intent();
                intentSearchInSet.putExtra(Config.PF_KEY_SCANNING_TYPE, OpenCVScanningActivity.ScanningType.recognitionInSet.getValue());
                intentSearchInSet.setClass(this, OpenCVScanningActivity.class);
                startActivity(intentSearchInSet);
                break;
        }
    }

    private boolean validEditText(EditText editText){
        String personName=editText.getText().toString();
        if(personName.isEmpty()) {
            showMsg("请输入名字");
            return false;
        }
        return true;
    }



    private void getFaceSetInfo(){
        try {
          JSONArray faces= FacePPManager.getInstance().getFaceSetInfo(faceSetName).getJSONArray("face");
            if(faces==null||faces.length()==0){
                showMsg(faceSetName+" 暂无数据");
            }else {
                getFaceAndShow(faces);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getFaceAndShow(JSONArray faces){
        try {
            ArrayList<String> faceIds=new ArrayList<>();
            for(int i=0;i<faces.length();i++){
                faceIds.add(faces.getJSONObject(i).getString("face_id"));
            }
            JSONObject result= FacePPManager.getInstance().getFaceInfo(faceIds);
            JSONArray faceInfos=result.getJSONArray("face_info");
            ArrayList<String> faceImgUrls=new ArrayList();
            for (int j=0;j<faceInfos.length();j++){
                faceImgUrls.add(faceInfos.getJSONObject(j).getString("url"));
            }
            showMsg("有"+faceImgUrls.size()+"个face图");
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
