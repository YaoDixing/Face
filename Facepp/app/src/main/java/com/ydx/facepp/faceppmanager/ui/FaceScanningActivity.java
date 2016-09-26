package com.ydx.facepp.faceppmanager.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.faceplusplus.api.FaceDetecter;
import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.Config;
import com.ydx.facepp.R;
import com.ydx.facepp.permission.DangerousPermissionEnum;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/9/13.
 */
public class FaceScanningActivity extends BaseActivity {
    private CameraManager cameraManager;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    boolean isPreview = false;        //是否在浏览中
    FaceDetecter faceDetecter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_scan);
        findViewById(R.id.openCamera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(permissionManager.isOverAndroidM()){
                    permissionManager.checkPermission(DangerousPermissionEnum.READ_PHONE_STATE,DangerousPermissionEnum.READ_PHONE_STATE.getReqCode());
                }else {
                    initCamera();
                }

            }
        });
        init();
    }

    private void init(){
        faceDetecter=new FaceDetecter();
        faceDetecter.init(this, Config.API_KEY);
        cameraManager= ((CameraManager) getSystemService(CAMERA_SERVICE));
        surfaceView= ((SurfaceView) findViewById(R.id.surface_view));

        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if(camera!=null){
                    if(isPreview){
                        camera.stopPreview();
                        camera.release();
                        camera=null;
                    }
                }
                System.exit(0);
            }
        });
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }


    private void initCamera(){
//        int width=dip2px(this,400);
//        int height=dip2px(this,300);
        int width=480;
        int height=800;
        if (!isPreview) {
            camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
        }
        if (camera != null && !isPreview) {
            try {
                Camera.Parameters parameters = camera.getParameters();
                parameters.setPreviewSize(width, height);    // 设置预览照片的大小
                parameters.setPreviewFpsRange(20,30);                    // 每秒显示20~30帧
                parameters.setPictureFormat(ImageFormat.NV21);           // 设置图片格式
                parameters.setPictureSize(width, height);    // 设置照片的大小
                //camera.setParameters(parameters);                      // android2.3.3以后不需要此行代码
                camera.setPreviewDisplay(surfaceHolder);                 // 通过SurfaceView显示取景画面
                camera.setPreviewCallback(new StreamIt());         // 设置回调的类
                camera.startPreview();// 开始预览
                camera.setDisplayOrientation(90);
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
//                        if(success){
//                            camera.takePicture();
//                        }
                    }
                });
            } catch (Exception e) {

            }
            isPreview=true;
        }


    }

    private int width = 480;
    private int height = 800;
    List<FaceDetecter.Face[]> faces=new ArrayList<>();
    List<ByteArrayOutputStream> imgOuts=new ArrayList<>();
    int faceInfoNum=0;
    class StreamIt implements Camera.PreviewCallback{
//        private String ipname;
//        public StreamIt(String ipname){
//            this.ipname = ipname;
//        }
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Size size = camera.getParameters().getPreviewSize();
            YuvImage image = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
            if(image!=null) {
                ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                image.compressToJpeg(new Rect(0, 0, size.width, size.height), 80, outstream);
                try {
                    outstream.flush();

                    if(imgOuts.size()<=10){
                        imgOuts.add(outstream);
                        if(imgOuts.size()==10){
                            findFice();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void findFice(){
      String path= Environment.getExternalStorageDirectory().getPath();
        path=path+"/"+getPackageName();
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        int i=0;
        for (ByteArrayOutputStream out:imgOuts) {
            i++;
            File f=new File(path+"/a"+i+".jpg");
            File ff=new File(path+"/a"+i+""+i+".jpg");
            if(!ff.exists()){
                try {
                    ff.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!f.exists()){
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            try {
//                FileOutputStream fileOutputStream=new FileOutputStream(f);
//                fileOutputStream.write(out.toByteArray());
//                fileOutputStream.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Bitmap bitmap= BitmapFactory.decodeFile(f.getPath());
//            ImageUtil.saveBitmapToFile(ImageUtil.rotaingBitmap(-90,bitmap),ff, Bitmap.CompressFormat.JPEG,50);
            Bitmap b=BitmapFactory.decodeFile(ff.getPath());
            final FaceDetecter.Face[] faceinfo = faceDetecter.findFaces(b);
            if (faceinfo != null && faceinfo.length > 0) {
                faceInfoNum++;
                faces.add(faceinfo);
                showMsg("has face");
            } else {
                showMsg("no face");
            }
            if (faceInfoNum == 10) {
                camera.stopPreview();
            }
//            bitmap.recycle();
            b.recycle();
        }
        showMsg(path);
    }



    public static int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5f);
    }
    public static float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    @Override
    public void onGranted(int requestCode) {
        if(requestCode==DangerousPermissionEnum.READ_PHONE_STATE.getReqCode()){
            initCamera();
        }
    }

    @Override
    public void onRefused(int requestCode) {

    }
}
