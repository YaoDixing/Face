package com.demo.highcharts.model;

import java.io.Serializable;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class Subtitle implements Serializable {
    private String text;//图表的副标题文字，默认是空，即默认是没有副标题的。
    private String align;//图表标题水平对齐方式。有“"left", "center" and "right"可选，分别对应“左对齐”、“居中对齐”、“右对齐”。 默认值：center.
    private boolean floating;//标题是否浮动。当设置浮动（即该属性值为true）时，标题将不占空间。
    private int margin ;//当有副标题，表示标题和副标题之间的间隔。 默认值：15.
    private String style;
    private boolean useHTML;
    private String verticalAlign;//标题垂直对齐方式。有“top”，”middle“和“bottom” 可选，分别对应“顶对齐”、“居中对齐”、“底对齐”。并且当给定一个值后，该标题将表现为浮动。
    private int x;//相对于水平对齐的偏移量，取值范围为：图表左边距到图表右边距，可以是负值，单位px。 默认值：0.
    private int y;//相对于垂直对齐的偏移量，取值范围：图表的上边距到图表的下边距，可以是负值，单位是px。副标题默认是在标题下面的，除非标题是floating属性。 默认值： null.

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public boolean isFloating() {
        return floating;
    }

    public void setFloating(boolean floating) {
        this.floating = floating;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public boolean isUseHTML() {
        return useHTML;
    }

    public void setUseHTML(boolean useHTML) {
        this.useHTML = useHTML;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(String verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "subtitle{" +
                "text='" + text + '\'' +
                ", align='" + align + '\'' +
                ", floating=" + floating +
                ", margin=" + margin +
                ", style='" + style + '\'' +
                ", useHTML=" + useHTML +
                ", verticalAlign='" + verticalAlign + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
