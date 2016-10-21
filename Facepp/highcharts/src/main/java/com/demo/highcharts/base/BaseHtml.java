package com.demo.highcharts.base;

/**
 * Created by yaodixing on 2016/10/17.
 */
public class BaseHtml {

    public static String getHtml(String showJS){
        return "<!doctype html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <script src=\"http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js\"></script>\n" +
                "  <script src=\"http://cdn.hcharts.cn/highcharts/highcharts.js\">  </script>\n" +
                "  <script src=\"http://cdn.hcharts.cn/highcharts/modules/exporting.js\">  </script>\n" +
                "  <script src=\" http://cdn.hcharts.cn/highcharts/highcharts-more.js \"></script>\n" +
                "  <script src=\" http://img.hcharts.cn/highcharts/themes/dark-unica.js\"> </script>\n" +
                "  <script id=\"show\">"+showJS+"</script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div id=\"container\" style=\"min-width:700px;height:400px\"></div>\n" +
                "</body>\n" +
                "</html>";
    }

    public static String getDarkThemeJS(){
        return "";
    }
}
