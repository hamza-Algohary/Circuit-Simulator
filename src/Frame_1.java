import javax.swing.JFrame;
import java.awt.*;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Frame_1 extends JFrame implements ActionListener{



    ComponentUI CUI;

    private String[] Cs = { Constants.UI.R,
                                    Constants.UI.DC_V,
                                    Constants.UI.DC_I,
                                    Constants.UI.AC_V,
                                    Constants.UI.AC_I,
                                    Constants.UI.W2,
                                    Constants.UI.W21,
                                    Constants.UI.W3,
                                    Constants.UI.W4,
                                    Constants.UI.C
                                };

    String x;                            

    JComboBox cB ;

    JTextField tF1;
    JTextField tF2;

    JButton b1;

    JLabel l1;
    JLabel l2;
    JLabel l3;
    JLabel l4;
    JLabel l5;

    public Frame_1(ComponentUI componentUI){
        this.CUI = componentUI;
        cB = new JComboBox<String>(Cs);
        cB.setSize(150,40);
        cB.addActionListener(this);

        tF1 = new JTextField();
        tF1.setSize(100,40);

        tF2 = new JTextField();
        tF2.setSize(100,40);

        b1 = new JButton("OK");
        b1.setFont(new Font("courier",Font.LAYOUT_RIGHT_TO_LEFT,20));
        b1.setSize(100,50);
        b1.setHorizontalAlignment(JLabel.CENTER);
        b1.addActionListener(this);
        b1.setFocusPainted(false);
        b1.setVisible(true);

        l5 = new JLabel("Choose a component");
        l5.setFont(new Font("Times New Roman",Font.BOLD,30));
        l5.setHorizontalAlignment(JLabel.CENTER);


        l1 = new JLabel("Component");
        l1.add(cB);


        l2 = new JLabel("Value 1");
        l2.add(tF1);
        l2.setHorizontalTextPosition(JLabel.LEFT);
        l2.setVerticalTextPosition(JLabel.CENTER);


        l3 = new JLabel("Value 2");
        l3.add(tF2);
        l3.setHorizontalTextPosition(JLabel.LEFT);
        l3.setVerticalTextPosition(JLabel.CENTER);


        l4 = new JLabel();
        l4.add(b1);



        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setSize(450,500);
        this.setLayout(new GridLayout(5,1));
        this.add(l5);
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);

        this.setVisible(true);
    }

    /*public static ComponentUI run(){
        Frame_1 frame1 = new Frame_1();
        frame1.
        //while(frame1.isVisible()){
            //System.out.println("hello");
        //}
        return frame1.CUI;
    }*/
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1){
            CUI.name = (String) cB.getSelectedItem();
            System.out.println("selected item is: " + (String) cB.getSelectedItem());
            if(!tF1.getText().isEmpty()){
                try{CUI.args[0] =  Double.parseDouble(tF1.getText());}catch(Exception error){
                    CUI.args[0] = 0.0;
                }
            }
            if(!tF2.getText().isEmpty()){
                try{ CUI.args[1] =  Double.parseDouble(tF2.getText());}catch(Exception error){
                    CUI.args[1] = 0.0;
                }
            }
            CUI.updateIcon();
            this.setVisible(false);
        }
        
        if (e.getSource()==cB){
        
            l2.setText(ComponentUI.componentsArgs.get( cB.getSelectedItem())[0]);
            l3.setText(ComponentUI.componentsArgs.get( cB.getSelectedItem())[1]);

        }
    }
    
}