import java.awt.*;
import java.util.Vector;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.*;

public class Net extends JFrame {

    int rows = 10;
    int cols = 10;
    int i = 0;
    ComponentUI[][] button = new ComponentUI[rows][cols];
    //JLabel []label=new JLabel[100];

    public Net() {
        // this.setTitle("Hagag");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(rows, cols));

        this.setLocation(200, 200);
        this.setSize(400,300);
        this.addWindowListener(
                new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        System.exit(0);
                    }
                }
        );

        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                button[i][j]=new ComponentUI();
                add(button[i][j]);
            }
        }
        this.setVisible(true);
    }

    public Component[] getElectricComponents() {
        Vector<Component> components = new Vector<>();
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; j < button.length; j++) {
                components.add(button[i][j].getComponent(i, j));
            }
        }
        return components.toArray(new Component[0]);
    }

    /*
     * public String toString(int k)
     * {
     * k=i;
     * String s;
     * s=""+k;
     * return s;
     * }
     * 
     * public void setrows(int r)
     * {
     * rows=r;
     * }
     * public void setcols(int c)
     * {
     * cols=c;
     * }
     */

}