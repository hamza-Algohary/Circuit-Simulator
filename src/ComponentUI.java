import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentUI extends JButton implements ActionListener{
    CircularInt orientation = new CircularInt(0, 3);
    String name = new String();
    Double args[] = new Double[MAX_ARGS];

    ComponentUI(){
        updateIcon();
    }
    private void load(ComponentUI block){
        this.orientation = block.orientation;
        this.name = block.name;
        this.args = block.args;
    }
    private void updateIcon(){
        ImageIcon icon = new ImageIcon(getImage());
        RotatedIcon R = new RotatedIcon(icon , RotatedIcon.Rotate.ABOUT_CENTER);
        R.setCircularIcon(true);
        R.setDegrees(90 * orientation.get());
        this.setIcon(R.getIcon());
    }

    Component getComponent(double x , double y){
        Point nodes[] = new Point[]{
            new Point(x+1 , y+0.5),
            new Point(x+0.5 , y),
            new Point(x , y+0.5),
            new Point(x+0.5 , y+1)
        };
        Vector<Point> targetPoints = new Vector<>();
        String target = componentsOrientataions.get(name)[orientation.get()];
        for(int c : target.chars().toArray()){
            targetPoints.add(nodes[Character.getNumericValue((char)c)]);
        }
        Point targetNodes[] = targetPoints.toArray(new Point[]{});
        /*Point targetNodes[] = new Point[]{
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(0))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(1))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(2))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(3))],                  
        };*/
        switch(name){
            case Constants.R:
                return new Resistance(targetNodes, args);
            case Constants.DC_V:
                return new DC_V(targetNodes, args);
            case Constants.DC_I:
                return new DC_I(targetNodes, args);
            case Constants.AC_I:
                return new AC_I(targetNodes, args);
            case Constants.AC_V:
                return new AC_V(targetNodes, args);
            case Constants.C:
                return new Capacitor(targetNodes, args);
            case Constants.W2:
                return new DC_V(targetNodes, new Double[]{0.0});
            case Constants.W3:
            case Constants.W4:
                return new Wire(targetNodes , new Double[]{0.0});
            default:
                return new DC_V(targetNodes , args);
        }
    }
    String getImage(){
        return name;
    }
    void rotate(){
        orientation.inc();
    }

    static HashMap<String , String[]> componentsArgs = new HashMap<>();
    static final int MAX_ARGS = 2;
    static{
        componentsArgs.put(Constants.R, new String[]{"Ohms" , ""});
        componentsArgs.put(Constants.DC_V, new String[]{"Volts" , ""});
        componentsArgs.put(Constants.DC_I, new String[]{"Amperes" , ""});
        componentsArgs.put(Constants.AC_V, new String[]{"Hertz" , "Maximum V"});
        componentsArgs.put(Constants.AC_I, new String[]{"Hertz" , "Maximum I"});
        componentsArgs.put(Constants.C, new String[]{"Farads" , ""});
        componentsArgs.put(Constants.W2, new String[]{"",""});
        componentsArgs.put(Constants.W3, new String[]{"",""});
        componentsArgs.put(Constants.W4, new String[]{"",""});
    }

    static HashMap<String , String[]> componentsOrientataions = new HashMap<>();
    static{
        try{
            File orientations = new File("../src/components_images.txt");
            Scanner in = new Scanner(orientations);
            while(in.hasNextLine()){
                String name = in.nextLine();
                String ors[] = in.nextLine().split(" ", 4);
                componentsOrientataions.put(name, ors);
            }
            in.close();
        }catch(Exception e){
            System.out.println(e);
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            ComponentUI block = Frame_1.run();
            this.load(block);
        }
    }
}