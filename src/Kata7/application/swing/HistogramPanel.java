package Kata7.application.swing;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import Kata7.model.Histogram;
import Kata7.view.HistogramDisplay;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistogramPanel extends JPanel implements HistogramDisplay {

    private Histogram<String> histogram;

    public HistogramPanel() {
        super(new BorderLayout());
    }
    private JFreeChart createChart(DefaultCategoryDataset dataSet) {
        JFreeChart chart = ChartFactory.createBarChart(null, "", "", dataSet, PlotOrientation.VERTICAL, false, false, false);
        return chart;
    }
    private DefaultCategoryDataset createDataset(Histogram histogram) {
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        for (Object key : histogram.keySet()) {
            dataSet.addValue(histogram.get(key), "", (Comparable) key);
        }
        return dataSet;
    }
    @Override
    public Histogram histogram() {
        return histogram;
    }
    @Override
    public void show(Histogram histogram) {
        this.histogram = histogram;
        this.reload();
    }
    private void reload() {
        this.removeAll();
        this.add(new ChartPanel(createChart(createDataset(histogram))));
        this.revalidate();
    }
}
