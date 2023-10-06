package com.iith.sem3.datamining;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;  
  
public class LinePlot extends JFrame {  
  
  private static final long serialVersionUID = 1L;  
  
  private HashMap<String, long[]> mapTimeTaken;
  
  private double[] minsup;
  
  public LinePlot(String title, HashMap<String, long[]> mapTimeTaken, double[] minsup) {  
    super(title); 
    this.mapTimeTaken = mapTimeTaken;
    this.minsup = minsup;
    // Create dataset  
    DefaultCategoryDataset dataset = createDataset();  
    // Create chart  
    JFreeChart chart = ChartFactory.createLineChart(
        "Freq", // Chart title  
        "mnsup", // X-Axis Label  
        "Time in sec", // Y-Axis Label  
        dataset, PlotOrientation.VERTICAL, true, true, false  
        );
        
    ChartPanel panel = new ChartPanel(chart);  
    setContentPane(panel);  
  }  
  
  private DefaultCategoryDataset createDataset() {  
  
 
    DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
    
    for (Map.Entry<String, long[]> entry : this.mapTimeTaken.entrySet()) {
        String key = entry.getKey();
        long[] value = entry.getValue();
        //
        for (int i = 0; i < value.length; i++) {
        	dataset.addValue(value[i], key, String.valueOf(minsup[i])); 
        }
    } 
  
    return dataset;  
  }  
   
}  