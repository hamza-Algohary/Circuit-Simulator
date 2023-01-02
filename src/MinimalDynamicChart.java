import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
public class MinimalDynamicChart extends Chart2D {
    public MinimalDynamicChart() {


            Chart2D chart = new Chart2D();

            ITrace2D trace = new Trace2DLtd(200);
            trace.setColor(Color.RED);

            chart.addTrace(trace);

        JPanel panel = new JPanel();
        panel.add(chart, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(600,800) );
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.RED);

        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setSize(800,1200);
        frame.pack();
        frame.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                }
        );
        frame.setVisible(true);



    }
}