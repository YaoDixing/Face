package com.demo.highcharts.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yaodixing on 2016/10/13.
 */
public class XAxis implements Serializable {

    /**
     * allowDecimals : true
     * alternateGridColor : null
     * categories : null
     * ceiling : 0
     * dateTimeLabelFormats : null
     * endOnTick : false
     * events : {"afterSetExtremes":null,"setExtremes":null,"afterBreaks":"undefined","pointBreak":"undefined"}
     * floor : null
     * gridLineColor : #C0C0C0
     * gridLineDashStyle : Solid
     * gridLineWidth : 0
     * gridZIndex : 1
     * id : null
     * labels : {"align":"center","distance":15,"enabled":true,"format":"{value}","formatter":null,"maxStaggerLines":5,"overflow":"null","rotation":0,"staggerLines":null,"step":null,"style":null,"useHTML":false,"x":0,"y":"null","zIndex":7,"autoRotation":[-45],"autoRotationLimit":80}
     * lineColor : #C0D0E0
     * lineWidth : 1
     * linkedTo : null
     * max : null
     * maxPadding : 0.01
     * maxZoom : null
     * min : null
     * minPadding : 0.01
     * minRange : null
     * minTickInterval : null
     * minorGridLineColor : #E0E0E0
     * minorGridLineDashStyle : Solid
     * minorGridLineWidth : 1
     * minorTickColor : #A0A0A0
     * minorTickInterval : null
     * minorTickLength : 2
     * minorTickPosition : outside
     * minorTickWidth : 0
     * offset : 0
     * opposite : false
     * plotBands : [{"borderColor":"null","borderWidth":0,"color":"null","events":null,"from":null,"id":"null","label":{"align":"center","rotation":0,"style":null,"text":"null","textAlign":"null","useHTML":false,"verticalAlign":"top","x":null,"y":null},"to":null,"zIndex":null}]
     * plotLines : [{"color":"null","dashStyle":"Solid","events":null,"id":"null","label":{"align":"left","rotation":null,"style":null,"text":"","textAlign":"null","useHTML":false,"verticalAlign":"top","x":null,"y":null},"value":null,"width":null,"zIndex":null}]
     * reversed : false
     * showEmpty : true
     * showFirstLabel : true
     * showLastLabel : true
     * startOfWeek : 1
     * startOnTick : false
     * tickColor : #C0D0E0
     * tickInterval : null
     * tickLength : 10
     * tickPixelInterval : null
     * tickPosition : outside
     * tickPositioner : null
     * tickPositions : null
     * tickWidth : 1
     * tickmarkPlacement : null
     * title : {"align":"middle","enabled":"middle","margin":0,"offset":0,"rotation":0,"style":["object Object"],"text":"null"}
     * type : linear
     * breaks : [{"breakSize":0,"from":0,"repeat":0,"to":0}]
     * tickAmount : undefined
     */

    private boolean allowDecimals;
    private String alternateGridColor;
    private Object categories;
    private int ceiling;
    private Object dateTimeLabelFormats;
    private boolean endOnTick;
    /**
     * afterSetExtremes : null
     * setExtremes : null
     * afterBreaks : undefined
     * pointBreak : undefined
     */

    private EventsBean events;
    private String floor;
    private String gridLineColor;
    private String gridLineDashStyle;
    private int gridLineWidth;
    private int gridZIndex;
    private String id;
    /**
     * align : center
     * distance : 15
     * enabled : true
     * format : {value}
     * formatter : null
     * maxStaggerLines : 5
     * overflow : null
     * rotation : 0
     * staggerLines : null
     * step : null
     * style : null
     * useHTML : false
     * x : 0
     * y : null
     * zIndex : 7
     * autoRotation : [-45]
     * autoRotationLimit : 80
     */

