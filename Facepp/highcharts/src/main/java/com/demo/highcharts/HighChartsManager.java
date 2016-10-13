package com.demo.highcharts;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class HighChartsManager {
    private static HighChartsManager ourInstance = new HighChartsManager();

    public static HighChartsManager getInstance() {
        return ourInstance;
    }

    private HighChartsManager() {
    }


}
