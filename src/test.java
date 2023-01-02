import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import java.awt.Color;

import info.monitorenter.gui.chart.Chart2D;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DLtd;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class test{
    public static void main(String args[]) throws Exception{

        Component[] components = {
            new DC_V(new Point[]{new Point(0 , 0) , new Point(0 , 1)} , new Double[] {60.0}),
            new Resistance(new Point[]{new Point(0 , 1) , new Point(1 , 1)} , new Double[] {5.0}),
            new Resistance(new Point[]{new Point(0 , 0) , new Point(1 , 0)} , new Double[] {5.0}),
            new Resistance(new Point[]{new Point(1 , 0) , new Point(1 , 1)} , new Double[] {5.0})
        };
        CircuitSimulator simulator = new CircuitSimulator(components);

        Net net = new Net();
        ITrace2D trace = new Trace2DLtd(50);
        trace.setColor(Color.RED);
        Chart2D chart = new Chart2D();
        chart.addTrace(trace);
        JFrame frame = new JFrame();

        frame.getContentPane().add(chart);

        frame.setSize(400,300);
        frame.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                }
        );
        frame.setVisible(true);

        Scanner in = new Scanner(System.in);
        for(;;){
            HashMap<String ,Double> vars = simulator.tick();
            double x = simulator.currentTime_ms;
            double y = vars.get("I0.0,0.0:0.0,1.0");
            trace.addPoint(x , y);    
            TimeUnit.MILLISECONDS.sleep(20);  
            in.next();
        }
        
    }
}