    private LabelsBean labels;
    private String lineColor;
    private int lineWidth;
    private Object linkedTo;
    private Object max;
    private double maxPadding;
    private Object maxZoom;
    private Object min;
    private double minPadding;
    private Object minRange;
    private Object minTickInterval;
    private String minorGridLineColor;
    private String minorGridLineDashStyle;
    private int minorGridLineWidth;
    private String minorTickColor;
    private Object minorTickInterval;
    private int minorTickLength;
    private String minorTickPosition;
    private int minorTickWidth;
    private int offset;
    private boolean opposite;
    private boolean reversed;
    private boolean showEmpty;
    private boolean showFirstLabel;
    private boolean showLastLabel;
    private int startOfWeek;
    private boolean startOnTick;
    private String tickColor;
    private Object tickInterval;
    private int tickLength;
    private Object tickPixelInterval;
    private String tickPosition;
    private Object tickPositioner;
    private Object tickPositions;
    private int tickWidth;
    private String tickmarkPlacement;
    /**
     * align : middle
     * enabled : middle
     * margin : 0
     * offset : 0
     * rotation : 0
     * style : ["object Object"]
     * text : null
     */

    private TitleBean title;
    private String type;
    private String tickAmount;
    /**
     * borderColor : null
     * borderWidth : 0
     * color : null
     * events : null
     * from : null
     * id : null
     * label : {"align":"center","rotation":0,"style":null,"text":"null","textAlign":"null","useHTML":false,"verticalAlign":"top","x":null,"y":null}
     * to : null
     * zIndex : null
     */

    private List<PlotBandsBean> plotBands;
    /**
     * color : null
     * dashStyle : Solid
     * events : null
     * id : null
     * label : {"align":"left","rotation":null,"style":null,"text":"","textAlign":"null","useHTML":false,"verticalAlign":"top","x":null,"y":null}
     * value : null
     * width : null
     * zIndex : null
     */

    private List<PlotLinesBean> plotLines;
    /**
     * breakSize : 0
     * from : 0
     * repeat : 0
     * to : 0
     */

    private List<BreaksBean> breaks;

    public boolean isAllowDecimals() {
        return allowDecimals;
    }

    public void setAllowDecimals(boolean allowDecimals) {
        this.allowDecimals = allowDecimals;
    }

    public String getAlternateGridColor() {
        return alternateGridColor;
    }

    public void setAlternateGridColor(String alternateGridColor) {
        this.alternateGridColor = alternateGridColor;
    }

    public Object getCategories() {
        return categories;
    }

    public void setCategories(Object categories) {
        this.categories = categories;
    }

    public int getCeiling() {
        return ceiling;
    }

    public void setCeiling(int ceiling) {
        this.ceiling = ceiling;
    }

    public Object getDateTimeLabelFormats() {
        return dateTimeLabelFormats;
    }

    public void setDateTimeLabelFormats(Object dateTimeLabelFormats) {
        this.dateTimeLabelFormats = dateTimeLabelFormats;
    }

    public boolean isEndOnTick() {
        return endOnTick;
    }

    public void setEndOnTick(boolean endOnTick) {
        this.endOnTick = endOnTick;
    }

    public EventsBean getEvents() {
        return events;
    }

