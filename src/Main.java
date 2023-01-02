import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main implements ActionListener {
    static JButton b1;
    static JButton b2;
    static JButton b3;
    static JButton b4;
    static JTextField tF1;
    static JTextField tF2;
    static JTextField tF3;
    static int rows =10;
    static int cols =10;
    static int i=0;
    static JButton [][] button=new ComponentUI[rows][cols];
    static JLabel []label=new JLabel[100];
    public static void main(String[] args) {

        b3 = new JButton("Click me");
        b3.setBounds(100,50,100,50);
        JPanel p4 = new JPanel();
        p4.setBounds(700,50,300,150);
        p4.setBackground(Color.RED);
        p4.setLayout(null);
        p4.add(b3);

        b4 = new JButton("Click me");
        b4.setBounds(100,50,100,50);
        JPanel p5 = new JPanel();
        p5.setBounds(700,250,300,150);
        p5.setBackground(Color.blue);
        p5.setLayout(null);
        p5.add(b4);

        JPanel p6 = new JPanel();
        p6.setBounds(700,450,300,77);
        p6.setBackground(Color.black);


        JPanel p7 = new JPanel();
        p7.setBounds(700,527,300,76);
        p7.setBackground(Color.gray);


        JPanel p8 = new JPanel();
        p8.setBounds(700,603,300,77);
        p8.setBackground(Color.green);



        b1 = new JButton("RUN");
        b1.setBounds(0,0,50,40);

        b2 = new JButton("Rotate");
        b2.setBounds(75,0,50,40);

        tF1 = new JTextField();
        tF1.setBounds(50,5,200,40);

        JPanel p9 = new JPanel();
        p9.setBounds(700,200,300,50);
        p9.setLayout(null);
        p9.add(tF1);

        tF2 = new JTextField();
        tF2.setBounds(50,5,100,40);

        tF3 = new JTextField();
        tF3.setBounds(150,5,100,40);

        JPanel p10 = new JPanel();
        p10.setBounds(700,400,300,50);
        p10.setLayout(null);
        p10.add(tF2);
        p10.add(tF3);




        JPanel p1 = new JPanel();
        p1.setBounds(0,0,1000,50);
        p1.setBackground(Color.CYAN);
        p1.add(b1);
        p1.add(b2);

        JPanel p2 = new JPanel();
        p2.setBounds(0,50,700,630);
        p2.setBackground(Color.orange);
        p2.setLayout(new GridLayout(rows,cols));
        for(int i=0; i<button.length; i++)
        {
            for(int j=0 ;j<button.length ;j++)
            {
                button[i][j]=new ComponentUI();
                p2.add(button[i][j]);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        //if (e.getSource()==b1){}

        //if (e.getSource()==b2){}

        if (e.getSource()==b3){
            MinimalDynamicChart chart = new MinimalDynamicChart();
        }

        if (e.getSource()==b4){
           new MinimalDynamicChart();
           MinimalDynamicChart chart = new MinimalDynamicChart();
        }

        //if (e.getSource()==tF1){}
        //if (e.getSource()==tF2){}
       //if (e.getSource()==tF3){}
    }


}
