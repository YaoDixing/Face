package com.yaodixing.wounddetection;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

/**
 * Created by yaodixing on 2016/11/24.
 */
public class CropActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        CropViewLay cropViewLay = ((CropViewLay) findViewById(R.id.cropviewlay));
        int imgRsId = getIntent().getIntExtra("imgRsId",0);
        cropViewLay.init(imgRsId);
        cropViewLay.setOnCropListener(new CropView.OnCropWound() {
            @Override
            public void onCropWound(Rect rect) {
                Intent intent = new Intent();
                intent.putExtra("rect",rect);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
