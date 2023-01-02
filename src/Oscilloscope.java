//package info.monitorenter.gui.chart.demos;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;
import info.monitorenter.gui.chart.views.ChartPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Oscilloscope extends Chart2D{

    //private final ITrace2D traceI = new Trace2DLtd(50);
    ITrace2D trace = new Trace2DLtd(50) ;
    Chart2D chart = new Chart2D();

    public Oscilloscope() {

        trace.setColor(Color.YELLOW);
        //traceI.setColor(Color.BLUE);
        //chart.addTrace(traceI);
        chart.addTrace(trace);

    }

    /*public void addCurrent(double time ,double I) {
        this.traceI.addPoint(time, I);
    }*/
    public void addPoint(double time ,double value) {
        this.trace.addPoint(time, value);
    }

    /*public static void main(String args[]){
        Oscilloscope oscilloscope = new Oscilloscope();
        JFrame frame = new JFrame();

        frame.getContentPane().add(oscilloscope);

        frame.setSize(400,300);
        frame.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                }
        );
        frame.setVisible(true);
    }*/


}
