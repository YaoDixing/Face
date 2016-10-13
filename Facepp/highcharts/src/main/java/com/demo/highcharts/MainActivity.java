package com.demo.highcharts;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.demo.highcharts.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initWebViewSettings();
        String htmlFilePath=initHtmlFile();
        String jsFilePath=initJsFile();
        String js=readJsToString(jsFilePath);
        addJsToHtml(htmlFilePath,js);
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
        return Utils.copyAssetsFile(this,"highcharts.html");
    }

    private String initJsFile(){
        return Utils.copyAssetsFile(this,"3d_pie.js");
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
