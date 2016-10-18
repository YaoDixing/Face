package com.demo.highcharts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.demo.highcharts.base.BaseHtml;
import com.demo.highcharts.charts.BaseAreaChartJS;
import com.demo.highcharts.model.BaseSeries;
import com.demo.highcharts.util.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initWebViewSettings();
        String htmlFilePath=initHtmlFile();
        List<BaseSeries> baseSeries=new ArrayList<>();
        BaseSeries baseSeries1=new BaseSeries();
        baseSeries1.setName("girl");
        baseSeries1.setData(new Object[]{"222","333","111","254"});
        BaseSeries baseSeries2=new BaseSeries();
        baseSeries2.setName("boy");
        baseSeries2.setData(new Object[]{"463","212","154","111"});
        baseSeries.add(baseSeries1);
        baseSeries.add(baseSeries2);
        BaseAreaChartJS baseAreaChartJS=new BaseAreaChartJS("HAHHAHA","CNMLGB",baseSeries);
        String js= baseAreaChartJS.getJs();
        String html= BaseHtml.getHtml(js);
        webView.loadData(html,"text/html","utf-8");
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
