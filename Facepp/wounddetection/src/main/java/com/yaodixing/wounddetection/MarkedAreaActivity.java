package com.yaodixing.wounddetection;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by yaodixing on 2016/11/22.
 */
public class MarkedAreaActivity extends Activity {
    ImageView iv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marked_area);
        iv = ((ImageView) findViewById(R.id.iv));
    }
}
