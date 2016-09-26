package com.ydx.facepp.signature.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;

import com.ydx.facepp.BaseActivity;
import com.ydx.facepp.R;

/**
 * Created by lenovo on 2016/9/23.
 */
public class SignatureActivity extends BaseActivity implements WritingBoard.OnOneWordCut{
    WritingBoard writingBoard;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);
        writingBoard= ((WritingBoard) findViewById(R.id.write_board));
        writingBoard.setPaintColor(Color.BLACK);
        writingBoard.setStrokeWidth(10);
        writingBoard.setOnOneWordCut(this);
        findView();
        initSeekBar();
        initSwitch();
        initCanvas();
        initReSet();
        initComposite();
    }

    private SeekBar seekBar;
    private Switch oneWordSwitch;
    private Button cutCanvas;
    private LinearLayout  console;
    private Button reSet;
    private Button compositeWord;
    private void findView(){
        seekBar= ((SeekBar) findViewById(R.id.seekBar));
        oneWordSwitch= ((Switch) findViewById(R.id.switch_one_word));
        cutCanvas = ((Button) findViewById(R.id.cutCanvas));
        console = ((LinearLayout) findViewById(R.id.console_lay));
        reSet = ((Button) findViewById(R.id.reset));
        compositeWord = ((Button) findViewById(R.id.compositeWord));
    }

    private void initSeekBar(){
        seekBar.setMax(15);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                writingBoard.setStrokeWidth(progress+10);
                writingBoard.postInvalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private LinearLayout oneWordLay,normalLay;
    private void initSwitch(){
        oneWordSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              writingBoard.setIsOnWordCutMode(isChecked);
                console.removeAllViews();
                if(isChecked){
                    cutCanvas.setVisibility(View.GONE);
                }else {
                    cutCanvas.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initCanvas(){
        cutCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Bitmap bitmap= writingBoard.cut();
                if(bitmap!=null){
                    console.removeAllViews();
                    ImageView imageView=new ImageView(SignatureActivity.this);
                    imageView.setImageBitmap(bitmap);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
                    normalLay=new LinearLayout(SignatureActivity.this);
                    normalLay.addView(imageView);
                    console.addView(normalLay);

                }
            }
        });
    }

    private void initReSet(){
        reSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writingBoard.reSet();
                console.removeAllViews();
                oneWordLay=null;
                normalLay=null;
            }
        });
    }

    private void initComposite(){
        compositeWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writingBoard.compositeWord();
            }
        });
    }



    @Override
    public void onGranted(int requestCode) {

    }

    @Override
    public void onRefused(int requestCode) {

    }

    @Override
    public void onOnWordCut(final Bitmap bitmap) {
        writingBoard.post(new Runnable() {
            @Override
            public void run() {
                ImageView imageView=new ImageView(SignatureActivity.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
                imageView.setImageBitmap(bitmap);
                if(oneWordLay==null){
                    oneWordLay=new LinearLayout(SignatureActivity.this);

                }
                oneWordLay.addView(imageView);
                try {
                    console.addView(oneWordLay);
                }catch ( Exception E){

                }
            }
        });

    }

    @Override
    public void onCompositeWord(final Bitmap bitmap) {
        writingBoard.post(new Runnable() {
            @Override
            public void run() {
                console.removeAllViews();
                ImageView imageView=new ImageView(SignatureActivity.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(400,200));
                imageView.setImageBitmap(bitmap);
                oneWordLay=new LinearLayout(SignatureActivity.this);
                oneWordLay.addView(imageView);
                console.addView(oneWordLay);
            }
        });
    }
}
