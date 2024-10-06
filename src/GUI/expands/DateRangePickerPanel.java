/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.expands;

/**
 *
 * @author nhatm
 */

import org.jdatepicker.impl.*;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;


public class DateRangePickerPanel extends JPanel {
    
    public LocalDate fromDate;
    public LocalDate toDate;
    
    public DateRangePickerPanel() {
        setLayout(new BorderLayout());

        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        UtilDateModel modelFrom = new UtilDateModel();
        UtilDateModel modelTo = new UtilDateModel();
       
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH)); // Ngày hôm nay
        calendar.add(Calendar.MONTH, -1);
        fromDate = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Calendar sang LocalDate
        modelFrom.setValue(Date.from(fromDate.atStartOfDay(ZoneId.systemDefault()).toInstant())); 
        
        calendar = Calendar.getInstance(); 
        toDate = LocalDate.now(); // Ngày hôm nay
        modelTo.setValue(Date.from(toDate.atStartOfDay(ZoneId.systemDefault()).toInstant())); // Chuyển đổi LocalDate thành Date cho date picker
        
        Properties p = new Properties();
        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
        
        JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
        JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
        
        Dimension datePickerSize = new Dimension(120, 30);
        datePickerFrom.setPreferredSize(datePickerSize);
        datePickerTo.setPreferredSize(datePickerSize);

        JLabel resultLabel = new JLabel();
        
        datePanel.add(new JLabel("Từ ngày:"));
        datePanel.add(datePickerFrom);
        datePanel.add(new JLabel("Đến ngày:"));
        datePanel.add(datePickerTo);

        datePickerFrom.addActionListener(e -> {
            Date selectedDate = (Date) datePickerFrom.getModel().getValue();
            if (selectedDate != null) {
                fromDate = selectedDate.toInstant()
                              .atZone(ZoneId.systemDefault()).toLocalDate();
            }
        });

        datePickerTo.addActionListener(e -> {
            Date selectedDate = (Date) datePickerTo.getModel().getValue();
            if (selectedDate != null) {
                toDate = selectedDate.toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
            }
        });

        add(datePanel, BorderLayout.NORTH);
    }
    
    public LocalDate getDateStart(){
        return fromDate;
    }
    
    public LocalDate getDateEnd(){
        return toDate;
    }
    
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
        private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        @Override
        public Object stringToValue(String text) throws java.text.ParseException {
            return sdf.parseObject(text);
        }

        @Override
        public String valueToString(Object value) {
            if (value != null) {
                if (value instanceof GregorianCalendar) {
                    GregorianCalendar calendar = (GregorianCalendar) value;
                    Date date = calendar.getTime();
                    return sdf.format(date);
                } else if (value instanceof Date) {
                    return sdf.format((Date) value);
                }
            }
            return "";
        }
    }
}

