/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author nhatm
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;

public class BangThongKeByDateGUI {
    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                null,                     // Tiêu đề biểu đồ
                "Ngày",                   // Nhãn trục X
                "Thu nhập (đ)",           // Nhãn trục Y
                dataset, 
                PlotOrientation.VERTICAL,
                false,                    // Chú giải (legend)
                true,
                false
        );

        // Tiêu đề phụ
        chart.addSubtitle(new TextTitle("Thu nhập cửa hàng", new Font("SansSerif", Font.BOLD, 14)));

        // Cài đặt plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setRangePannable(true);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        plot.setOutlineVisible(false);
        
        // Thiết lập trục Y
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRange(true); // Tự động điều chỉnh phạm vi theo giá trị lớn nhất
        rangeAxis.setAutoRangeIncludesZero(true);
        
        // Cài đặt renderer cho biểu đồ cột
        BarRenderer renderer = new BarRenderer();
        renderer.setSeriesPaint(0, new Color(255, 105, 180));  // Màu hồng cho các cột
        renderer.setBarPainter(new BarRenderer().getBarPainter()); // Loại bỏ gradient mặc định
        plot.setRenderer(renderer);

        // Cài đặt màu nền
        chart.setBackgroundPaint(new Color(242, 242, 242));
        plot.setBackgroundPaint(new Color(242, 242, 242));

        ChartUtils.applyCurrentTheme(chart);

        return chart;
    }

    public static JPanel createChartPanel(CategoryDataset dataset) {
        JFreeChart chart = createChart(dataset);
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(false); // Tắt lăn chuột
        panel.setBackground(Color.WHITE);
        return panel;
    }
}
