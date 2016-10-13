package com.demo.highcharts.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class Chart implements Serializable {

    /**
     * alignTicks : true
     * animation : true
     * backgroundColor : #FFFFFF
     * borderColor : #4572A7
     * borderRadius : 0
     * borderWidth : 0
     * className : null
     * defaultSeriesType : line
     * events : {"addSeries":null,"click":null,"drilldown":"fuction","drillup":"fuction","load":null,"redraw":null,"selection":null,"afterPrint":"fuction","beforePrint":"fuction"}
     * height : null
     * ignoreHiddenSeries : true
     * inverted : false
     * margin : null
     * marginBottom : null
     * marginLeft : null
     * marginRight : null
     * marginTop : null
     * options3d : {"alpha":0,"beta":0,"depth":100,"enabled":false,"frame":{"back":{"color":"transparent","size":1},"bottom":{"color":"transparent","size":1},"side":{"color":"transparent","size":1}},"viewDistance":100}
     * panKey :
     * panning : false
     * pinchType : null
     * plotBackgroundColor : null
     * plotBackgroundImage : null
     * plotBorderColor : #C0C0C0
     * plotBorderWidth : 0
     * plotShadow : false
     * polar : false
     * reflow : true
     * renderTo : null
     * resetZoomButton : {"position":null,"relativeTo":"plot","theme":null}
     * selectionMarkerFill : rgba(69,114,167,0.25)
     * shadow : false
     * showAxes : false
     * spacing : [10,10,15,10]
     * spacingBottom : 15
     * spacingLeft : 10
     * spacingRight : 10
     * spacingTop : 10
     * style : null
     * type : line
     * width : null
     * zoomType : null
     */

    private boolean alignTicks;
    private boolean animation;
    private String backgroundColor;
    private String borderColor;
    private int borderRadius;
    private int borderWidth;
    private String className;
    private String defaultSeriesType;
    /**
     * addSeries : null
     * click : null
     * drilldown : fuction
     * drillup : fuction
     * load : null
     * redraw : null
     * selection : null
     * afterPrint : fuction
     * beforePrint : fuction
     */

    private EventsBean events;
    private Object height;
    private boolean ignoreHiddenSeries;
    private boolean inverted;
    private Object margin;
    private Object marginBottom;
    private Object marginLeft;
    private Object marginRight;
    private Object marginTop;
    /**
     * alpha : 0
     * beta : 0
     * depth : 100
     * enabled : false
     * frame : {"back":{"color":"transparent","size":1},"bottom":{"color":"transparent","size":1},"side":{"color":"transparent","size":1}}
     * viewDistance : 100
     */

    private Options3dBean options3d;
    private String panKey;
    private boolean panning;
    private String pinchType;
    private String plotBackgroundColor;
    private String plotBackgroundImage;
    private String plotBorderColor;
    private int plotBorderWidth;
    private boolean plotShadow;
    private boolean polar;
    private boolean reflow;
    private Object renderTo;
    /**
     * position : null
     * relativeTo : plot
     * theme : null
     */

    private ResetZoomButtonBean resetZoomButton;
    private String selectionMarkerFill;
    private boolean shadow;
    private boolean showAxes;
    private int spacingBottom;
    private int spacingLeft;
    private int spacingRight;
    private int spacingTop;
    private Object style;
    private String type;
    private Object width;
    private String zoomType;
    private List<Integer> spacing;

    public boolean isAlignTicks() {
        return alignTicks;
    }

    public void setAlignTicks(boolean alignTicks) {
        this.alignTicks = alignTicks;
    }

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDefaultSeriesType() {
        return defaultSeriesType;
    }

    public void setDefaultSeriesType(String defaultSeriesType) {
        this.defaultSeriesType = defaultSeriesType;
    }

    public EventsBean getEvents() {
        return events;
    }

    public void setEvents(EventsBean events) {
        this.events = events;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public boolean isIgnoreHiddenSeries() {
        return ignoreHiddenSeries;
    }

    public void setIgnoreHiddenSeries(boolean ignoreHiddenSeries) {
        this.ignoreHiddenSeries = ignoreHiddenSeries;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }

    public Object getMargin() {
        return margin;
    }

    public void setMargin(Object margin) {
        this.margin = margin;
    }

    public Object getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(Object marginBottom) {
        this.marginBottom = marginBottom;
    }

    public Object getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(Object marginLeft) {
        this.marginLeft = marginLeft;
    }

    public Object getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(Object marginRight) {
        this.marginRight = marginRight;
    }

    public Object getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(Object marginTop) {
        this.marginTop = marginTop;
    }

    public Options3dBean getOptions3d() {
        return options3d;
    }

    public void setOptions3d(Options3dBean options3d) {
        this.options3d = options3d;
    }

    public String getPanKey() {
        return panKey;
    }

    public void setPanKey(String panKey) {
        this.panKey = panKey;
    }

    public boolean isPanning() {
        return panning;
    }

    public void setPanning(boolean panning) {
        this.panning = panning;
    }

    public String getPinchType() {
        return pinchType;
    }

    public void setPinchType(String pinchType) {
        this.pinchType = pinchType;
    }

    public String getPlotBackgroundColor() {
        return plotBackgroundColor;
    }

    public void setPlotBackgroundColor(String plotBackgroundColor) {
        this.plotBackgroundColor = plotBackgroundColor;
    }

    public String getPlotBackgroundImage() {
        return plotBackgroundImage;
    }

    public void setPlotBackgroundImage(String plotBackgroundImage) {
        this.plotBackgroundImage = plotBackgroundImage;
    }

    public String getPlotBorderColor() {
        return plotBorderColor;
    }

    public void setPlotBorderColor(String plotBorderColor) {
        this.plotBorderColor = plotBorderColor;
    }

    public int getPlotBorderWidth() {
        return plotBorderWidth;
    }

    public void setPlotBorderWidth(int plotBorderWidth) {
        this.plotBorderWidth = plotBorderWidth;
    }

    public boolean isPlotShadow() {
        return plotShadow;
    }

    public void setPlotShadow(boolean plotShadow) {
        this.plotShadow = plotShadow;
    }

    public boolean isPolar() {
        return polar;
    }

    public void setPolar(boolean polar) {
        this.polar = polar;
    }

    public boolean isReflow() {
        return reflow;
    }

    public void setReflow(boolean reflow) {
        this.reflow = reflow;
    }

    public Object getRenderTo() {
        return renderTo;
    }

    public void setRenderTo(Object renderTo) {
        this.renderTo = renderTo;
    }

    public ResetZoomButtonBean getResetZoomButton() {
        return resetZoomButton;
    }

    public void setResetZoomButton(ResetZoomButtonBean resetZoomButton) {
        this.resetZoomButton = resetZoomButton;
    }

    public String getSelectionMarkerFill() {
        return selectionMarkerFill;
    }

    public void setSelectionMarkerFill(String selectionMarkerFill) {
        this.selectionMarkerFill = selectionMarkerFill;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public boolean isShowAxes() {
        return showAxes;
    }

    public void setShowAxes(boolean showAxes) {
        this.showAxes = showAxes;
    }

    public int getSpacingBottom() {
        return spacingBottom;
    }

    public void setSpacingBottom(int spacingBottom) {
        this.spacingBottom = spacingBottom;
    }

    public int getSpacingLeft() {
        return spacingLeft;
    }

    public void setSpacingLeft(int spacingLeft) {
        this.spacingLeft = spacingLeft;
    }

    public int getSpacingRight() {
        return spacingRight;
    }

    public void setSpacingRight(int spacingRight) {
        this.spacingRight = spacingRight;
    }

    public int getSpacingTop() {
        return spacingTop;
    }

    public void setSpacingTop(int spacingTop) {
        this.spacingTop = spacingTop;
    }

    public Object getStyle() {
        return style;
    }

    public void setStyle(Object style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public String getZoomType() {
        return zoomType;
    }

    public void setZoomType(String zoomType) {
        this.zoomType = zoomType;
    }

    public List<Integer> getSpacing() {
        return spacing;
    }

    public void setSpacing(List<Integer> spacing) {
        this.spacing = spacing;
    }

    public static class EventsBean {
        private Object addSeries;
        private Object click;
        private String drilldown;
        private String drillup;
        private Object load;
        private Object redraw;
        private Object selection;
        private String afterPrint;
        private String beforePrint;

        public Object getAddSeries() {
            return addSeries;
        }

        public void setAddSeries(Object addSeries) {
            this.addSeries = addSeries;
        }

        public Object getClick() {
            return click;
        }

        public void setClick(Object click) {
            this.click = click;
        }

        public String getDrilldown() {
            return drilldown;
        }

        public void setDrilldown(String drilldown) {
            this.drilldown = drilldown;
        }

        public String getDrillup() {
            return drillup;
        }

        public void setDrillup(String drillup) {
            this.drillup = drillup;
        }

        public Object getLoad() {
            return load;
        }

        public void setLoad(Object load) {
            this.load = load;
        }

        public Object getRedraw() {
            return redraw;
        }

        public void setRedraw(Object redraw) {
            this.redraw = redraw;
        }

        public Object getSelection() {
            return selection;
        }

        public void setSelection(Object selection) {
            this.selection = selection;
        }

        public String getAfterPrint() {
            return afterPrint;
        }

        public void setAfterPrint(String afterPrint) {
            this.afterPrint = afterPrint;
        }

        public String getBeforePrint() {
            return beforePrint;
        }

        public void setBeforePrint(String beforePrint) {
            this.beforePrint = beforePrint;
        }
    }

    public static class Options3dBean {
        private int alpha;
        private int beta;
        private int depth;
        private boolean enabled;
        /**
         * back : {"color":"transparent","size":1}
         * bottom : {"color":"transparent","size":1}
         * side : {"color":"transparent","size":1}
         */

        private FrameBean frame;
        private int viewDistance;

        public int getAlpha() {
            return alpha;
        }

        public void setAlpha(int alpha) {
            this.alpha = alpha;
        }

        public int getBeta() {
            return beta;
        }

        public void setBeta(int beta) {
            this.beta = beta;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public FrameBean getFrame() {
            return frame;
        }

        public void setFrame(FrameBean frame) {
            this.frame = frame;
        }

        public int getViewDistance() {
            return viewDistance;
        }

        public void setViewDistance(int viewDistance) {
            this.viewDistance = viewDistance;
        }

        public static class FrameBean {
            /**
             * color : transparent
             * size : 1
             */

            private BackBean back;
            /**
             * color : transparent
             * size : 1
             */

            private BottomBean bottom;
            /**
             * color : transparent
             * size : 1
             */

            private SideBean side;

            public BackBean getBack() {
                return back;
            }

            public void setBack(BackBean back) {
                this.back = back;
            }

            public BottomBean getBottom() {
                return bottom;
            }

            public void setBottom(BottomBean bottom) {
                this.bottom = bottom;
            }

            public SideBean getSide() {
                return side;
            }

            public void setSide(SideBean side) {
                this.side = side;
            }

            public static class BackBean {
                private String color;
                private int size;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }
            }

            public static class BottomBean {
                private String color;
                private int size;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }
            }

            public static class SideBean {
                private String color;
                private int size;

                public String getColor() {
                    return color;
                }

                public void setColor(String color) {
                    this.color = color;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }
            }
        }
    }

    public static class ResetZoomButtonBean {
        private Object position;
        private String relativeTo;
        private Object theme;

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }

        public String getRelativeTo() {
            return relativeTo;
        }

        public void setRelativeTo(String relativeTo) {
            this.relativeTo = relativeTo;
        }

        public Object getTheme() {
            return theme;
        }

        public void setTheme(Object theme) {
            this.theme = theme;
        }
    }
}