    public void setEvents(EventsBean events) {
        this.events = events;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getGridLineColor() {
        return gridLineColor;
    }

    public void setGridLineColor(String gridLineColor) {
        this.gridLineColor = gridLineColor;
    }

    public String getGridLineDashStyle() {
        return gridLineDashStyle;
    }

    public void setGridLineDashStyle(String gridLineDashStyle) {
        this.gridLineDashStyle = gridLineDashStyle;
    }

    public int getGridLineWidth() {
        return gridLineWidth;
    }

    public void setGridLineWidth(int gridLineWidth) {
        this.gridLineWidth = gridLineWidth;
    }

    public int getGridZIndex() {
        return gridZIndex;
    }

    public void setGridZIndex(int gridZIndex) {
        this.gridZIndex = gridZIndex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LabelsBean getLabels() {
        return labels;
    }

    public void setLabels(LabelsBean labels) {
        this.labels = labels;
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

    public Object getLinkedTo() {
        return linkedTo;
    }

    public void setLinkedTo(Object linkedTo) {
        this.linkedTo = linkedTo;
    }

    public Object getMax() {
        return max;
    }

    public void setMax(Object max) {
        this.max = max;
    }

    public double getMaxPadding() {
        return maxPadding;
    }

    public void setMaxPadding(double maxPadding) {
        this.maxPadding = maxPadding;
    }

    public Object getMaxZoom() {
        return maxZoom;
    }

    public void setMaxZoom(Object maxZoom) {
        this.maxZoom = maxZoom;
    }

    public Object getMin() {
        return min;
    }

    public void setMin(Object min) {
        this.min = min;
    }

    public double getMinPadding() {
        return minPadding;
    }

    public void setMinPadding(double minPadding) {
        this.minPadding = minPadding;
    }

    public Object getMinRange() {
        return minRange;
    }

    public void setMinRange(Object minRange) {
        this.minRange = minRange;
    }

    public Object getMinTickInterval() {
        return minTickInterval;
    }

    public void setMinTickInterval(Object minTickInterval) {
        this.minTickInterval = minTickInterval;
    }

    public String getMinorGridLineColor() {
        return minorGridLineColor;
    }

    public void setMinorGridLineColor(String minorGridLineColor) {
        this.minorGridLineColor = minorGridLineColor;
    }

    public String getMinorGridLineDashStyle() {
        return minorGridLineDashStyle;
    }

    public void setMinorGridLineDashStyle(String minorGridLineDashStyle) {
        this.minorGridLineDashStyle = minorGridLineDashStyle;
    }

    public int getMinorGridLineWidth() {
        return minorGridLineWidth;
    }

    public void setMinorGridLineWidth(int minorGridLineWidth) {
        this.minorGridLineWidth = minorGridLineWidth;
    }

    public String getMinorTickColor() {
        return minorTickColor;
    }

    public void setMinorTickColor(String minorTickColor) {
        this.minorTickColor = minorTickColor;
    }

    public Object getMinorTickInterval() {
        return minorTickInterval;
    }

    public void setMinorTickInterval(Object minorTickInterval) {
        this.minorTickInterval = minorTickInterval;
    }

    public int getMinorTickLength() {
        return minorTickLength;
    }

    public void setMinorTickLength(int minorTickLength) {
        this.minorTickLength = minorTickLength;
    }

    public String getMinorTickPosition() {
        return minorTickPosition;
    }

    public void setMinorTickPosition(String minorTickPosition) {
        this.minorTickPosition = minorTickPosition;
    }

    public int getMinorTickWidth() {
        return minorTickWidth;
    }

    public void setMinorTickWidth(int minorTickWidth) {
        this.minorTickWidth = minorTickWidth;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOpposite() {
        return opposite;
    }

    public void setOpposite(boolean opposite) {
        this.opposite = opposite;
    }

    public boolean isReversed() {
        return reversed;
    }

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    public boolean isShowEmpty() {
        return showEmpty;
    }

    public void setShowEmpty(boolean showEmpty) {
        this.showEmpty = showEmpty;
    }

    public boolean isShowFirstLabel() {
        return showFirstLabel;
    }

    public void setShowFirstLabel(boolean showFirstLabel) {
        this.showFirstLabel = showFirstLabel;
    }

    public boolean isShowLastLabel() {
        return showLastLabel;
    }

    public void setShowLastLabel(boolean showLastLabel) {
        this.showLastLabel = showLastLabel;
    }

    public int getStartOfWeek() {
        return startOfWeek;
    }

    public void setStartOfWeek(int startOfWeek) {
        this.startOfWeek = startOfWeek;
    }

    public boolean isStartOnTick() {
        return startOnTick;
    }

    public void setStartOnTick(boolean startOnTick) {
        this.startOnTick = startOnTick;
    }

    public String getTickColor() {
        return tickColor;
    }

    public void setTickColor(String tickColor) {
        this.tickColor = tickColor;
    }

    public Object getTickInterval() {
        return tickInterval;
    }

    public void setTickInterval(Object tickInterval) {
        this.tickInterval = tickInterval;
    }

    public int getTickLength() {
        return tickLength;
    }

    public void setTickLength(int tickLength) {
        this.tickLength = tickLength;
    }

    public Object getTickPixelInterval() {
        return tickPixelInterval;
    }

    public void setTickPixelInterval(Object tickPixelInterval) {
        this.tickPixelInterval = tickPixelInterval;
    }

    public String getTickPosition() {
        return tickPosition;
    }

    public void setTickPosition(String tickPosition) {
        this.tickPosition = tickPosition;
    }

    public Object getTickPositioner() {
        return tickPositioner;
    }

    public void setTickPositioner(Object tickPositioner) {
        this.tickPositioner = tickPositioner;
    }

    public Object getTickPositions() {
        return tickPositions;
    }

    public void setTickPositions(Object tickPositions) {
        this.tickPositions = tickPositions;
    }

    public int getTickWidth() {
        return tickWidth;
    }

    public void setTickWidth(int tickWidth) {
        this.tickWidth = tickWidth;
    }

    public String getTickmarkPlacement() {
        return tickmarkPlacement;
    }

    public void setTickmarkPlacement(String tickmarkPlacement) {
        this.tickmarkPlacement = tickmarkPlacement;
    }

    public TitleBean getTitle() {
        return title;
    }

    public void setTitle(TitleBean title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTickAmount() {
        return tickAmount;
    }

    public void setTickAmount(String tickAmount) {
        this.tickAmount = tickAmount;
    }

    public List<PlotBandsBean> getPlotBands() {
        return plotBands;
    }

    public void setPlotBands(List<PlotBandsBean> plotBands) {
        this.plotBands = plotBands;
    }

    public List<PlotLinesBean> getPlotLines() {
        return plotLines;
    }

    public void setPlotLines(List<PlotLinesBean> plotLines) {
        this.plotLines = plotLines;
    }

    public List<BreaksBean> getBreaks() {
        return breaks;
    }

    public void setBreaks(List<BreaksBean> breaks) {
        this.breaks = breaks;
    }

    public static class EventsBean {
        private Object afterSetExtremes;
        private Object setExtremes;
        private String afterBreaks;
        private String pointBreak;

        public Object getAfterSetExtremes() {
            return afterSetExtremes;
        }

        public void setAfterSetExtremes(Object afterSetExtremes) {
            this.afterSetExtremes = afterSetExtremes;
        }

        public Object getSetExtremes() {
            return setExtremes;
        }

        public void setSetExtremes(Object setExtremes) {
            this.setExtremes = setExtremes;
        }

        public String getAfterBreaks() {
            return afterBreaks;
        }

        public void setAfterBreaks(String afterBreaks) {
            this.afterBreaks = afterBreaks;
        }

        public String getPointBreak() {
            return pointBreak;
        }

        public void setPointBreak(String pointBreak) {
            this.pointBreak = pointBreak;
        }
    }

    public static class LabelsBean {
        private String align;
        private int distance;
        private boolean enabled;
        private String format;
        private Object formatter;
        private int maxStaggerLines;
        private String overflow;
        private int rotation;
        private Object staggerLines;
        private Object step;
        private Object style;
        private boolean useHTML;
        private int x;
        private String y;
        private int zIndex;
        private int autoRotationLimit;
        private List<Integer> autoRotation;

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Object getFormatter() {
            return formatter;
        }

        public void setFormatter(Object formatter) {
            this.formatter = formatter;
        }

        public int getMaxStaggerLines() {
            return maxStaggerLines;
        }

        public void setMaxStaggerLines(int maxStaggerLines) {
            this.maxStaggerLines = maxStaggerLines;
        }

        public String getOverflow() {
            return overflow;
        }

        public void setOverflow(String overflow) {
            this.overflow = overflow;
        }

        public int getRotation() {
            return rotation;
        }

        public void setRotation(int rotation) {
            this.rotation = rotation;
        }

        public Object getStaggerLines() {
            return staggerLines;
        }

        public void setStaggerLines(Object staggerLines) {
            this.staggerLines = staggerLines;
        }

        public Object getStep() {
            return step;
        }

        public void setStep(Object step) {
            this.step = step;
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

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public int getZIndex() {
            return zIndex;
        }

        public void setZIndex(int zIndex) {
            this.zIndex = zIndex;
        }

        public int getAutoRotationLimit() {
            return autoRotationLimit;
        }

        public void setAutoRotationLimit(int autoRotationLimit) {
            this.autoRotationLimit = autoRotationLimit;
        }

        public List<Integer> getAutoRotation() {
            return autoRotation;
        }

        public void setAutoRotation(List<Integer> autoRotation) {
            this.autoRotation = autoRotation;
        }
    }

    public static class TitleBean {
        private String align;
        private String enabled;
        private int margin;
        private int offset;
        private int rotation;
        private String text;
        private List<String> style;

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        public String getEnabled() {
            return enabled;
        }

        public void setEnabled(String enabled) {
            this.enabled = enabled;
        }

        public int getMargin() {
            return margin;
        }

        public void setMargin(int margin) {
            this.margin = margin;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getRotation() {
            return rotation;
        }

        public void setRotation(int rotation) {
            this.rotation = rotation;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public List<String> getStyle() {
            return style;
        }

        public void setStyle(List<String> style) {
            this.style = style;
        }
    }

    public static class PlotBandsBean {
        private String borderColor;
        private int borderWidth;
        private String color;
        private Object events;
        private Object from;
        private String id;
        /**
         * align : center
         * rotation : 0
         * style : null
         * text : null
         * textAlign : null
         * useHTML : false
         * verticalAlign : top
         * x : null
         * y : null
         */

        private LabelBean label;
        private Object to;
        private Object zIndex;

        public String getBorderColor() {
            return borderColor;
        }

        public void setBorderColor(String borderColor) {
            this.borderColor = borderColor;
        }

        public int getBorderWidth() {
            return borderWidth;
        }

        public void setBorderWidth(int borderWidth) {
            this.borderWidth = borderWidth;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Object getEvents() {
            return events;
        }

        public void setEvents(Object events) {
            this.events = events;
        }

        public Object getFrom() {
            return from;
        }

        public void setFrom(Object from) {
            this.from = from;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LabelBean getLabel() {
            return label;
        }

        public void setLabel(LabelBean label) {
            this.label = label;
        }

        public Object getTo() {
            return to;
        }

        public void setTo(Object to) {
            this.to = to;
        }

        public Object getZIndex() {
            return zIndex;
        }

        public void setZIndex(Object zIndex) {
            this.zIndex = zIndex;
        }

        public static class LabelBean {
            private String align;
            private int rotation;
            private Object style;
            private String text;
            private String textAlign;
            private boolean useHTML;
            private String verticalAlign;
            private Object x;
            private Object y;

            public String getAlign() {
                return align;
            }

            public void setAlign(String align) {
                this.align = align;
            }

            public int getRotation() {
                return rotation;
            }

            public void setRotation(int rotation) {
                this.rotation = rotation;
            }

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

            public String getTextAlign() {
                return textAlign;
            }

            public void setTextAlign(String textAlign) {
                this.textAlign = textAlign;
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
        }
    }

    public static class PlotLinesBean {
        private String color;
        private String dashStyle;
        private Object events;
        private String id;
        /**
         * align : left
         * rotation : null
         * style : null
         * text :
         * textAlign : null
         * useHTML : false
         * verticalAlign : top
         * x : null
         * y : null
         */

        private LabelBean label;
        private Object value;
        private Object width;
        private Object zIndex;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDashStyle() {
            return dashStyle;
        }

        public void setDashStyle(String dashStyle) {
            this.dashStyle = dashStyle;
        }

        public Object getEvents() {
            return events;
        }

        public void setEvents(Object events) {
            this.events = events;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public LabelBean getLabel() {
            return label;
        }

        public void setLabel(LabelBean label) {
            this.label = label;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Object getWidth() {
            return width;
        }

        public void setWidth(Object width) {
            this.width = width;
        }

        public Object getZIndex() {
            return zIndex;
        }

        public void setZIndex(Object zIndex) {
            this.zIndex = zIndex;
        }

        public static class LabelBean {
            private String align;
            private Object rotation;
            private Object style;
            private String text;
            private String textAlign;
            private boolean useHTML;
            private String verticalAlign;
            private Object x;
            private Object y;

            public String getAlign() {
                return align;
            }

            public void setAlign(String align) {
                this.align = align;
            }

            public Object getRotation() {
                return rotation;
            }

            public void setRotation(Object rotation) {
                this.rotation = rotation;
            }

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

            public String getTextAlign() {
                return textAlign;
            }

            public void setTextAlign(String textAlign) {
                this.textAlign = textAlign;
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
        }
    }

    public static class BreaksBean {
        private int breakSize;
        private int from;
        private int repeat;
        private int to;

        public int getBreakSize() {
            return breakSize;
        }

        public void setBreakSize(int breakSize) {
            this.breakSize = breakSize;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getRepeat() {
            return repeat;
        }

        public void setRepeat(int repeat) {
            this.repeat = repeat;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }
    }
}
