package com.demo.highcharts.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class Series implements Serializable {

    /**
     * data : [{"color":"","dataLabels":null,"drilldown":"","events":{"click":null,"mouseOut":null,"mouseOver":null,"remove":null,"select":null,"unselect":null,"update":null},"id":"null","isIntermediateSum":false,"isSum":false,"legendIndex":null,"marker":{"enabled":true,"fillColor":"null","height":null,"lineColor":"#FFFFFF","lineWidth":0,"radius":4,"states":{"hover":{"enabled":true,"fillColor":"","lineColor":"#FFFFFF","lineWidth":0,"lineWidthPlus":1,"radius":null,"radiusPlus":2},"select":{"enabled":true,"fillColor":"null","lineColor":"#000000","lineWidth":0,"radius":null}},"symbol":"null","width":null},"name":"null","sliced":false,"x":null,"y":null,"colorValue":1,"parent":""}]
     * dataParser : null
     * dataURL : null
     * id :
     * index : 0
     * legendIndex : 0
     * name : null
     * stack : null
     * type : null
     * xAxis : 0
     * yAxis : 0
     * zIndex : 0
     */

    private Object dataParser;
    private String dataURL;
    private String id;
    private int index;
    private int legendIndex;
    private String name;
    private Object stack;
    private String type;
    private int xAxis;
    private int yAxis;
    private int zIndex;
    /**
     * color :
     * dataLabels : null
     * drilldown :
     * events : {"click":null,"mouseOut":null,"mouseOver":null,"remove":null,"select":null,"unselect":null,"update":null}
     * id : null
     * isIntermediateSum : false
     * isSum : false
     * legendIndex : null
     * marker : {"enabled":true,"fillColor":"null","height":null,"lineColor":"#FFFFFF","lineWidth":0,"radius":4,"states":{"hover":{"enabled":true,"fillColor":"","lineColor":"#FFFFFF","lineWidth":0,"lineWidthPlus":1,"radius":null,"radiusPlus":2},"select":{"enabled":true,"fillColor":"null","lineColor":"#000000","lineWidth":0,"radius":null}},"symbol":"null","width":null}
     * name : null
     * sliced : false
     * x : null
     * y : null
     * colorValue : 1
     * parent :
     */

    private List<DataBean> data;

    public Object getDataParser() {
        return dataParser;
    }

    public void setDataParser(Object dataParser) {
        this.dataParser = dataParser;
    }

    public String getDataURL() {
        return dataURL;
    }

    public void setDataURL(String dataURL) {
        this.dataURL = dataURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLegendIndex() {
        return legendIndex;
    }

    public void setLegendIndex(int legendIndex) {
        this.legendIndex = legendIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getStack() {
        return stack;
    }

    public void setStack(Object stack) {
        this.stack = stack;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getXAxis() {
        return xAxis;
    }

    public void setXAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setYAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getZIndex() {
        return zIndex;
    }

    public void setZIndex(int zIndex) {
        this.zIndex = zIndex;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String color;
        private Object dataLabels;
        private String drilldown;
        /**
         * click : null
         * mouseOut : null
         * mouseOver : null
         * remove : null
         * select : null
         * unselect : null
         * update : null
         */

        private EventsBean events;
        private String id;
        private boolean isIntermediateSum;
        private boolean isSum;
        private Object legendIndex;
        /**
         * enabled : true
         * fillColor : null
         * height : null
         * lineColor : #FFFFFF
         * lineWidth : 0
         * radius : 4
         * states : {"hover":{"enabled":true,"fillColor":"","lineColor":"#FFFFFF","lineWidth":0,"lineWidthPlus":1,"radius":null,"radiusPlus":2},"select":{"enabled":true,"fillColor":"null","lineColor":"#000000","lineWidth":0,"radius":null}}
         * symbol : null
         * width : null
         */

        private MarkerBean marker;
        private String name;
        private boolean sliced;
        private Object x;
        private Object y;
        private int colorValue;
        private String parent;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Object getDataLabels() {
            return dataLabels;
        }

        public void setDataLabels(Object dataLabels) {
            this.dataLabels = dataLabels;
        }

        public String getDrilldown() {
            return drilldown;
        }

        public void setDrilldown(String drilldown) {
            this.drilldown = drilldown;
        }

        public EventsBean getEvents() {
            return events;
        }

        public void setEvents(EventsBean events) {
            this.events = events;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsIntermediateSum() {
            return isIntermediateSum;
        }

        public void setIsIntermediateSum(boolean isIntermediateSum) {
            this.isIntermediateSum = isIntermediateSum;
        }

        public boolean isIsSum() {
            return isSum;
        }

        public void setIsSum(boolean isSum) {
            this.isSum = isSum;
        }

        public Object getLegendIndex() {
            return legendIndex;
        }

        public void setLegendIndex(Object legendIndex) {
            this.legendIndex = legendIndex;
        }

        public MarkerBean getMarker() {
            return marker;
        }

        public void setMarker(MarkerBean marker) {
            this.marker = marker;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSliced() {
            return sliced;
        }

        public void setSliced(boolean sliced) {
            this.sliced = sliced;
        }

        public Object getX() {
            return x;
        }

        public void setX(Object x) {
            this.x = x;
        }

        public Object getY() {
            return y;
        }

        public void setY(Object y) {
            this.y = y;
        }

        public int getColorValue() {
            return colorValue;
        }

        public void setColorValue(int colorValue) {
            this.colorValue = colorValue;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public static class EventsBean {
            private Object click;
            private Object mouseOut;
            private Object mouseOver;
            private Object remove;
            private Object select;
            private Object unselect;
            private Object update;

            public Object getClick() {
                return click;
            }

            public void setClick(Object click) {
                this.click = click;
            }

            public Object getMouseOut() {
                return mouseOut;
            }

            public void setMouseOut(Object mouseOut) {
                this.mouseOut = mouseOut;
            }

            public Object getMouseOver() {
                return mouseOver;
            }

            public void setMouseOver(Object mouseOver) {
                this.mouseOver = mouseOver;
            }

            public Object getRemove() {
                return remove;
            }

            public void setRemove(Object remove) {
                this.remove = remove;
            }

            public Object getSelect() {
                return select;
            }

            public void setSelect(Object select) {
                this.select = select;
            }

            public Object getUnselect() {
                return unselect;
            }

            public void setUnselect(Object unselect) {
                this.unselect = unselect;
            }

            public Object getUpdate() {
                return update;
            }

            public void setUpdate(Object update) {
                this.update = update;
            }
        }

        public static class MarkerBean {
            private boolean enabled;
            private String fillColor;
            private Object height;
            private String lineColor;
            private int lineWidth;
            private int radius;
            /**
             * hover : {"enabled":true,"fillColor":"","lineColor":"#FFFFFF","lineWidth":0,"lineWidthPlus":1,"radius":null,"radiusPlus":2}
             * select : {"enabled":true,"fillColor":"null","lineColor":"#000000","lineWidth":0,"radius":null}
             */

            private StatesBean states;
            private String symbol;
            private Object width;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getFillColor() {
                return fillColor;
            }

            public void setFillColor(String fillColor) {
                this.fillColor = fillColor;
            }

            public Object getHeight() {
                return height;
            }

            public void setHeight(Object height) {
                this.height = height;
            }

            public String getLineColor() {
                return lineColor;
            }

            public void setLineColor(String lineColor) {
                this.lineColor = lineColor;
            }

            public int getLineWidth() {
                return lineWidth;
            }

            public void setLineWidth(int lineWidth) {
                this.lineWidth = lineWidth;
            }

            public int getRadius() {
                return radius;
            }

            public void setRadius(int radius) {
                this.radius = radius;
            }

            public StatesBean getStates() {
                return states;
            }

            public void setStates(StatesBean states) {
                this.states = states;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }

            public Object getWidth() {
                return width;
            }

            public void setWidth(Object width) {
                this.width = width;
            }

            public static class StatesBean {
                /**
                 * enabled : true
                 * fillColor :
                 * lineColor : #FFFFFF
                 * lineWidth : 0
                 * lineWidthPlus : 1
                 * radius : null
                 * radiusPlus : 2
                 */

                private HoverBean hover;
                /**
                 * enabled : true
                 * fillColor : null
                 * lineColor : #000000
                 * lineWidth : 0
                 * radius : null
                 */

                private SelectBean select;

                public HoverBean getHover() {
                    return hover;
                }

                public void setHover(HoverBean hover) {
                    this.hover = hover;
                }

                public SelectBean getSelect() {
                    return select;
                }

                public void setSelect(SelectBean select) {
                    this.select = select;
                }

                public static class HoverBean {
                    private boolean enabled;
                    private String fillColor;
                    private String lineColor;
                    private int lineWidth;
                    private int lineWidthPlus;
                    private Object radius;
                    private int radiusPlus;

                    public boolean isEnabled() {
                        return enabled;
                    }

                    public void setEnabled(boolean enabled) {
                        this.enabled = enabled;
                    }

                    public String getFillColor() {
                        return fillColor;
                    }

                    public void setFillColor(String fillColor) {
                        this.fillColor = fillColor;
                    }

                    public String getLineColor() {
                        return lineColor;
                    }

                    public void setLineColor(String lineColor) {
                        this.lineColor = lineColor;
                    }

                    public int getLineWidth() {
                        return lineWidth;
                    }

                    public void setLineWidth(int lineWidth) {
                        this.lineWidth = lineWidth;
                    }

                    public int getLineWidthPlus() {
                        return lineWidthPlus;
                    }

                    public void setLineWidthPlus(int lineWidthPlus) {
                        this.lineWidthPlus = lineWidthPlus;
                    }

                    public Object getRadius() {
                        return radius;
                    }

                    public void setRadius(Object radius) {
                        this.radius = radius;
                    }

                    public int getRadiusPlus() {
                        return radiusPlus;
                    }

                    public void setRadiusPlus(int radiusPlus) {
                        this.radiusPlus = radiusPlus;
                    }
                }

                public static class SelectBean {
                    private boolean enabled;
                    private String fillColor;
                    private String lineColor;
                    private int lineWidth;
                    private Object radius;

                    public boolean isEnabled() {
                        return enabled;
                    }

                    public void setEnabled(boolean enabled) {
                        this.enabled = enabled;
                    }

                    public String getFillColor() {
                        return fillColor;
                    }

                    public void setFillColor(String fillColor) {
                        this.fillColor = fillColor;
                    }

                    public String getLineColor() {
                        return lineColor;
                    }

                    public void setLineColor(String lineColor) {
                        this.lineColor = lineColor;
                    }

                    public int getLineWidth() {
                        return lineWidth;
                    }

                    public void setLineWidth(int lineWidth) {
                        this.lineWidth = lineWidth;
                    }

                    public Object getRadius() {
                        return radius;
                    }

                    public void setRadius(Object radius) {
                        this.radius = radius;
                    }
                }
            }
        }
    }
}
