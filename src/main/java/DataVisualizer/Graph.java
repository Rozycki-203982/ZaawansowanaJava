package DataVisualizer;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Class which is responsible for visualizating graph with received data
 */
public class Graph extends ApplicationFrame {

    private final int windowAxis = 5;
    private CombinedDomainXYPlot plot;
    private int samplingRate;
    private Map<String, XYPlot> subplots;
    private Map<String, XYSeries> subSeries;

    public Graph(final String title, int samplingRate) {

        super(title);
        this.samplingRate = samplingRate;
        this.subplots = new HashMap<>();
        this.subSeries = new HashMap<>();
        plot = new CombinedDomainXYPlot(new NumberAxis("Czas"));
        plot.setGap(10.0);

        JFreeChart chart = new JFreeChart(
                "Pomiary EEG",
                JFreeChart.DEFAULT_TITLE_FONT,
                plot,
                false
        );

        ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
        final Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        panel.setPreferredSize(fullScreen);
        setContentPane(panel);
    }

    private void defineXAxes(List<Double> yAxis, String yAxisTitle) {

        subSeries.putIfAbsent(yAxisTitle, new XYSeries(""));
        int length = subSeries.get(yAxisTitle).toArray()[0].length;

        for (double i = length; i < length + yAxis.size(); i++) {

            subSeries.get(yAxisTitle).add(i / samplingRate, yAxis.get((int) i - length));
        }
    }

    private void createSubplot(String yAxisTitle) {

        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(subSeries.get(yAxisTitle));
        XYDataset data = collection;
        XYItemRenderer renderer = new StandardXYItemRenderer();
        NumberAxis yAx = new NumberAxis(yAxisTitle);

        subplots.putIfAbsent(yAxisTitle, new XYPlot(data, null, yAx, renderer));
        subplots.get(yAxisTitle).setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
        plot.add(subplots.get(yAxisTitle));

        updatePlot(yAxisTitle);
        plot.setOrientation(PlotOrientation.VERTICAL);
    }

    private void updatePlot(String yAxisTitle) {

        XYSeriesCollection collection = new XYSeriesCollection();

        collection.addSeries(subSeries.get(yAxisTitle));
        subplots.get(yAxisTitle).setDataset(collection);
        int arrLen = subSeries.get(yAxisTitle).toArray()[0].length;

        int timeMultiplier = arrLen / (samplingRate * windowAxis);
        int xOrigin = timeMultiplier * windowAxis * samplingRate;

        if (xOrigin >= arrLen)
            return;

        NumberAxis domain = (NumberAxis) subplots.get(yAxisTitle).getDomainAxis();
        domain.setRange(xOrigin / samplingRate, xOrigin / samplingRate + windowAxis);

        plot.setDomainAxis(domain);
    }

    public void vizualizeData(List<Double> eegData, String yAxisTitle) {

        defineXAxes(eegData, yAxisTitle);

        if (!subplots.containsKey(yAxisTitle)) {

            createSubplot(yAxisTitle);
        } else {

            updatePlot(yAxisTitle);
        }

        this.pack();
        this.setVisible(true);
    }
}
