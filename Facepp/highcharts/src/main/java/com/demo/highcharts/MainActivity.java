package com.demo.highcharts;

import android.os.Process;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.demo.highcharts.util.Utils;
import com.yaodixing.permissionlibrary.permission.DangerousPermissionEnum;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(permissionManager.isOverAndroidM()){
            List<DangerousPermissionEnum> list = new ArrayList<>();
            list.add(DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE);
            permissionManager.checkPermission(list,DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE.getReqCode());
        }else {
            init();
        }


    }
    private void init(){
        findView();
        initWebViewSettings();
        initJsFile("echarts.common.min.js");
        initJsFile("jquery-1.8.3.min.js");
        String htmlFilePath=initHtmlFile();
        showHtml(htmlFilePath);
    }

    @Override
    public void onGranted(int requestCode) {
        if(requestCode==DangerousPermissionEnum.WRITE_EXTERNAL_STORAGE.getReqCode())
            init();
    }

    @Override
    public void onRefused(int requestCode) {
        android.os.Process.killProcess(Process.myPid());
    }

    private void findView(){
        webView= ((WebView) findViewById(R.id.webView));
    }

    private void initWebViewSettings(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
    }

    private void showHtml(String path){
        webView.loadUrl("file://"+path);
    }

    private String initHtmlFile(){
        return Utils.copyAssetsFile(this,"ShowCharts.html");
    }

    private String initJsFile(String jsName){
        return Utils.copyAssetsFile(this,jsName);
    }


    private void addJsToHtml(String htmlPath,String js){
        String html=readHtmlToString(htmlPath);
        Document document=Jsoup.parse(html);
        Element element=document.getElementById("show");
        element.append(js);
        Log.i("document",document.toString());
        webView.loadData(document.toString(),"text/html","utf-8");
    }

    private String readJsToString(String jsPath){
       return Utils.readFileToString(jsPath);
    }

    private String readHtmlToString(String htmlpath){
        return Utils.readFileToString(htmlpath);
    }
}
