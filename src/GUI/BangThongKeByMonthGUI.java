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
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.title.Title;
import org.jfree.data.category.CategoryDataset;

public class BangThongKeByMonthGUI {

    private static JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createLineChart(null, "Tháng", "Thu nhập(100K)", dataset, PlotOrientation.VERTICAL, false, true, false);
        chart.addSubtitle((Title) new TextTitle("Thu nhập cửa hàng"));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangePannable(true); // cho phép panning (di chuyển) trên trục phạm vi.
        plot.setRangeGridlinesVisible(false); //Dòng này ẩn lưới trên trục phạm vi.
        plot.getDomainAxis().setCategoryLabelPositions(
                CategoryLabelPositions.UP_45); //đặt vị trí của nhãn danh mục trên trục X thành góc 45 độ.
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); // trục chỉ hiển thị nhãn cho các số nguyên.
        ChartUtils.applyCurrentTheme(chart); //áp dụng theme hiện tại cho biểu đồ.
        LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        renderer.setDefaultShapesVisible(true); //làm cho các hình dạng mặc định (hình tròn) hiển thị trên biểu đồ.
        renderer.setDrawOutlines(true); // thêm viền cho các hình dạng
        renderer.setUseFillPaint(true); // cho phép tô màu cho các hình dạng
//        renderer.setDefaultFillPaint(Color.WHITE);
        renderer.setSeriesStroke(0, new BasicStroke(3.0F)); // đặt độ dày nét cho đường kẻ của chuỗi dữ liệu đầu tiên thành 3.0 pixel
        renderer.setSeriesOutlineStroke(0, new BasicStroke(2.0F)); //đặt độ dày nét viền cho đường kẻ của chuỗi dữ liệu đầu tiên thành 2.0 pixel
        renderer.setSeriesShape(0, new Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D)); //đặt hình dạng cho các điểm dữ liệu của chuỗi dữ liệu đầu tiên
        return chart; // Trả về biểu đồ
    }

    public static JPanel createChartPanel(CategoryDataset dataset) {
        JFreeChart chart = createChart(dataset);
        chart.getPlot().setBackgroundPaint(new Color(242, 242, 242));
        ChartPanel panel = new ChartPanel(chart);
        panel.setMouseWheelEnabled(true); // kích hoạt cuộn chuột
        panel.setBackground(Color.WHITE);
        return (JPanel) panel;
    }
}
