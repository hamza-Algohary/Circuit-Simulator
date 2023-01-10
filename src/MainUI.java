///import info.monitorenter.gui.chart.demos.I_Chart2D;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.*;

public class MainUI {
    static JButton b1;
    static JButton b2;
    static JButton b3;
    static JButton b4;
    static JButton b5;
    static JButton b6;
    static JTextField tF1;
    static JTextField tF2;
    static JTextField tF3;
    static int rows = 10;
    static int cols = 10;
    static int i = 0;
    static ComponentUI[][] button = new ComponentUI[rows][cols];
    // static JLabel []label=new JLabel[100];
    static JLabel vlabel = new JLabel("V = "), ilabel = new JLabel("I = "), rlabel = new JLabel("R = ");
    // enum Mode{SELECT , SHOW_INFO , CHANGE}
    static final int MODE_SELECT = 1, MODE_CHANGE = 0;
    static CircularInt mode = new CircularInt(0, 1);
    static ComponentUI selectedComponentUI = new ComponentUI();
    static CircuitSimulator simulator;
    static HashMap<String , Double> values = new HashMap<>();

    public static Component[] getComponenets() {
        Vector<Component> components = new Vector<>();
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                try{
                    if(!button[i][j].isEmpty())
                        components.add(button[i][j].getComponent(i, j));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return components.toArray(new Component[0]);
    }

    public static void main(String[] args)throws Exception {

        b3 = new JButton("Oscilliscope");
        b3.setBounds(100,50,100,50);
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==b3){
                    new I_Chart2D();
                }
            }
        });
        JPanel p4 = new JPanel();
        p4.setBounds(700,50,300,150);
        //p4.setBackground(Color.RED);
        p4.setLayout(null);
        p4.add(b3);

        b4 = new JButton("Oscilliscope");
        b4.setBounds(100,50,100,50);
        b4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                if (a.getSource()==b3){
                    new I_Chart2D();
                }
            }
        });
        JPanel p5 = new JPanel();
        p5.setBounds(700,250,300,150);
        //p5.setBackground(Color.blue);
        p5.setLayout(null);
        p5.add(b4);

        JPanel p6 = new JPanel();
        p6.setLayout(new GridLayout());
        p6.setBounds(700,450,300,77);
        //p6.setBackground(Color.black);
        p6.add(vlabel);


        JPanel p7 = new JPanel();
        p7.setLayout(new GridLayout());
        p7.setBounds(700,527,300,76);
        //p7.setBackground(Color.gray);
        p7.add(ilabel);


        JPanel p8 = new JPanel();
        p8.setLayout(new GridLayout());
        p8.setBounds(700,603,300,77);
        //p8.setBackground(Color.green);
        p8.add(rlabel);

 

        b1 = new JButton("RUN");
        b1.setBounds(0,0,50,40);
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent n){
                if (n.getSource()==b1){
                    try{
                        simulator = new CircuitSimulator(getComponenets());
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    try{
                        values = simulator.solve();
                        values.forEach((String name , Double value)->{
                            System.out.println(name + " = " + value);
                        });
                    }catch(Exception e){
                        e.printStackTrace();
                        //System.out.println(e);
                    }
                    
                }
            }
        });

        b2 = new JButton("Rotate");
        b2.setBounds(50,0,50,40);
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent l) {
                if (l.getSource()==b2){
                    selectedComponentUI.rotate();
                }
            }
        });

        b5 = new JButton("Change");
        b5.setBounds(75,0,50,40);
        b5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent l) {
                if (l.getSource()==b5){
                    //System.out.println("Here is Select");
                    mode.inc();
                    switch(mode.get()){
                        case MODE_CHANGE:
                            b5.setText("Change");
                            break;
                        case MODE_SELECT:
                            b5.setText("Select");
                            break;
                        default:
                            System.out.println("!!!Strange!!!");
                    }
                }
            }
        });

        b6 = new JButton("Show info");
        b6.setBounds(100,0,50,40);
        b6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent l) {
                if (l.getSource()==b6){
                    //System.out.println("Here is Show info");

                }
            }
        });

        tF1 = new JTextField();
        tF1.setBounds(50,5,200,40);
        tF1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent o) {
                if (o.getSource()==b3){

                }
            }
        });

        JPanel p9 = new JPanel();
        p9.setBounds(700,200,300,50);
        p9.setLayout(null);
        p9.add(tF1);

        tF2 = new JTextField();
        tF2.setBounds(50,5,100,40);
        tF2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent u) {
                if (u.getSource()==b3){

                }
            }
        });

        tF3 = new JTextField();
        tF3.setBounds(150,5,100,40);
        tF3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent s) {
                if (s.getSource()==b3){

                }
            }
        });

        JPanel p10 = new JPanel();
        p10.setBounds(700,400,300,50);
        p10.setLayout(null);
        p10.add(tF2);
        p10.add(tF3);

        JPanel p1 = new JPanel();
        p1.setBounds(0,0,1000,50);
        //p1.setBackground(Color.CYAN);
        p1.add(b1);
        p1.add(b2);
        p1.add(b5);
        p1.add(b6);
        //p1.add(vlabel);

        JPanel p2 = new JPanel();
        p2.setBounds(0,50,700,630);
        //p2.setBackground(Color.orange);
        p2.setLayout(new GridLayout(rows,cols));
        //p2.add(ilabel);
        //Integer i = 0 , j = 0;
        for(int i=0; i<button.length; i++)
        {
            for(int j=0 ;j<button.length ;j++)
            {
                button[i][j]=new ComponentUI();
                p2.add(button[i][j]);
                button[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent g) {
                        //if (g.getSource()==b5){
                            //System.out.println("Here is Select");
                        //}
                        ComponentUI target = (ComponentUI) g.getSource();
                        switch(mode.get()){
                            case MODE_CHANGE:
                                Frame_1 frame_1 = new Frame_1(target);
                                selectedComponentUI = new ComponentUI();
                                break;
                            case MODE_SELECT:
                                selectedComponentUI = target;
                                break;
                        }
                        
                    }
                });
            }
        }
        JFrame frame = new JFrame();
        frame.setSize(1000,680);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(p1);
        frame.add(p2);
        frame.add(p4);
        frame.add(p9);
        frame.add(p5);
        frame.add(p10);
        frame.add(p6);
        frame.add(p7);
        frame.add(p8);

        frame.setVisible(true);
        frame.setResizable(true);



    }

}