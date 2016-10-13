package com.demo.highcharts.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class Legend implements Serializable {

    /**
     * align : center
     * backgroundColor : null
     * borderColor : #909090
     * borderRadius : 0
     * borderWidth : 0
     * enabled : true
     * floating : false
     * itemDistance : 20
     * itemHiddenStyle : null
     * itemHoverStyle : null
     * itemMarginBottom : 0
     * itemMarginTop : 0
     * itemStyle : ["object Object"]
     * itemWidth : null
     * labelFormat : {name}
     * labelFormatter : null
     * layout : horizontal
     * lineHeight : 16
     * margin : 15
     * maxHeight : null
     * navigation : {"activeColor":"#3E576F","animation":true,"arrowSize":12,"inactiveColor":"#CCC","style":null}
     * padding : 8
     * reversed : false
     * rtl : false
     * shadow : false
     * style : null
     * symbolHeight : 12
     * symbolPadding : 5
     * symbolRadius : 2
     * symbolWidth : 16
     * title : {"style":null,"text":"null"}
     * useHTML : false
     * verticalAlign : bottom
     * width : null
     * x : 0
     * y : 0
     */

    private String align;
    private String backgroundColor;
    private String borderColor;
    private int borderRadius;
    private int borderWidth;
    private boolean enabled;
    private boolean floating;
    private int itemDistance;
    private Object itemHiddenStyle;
    private Object itemHoverStyle;
    private int itemMarginBottom;
    private int itemMarginTop;
    private Object itemWidth;
    private String labelFormat;
    private Object labelFormatter;
    private String layout;
    private int lineHeight;
    private int margin;
    private Object maxHeight;
    /**
     * activeColor : #3E576F
     * animation : true
     * arrowSize : 12
     * inactiveColor : #CCC
     * style : null
     */

    private NavigationBean navigation;
    private int padding;
    private boolean reversed;
    private boolean rtl;
    private boolean shadow;
    private Object style;
    private int symbolHeight;
    private int symbolPadding;
    private int symbolRadius;
    private int symbolWidth;
    /**
     * style : null
     * text : null
     */

    private TitleBean title;
    private boolean useHTML;
    private String verticalAlign;
    private Object width;
    private int x;
    private int y;
    private List<String> itemStyle;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFloating() {
        return floating;
    }

    public void setFloating(boolean floating) {
        this.floating = floating;
    }

    public int getItemDistance() {
        return itemDistance;
    }

    public void setItemDistance(int itemDistance) {
        this.itemDistance = itemDistance;
    }

    public Object getItemHiddenStyle() {
        return itemHiddenStyle;
    }

    public void setItemHiddenStyle(Object itemHiddenStyle) {
        this.itemHiddenStyle = itemHiddenStyle;
    }

    public Object getItemHoverStyle() {
        return itemHoverStyle;
    }

    public void setItemHoverStyle(Object itemHoverStyle) {
        this.itemHoverStyle = itemHoverStyle;
    }

    public int getItemMarginBottom() {
        return itemMarginBottom;
    }

    public void setItemMarginBottom(int itemMarginBottom) {
        this.itemMarginBottom = itemMarginBottom;
    }

    public int getItemMarginTop() {
        return itemMarginTop;
    }

    public void setItemMarginTop(int itemMarginTop) {
        this.itemMarginTop = itemMarginTop;
    }

    public Object getItemWidth() {
        return itemWidth;
    }

    public void setItemWidth(Object itemWidth) {
        this.itemWidth = itemWidth;
    }

    public String getLabelFormat() {
        return labelFormat;
    }

    public void setLabelFormat(String labelFormat) {
        this.labelFormat = labelFormat;
    }

    public Object getLabelFormatter() {
        return labelFormatter;
    }

    public void setLabelFormatter(Object labelFormatter) {
        this.labelFormatter = labelFormatter;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public int getMargin() {
        return margin;
    }

    public void setMargin(int margin) {
        this.margin = margin;
    }

    public Object getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Object maxHeight) {
        this.maxHeight = maxHeight;
    }

    public NavigationBean getNavigation() {
        return navigation;
    }

    public void setNavigation(NavigationBean navigation) {
        this.navigation = navigation;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public boolean isRtl() {
        return rtl;
    }

    public void setRtl(boolean rtl) {
        this.rtl = rtl;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public int getSymbolHeight() {
        return symbolHeight;
    }

    public void setSymbolHeight(int symbolHeight) {
        this.symbolHeight = symbolHeight;
    }

    public int getSymbolPadding() {
        return symbolPadding;
    }

    public void setSymbolPadding(int symbolPadding) {
        this.symbolPadding = symbolPadding;
    }

    public int getSymbolRadius() {
        return symbolRadius;
    }

    public void setSymbolRadius(int symbolRadius) {
        this.symbolRadius = symbolRadius;
    }

    public int getSymbolWidth() {
        return symbolWidth;
    }

    public void setSymbolWidth(int symbolWidth) {
        this.symbolWidth = symbolWidth;
    }

    public TitleBean getTitle() {
        return title;
    }

    public void setTitle(TitleBean title) {
        this.title = title;
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

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<String> getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(List<String> itemStyle) {
        this.itemStyle = itemStyle;
    }

    public static class NavigationBean {
        private String activeColor;
        private boolean animation;
        private int arrowSize;
        private String inactiveColor;
        private Object style;

        public String getActiveColor() {
            return activeColor;
        }

        public void setActiveColor(String activeColor) {
            this.activeColor = activeColor;
        }

        public boolean isAnimation() {
            return animation;
        }

        public void setAnimation(boolean animation) {
            this.animation = animation;
        }

        public int getArrowSize() {
            return arrowSize;
        }

        public void setArrowSize(int arrowSize) {
            this.arrowSize = arrowSize;
        }

        public String getInactiveColor() {
            return inactiveColor;
        }

        public void setInactiveColor(String inactiveColor) {
            this.inactiveColor = inactiveColor;
        }

        public Object getStyle() {
            return style;
        }

        public void setStyle(Object style) {
            this.style = style;
        }
    }

    public static class TitleBean {
        private Object style;
        private String text;

        public Object getStyle() {
            return style;
        }

        public void setStyle(Object style) {
            this.style = style;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
