package com.demo.highcharts.charts;

import com.demo.highcharts.model.BaseSeries;

import java.util.List;

/**
 * Created by yaodixing on 2016/10/17.
 */
public class BaseAreaChartJS extends BaseJS{
    private static int startX=50;
    private static int pointSize=3;
    private String title,subtitle;
    private List<BaseSeries> seriesList;
    public BaseAreaChartJS(String title,String subtitle,List<BaseSeries> seriesList){
        this.title=title;
        this.subtitle=subtitle;
        this.seriesList=seriesList;
        init();
    }

    private void init(){
        setChart("chart:{type:'area'}");
        setTitle("title:{text:'"+title+"'}");
        setSubtitle("subtitle:{text:'"+subtitle+"'}");
        setxAxis("xAxis: {\n" +
                "            allowDecimals: false,\n" +
                "            labels: {\n" +
                "                formatter: function () {\n" +
                "                    return this.value; \n" +
                "                }\n" +
                "            }\n" +
                "        }");
        setyAxis("yAxis: {\n" +
                "            title: {\n" +
                "                text: 'Nuclear weapon states'\n" +
                "            },\n" +
                "            labels: {\n" +
                "                formatter: function () {\n" +
                "                    return this.value;\n" +
                "                }\n" +
                "            }\n" +
                "        }");
        setToolTip("tooltip: {\n" +
                "            pointFormat: '{series.name} produced <b>{point.y:,.0f}</b><br/>warheads in {point.x}'\n" +
                "        }");
        setPlotOptions("plotOptions: {\n" +
                "            area: {\n" +
                "                pointStart: "+startX+",\n" +
                "                marker: {\n" +
                "                    enabled: false,\n" +
                "                    symbol: 'circle',\n" +
                "                    radius: "+pointSize+",\n" +
                "                    states: {\n" +
                "                        hover: {\n" +
                "                            enabled: true\n" +
                "                        }\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        }");
        setSeries(initSeries(seriesList));
    }

    private String initSeries(List<BaseSeries> baseSeriesList){
        StringBuffer sb=new StringBuffer();
        if(baseSeriesList!=null&&!baseSeriesList.isEmpty()) {
            sb.append("series: [\n");
            int i=0;
            for (BaseSeries baseSeries : baseSeriesList) {
                i++;
                sb.append("{name:'" + baseSeries.getName() + "',\n");
                sb.append("data:" + baseSeries.getDataToString() + "}");
                if(i!=baseSeriesList.size())
                    sb.append(",\n");
            }
            sb.append("]");
            return sb.toString();
        }
        return "";
    }


}
