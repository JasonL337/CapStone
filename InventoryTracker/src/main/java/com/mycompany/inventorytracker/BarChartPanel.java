/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventorytracker;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author jampa
 */
public class BarChartPanel extends JPanel {
    private ArrayList<Integer> values;
    private ArrayList<String> names;

            public void setVals(ArrayList<Integer> data)
            {
                values = data;
            }
            
            public void setNames(ArrayList<String> data)
            {
                names = data;
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.setColor(Color.gray);
                g.fillRect(0, 0, getWidth(), getHeight());

                // Paint the border
                g.setColor(Color.BLACK);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                if (values == null || values.isEmpty()) {
                    return;
                }

                int panelWidth = getWidth();
        int panelHeight = getHeight();
        int maxValue = values.stream().max(Integer::compareTo).orElse(0);

        int totalBufferSpace = 20 * (values.size() - 1);
        int totalBarWidth = panelWidth - totalBufferSpace;
        int barWidth = totalBarWidth / values.size();
        int maxBarHeight = (int) (panelHeight * 0.8); // 4/5 of the panel height

        for (int i = 0; i < values.size(); i++) {
            int value = values.get(i);
            String item = names.get(i);
            int barHeight = (int) ((double) value / maxValue * maxBarHeight);
            int x = i * (barWidth + 20);
            int y = panelHeight - barHeight;

            // Draw the bar
            if (value < 5)
                g.setColor(Color.RED);
            else if (value < 10)
                g.setColor(Color.YELLOW);
            else
                g.setColor(Color.GREEN);
            g.fillRect(x, y, barWidth, barHeight);

            // Draw the label above the bar
            g.setColor(Color.BLACK);
            String label = item + ": " + String.valueOf(value);
            FontMetrics metrics = g.getFontMetrics();
            int labelWidth = metrics.stringWidth(label);
            g.drawString(label, x + (barWidth - labelWidth) / 2, y - 5);
        }
    }
}
