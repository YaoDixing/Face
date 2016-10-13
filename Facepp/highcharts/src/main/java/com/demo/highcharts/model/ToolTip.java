package com.demo.highcharts.model;

import java.io.Serializable;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class ToolTip implements Serializable {

    /**
     * animation : true
     * backgroundColor : rgba(255, 255, 255, 0.85)
     * borderColor : null
     * borderRadius : 3
     * borderWidth : 1
     * crosshairs : null
     * dateTimeLabelFormats : null
     * enabled : true
     * followPointer : false
     * followTouchMove : false
     * footerFormat : false
     * formatter : null
     * headerFormat :
     * hideDelay : 500
     * pointFormat :
     * positioner : null
     * shadow : true
     * shape : callout
     * shared : false
     * snap : null
     * style : null
     * useHTML : false
     * valueDecimals : null
     * valuePrefix : null
     * valueSuffix : null
     * xDateFormat : null
     * pointFormatter : null
     */

    private boolean animation;
    private String backgroundColor;
    private String borderColor;
    private int borderRadius;
    private int borderWidth;
    private Object crosshairs;
    private Object dateTimeLabelFormats;
    private boolean enabled;
    private boolean followPointer;
    private boolean followTouchMove;
    private String footerFormat;
    private Object formatter;
    private String headerFormat;
    private int hideDelay;
    private String pointFormat;
    private Object positioner;
    private boolean shadow;
    private String shape;
    private boolean shared;
    private Object snap;
    private Object style;
    private boolean useHTML;
    private Object valueDecimals;
    private String valuePrefix;
    private String valueSuffix;
    private String xDateFormat;
    private Object pointFormatter;

    public boolean isAnimation() {
        return animation;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
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

    public Object getCrosshairs() {
        return crosshairs;
    }

    public void setCrosshairs(Object crosshairs) {
        this.crosshairs = crosshairs;
    }

    public Object getDateTimeLabelFormats() {
        return dateTimeLabelFormats;
    }

    public void setDateTimeLabelFormats(Object dateTimeLabelFormats) {
        this.dateTimeLabelFormats = dateTimeLabelFormats;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isFollowPointer() {
        return followPointer;
    }

    public void setFollowPointer(boolean followPointer) {
        this.followPointer = followPointer;
    }

    public boolean isFollowTouchMove() {
        return followTouchMove;
    }

    public void setFollowTouchMove(boolean followTouchMove) {
        this.followTouchMove = followTouchMove;
    }

    public String getFooterFormat() {
        return footerFormat;
    }

    public void setFooterFormat(String footerFormat) {
        this.footerFormat = footerFormat;
    }

    public Object getFormatter() {
        return formatter;
    }

    public void setFormatter(Object formatter) {
        this.formatter = formatter;
    }

    public String getHeaderFormat() {
        return headerFormat;
    }

    public void setHeaderFormat(String headerFormat) {
        this.headerFormat = headerFormat;
    }

    public int getHideDelay() {
        return hideDelay;
    }

    public void setHideDelay(int hideDelay) {
        this.hideDelay = hideDelay;
    }

    public String getPointFormat() {
        return pointFormat;
    }

    public void setPointFormat(String pointFormat) {
        this.pointFormat = pointFormat;
    }

    public Object getPositioner() {
        return positioner;
    }

    public void setPositioner(Object positioner) {
        this.positioner = positioner;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public Object getSnap() {
        return snap;
    }

    public void setSnap(Object snap) {
        this.snap = snap;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public boolean isUseHTML() {
        return useHTML;
    }

    public void setUseHTML(boolean useHTML) {
        this.useHTML = useHTML;
    }

    public Object getValueDecimals() {
        return valueDecimals;
    }

    public void setValueDecimals(Object valueDecimals) {
        this.valueDecimals = valueDecimals;
    }

    public String getValuePrefix() {
        return valuePrefix;
    }

    public void setValuePrefix(String valuePrefix) {
        this.valuePrefix = valuePrefix;
    }

    public String getValueSuffix() {
        return valueSuffix;
    }

    public void setValueSuffix(String valueSuffix) {
        this.valueSuffix = valueSuffix;
    }

    public String getXDateFormat() {
        return xDateFormat;
    }

    public void setXDateFormat(String xDateFormat) {
        this.xDateFormat = xDateFormat;
    }

    public Object getPointFormatter() {
        return pointFormatter;
    }

    public void setPointFormatter(Object pointFormatter) {
        this.pointFormatter = pointFormatter;
    }
}
