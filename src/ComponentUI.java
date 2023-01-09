import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;


import javax.swing.*;

public class ComponentUI extends JButton{
    CircularInt orientation = new CircularInt(0, 3);
    String name = new String();
    Double args[] = new Double[MAX_ARGS];

    ComponentUI(){
        updateIcon();
    }
    public void load(ComponentUI block){
        this.orientation = block.orientation;
        this.name = block.name;
        this.args = block.args;
    }
    public void updateIcon(){
        /*ImageIcon icon = new ImageIcon(getImage());
        System.out.println(getImage());
        RotatedIcon R = new RotatedIcon(icon , RotatedIcon.Rotate.ABOUT_CENTER);
        R.setCircularIcon(true);
        R.setDegrees(90 * orientation.get());
        R.paintIcon(this , this.getGraphics(), 0 , 0);*/
        ImageIcon icon = new ImageIcon(getImage());
        System.out.println(getImage());
        this.setIcon(icon);
    }
    public boolean isEmpty(){
        return !targetPoints.containsKey(name);
    }

    Component getComponent(double x , double y) throws Exception{
        /*Point nodes[] = new Point[]{
            new Point(x+1 , y+0.5),
            new Point(x , y+0.5),
            new Point(x+0.5 , y),
            new Point(x+1 , y+1)
        };
        Point wireNodes[] = new Point[]{
            new Point(x+1 , y+0.5),
            new Point(x+0.5 , y),
            new Point(x , y+0.5),
            new Point(x+1 , y+1)
        };*/
        //CircularArray<Point> nodesVector = new CircularArray<>();
        //nodesVector.setVector(new Vector<Point>(Arrays.asList(nodes)));
        //Vector<Point> targetPoints = nodesVector.getVector(orientation.get(), name==Constants.UI.W3?3:name==Constants.UI.W4?4:2);
        if(isEmpty()){
            throw new Exception("Empty Component");
        }
        Point[] targetNodes = NodeUI.getPoints(targetPoints.get(name)[orientation.get()], x, y);
        /*String target = componentsOrientataions.get(name)[orientation.get()];
        for(int c : target.chars().toArray()){
            System.out.println(Character.getNumericValue(c));
            targetPoints.add(nodes[Character.getNumericValue(c)]);
        }
        Point targetNodes[] = targetPoints.toArray(new Point[]{});*/
        /*Point targetNodes[] = new Point[]{
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(0))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(1))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(2))],
            nodes[Character.getNumericValue(componentsImages.get(name)[orientation.get()].charAt(3))],                  
        };*/ 
        if(Constants.UI.R.compareTo(name) == 0){
            return new Resistance(targetNodes, args);
        }else if(Constants.UI.DC_V.compareTo(name) == 0){
            return new DC_V(targetNodes, args);
        }else if(Constants.UI.DC_I.compareTo(name) == 0){
            return new DC_I(targetNodes, args);
        }else if(Constants.UI.AC_V.compareTo(name) == 0){
            return new Resistance(targetNodes, args);
        }else if(Constants.UI.AC_I.compareTo(name) == 0){
            return new Resistance(targetNodes, args);
        }else if(Constants.UI.C.compareTo(name) == 0){
            return new Capacitor(targetNodes, args);
        }else if(Constants.UI.W2.compareTo(name) == 0 || Constants.UI.W21.compareTo(name) == 0){
            return new DC_V(targetNodes, args);
        }else if(Constants.UI.W3.compareTo(name) == 0 || Constants.UI.W4.compareTo(name) == 0){
            return new DC_V(targetNodes, args);
        }else{
            throw new Exception("Unknown Component\n");
            //return new Resistance(targetNodes, args);
            //System.out.println("Unknown Component");
        }
        /* 
        switch(name){
            case Constants.UI.R:
                return new Resistance(targetNodes, args);
            case Constants.UI.DC_V:
                return new DC_V(targetNodes, args);
            case Constants.UI.DC_I:
                return new DC_I(targetNodes, args);
            case Constants.UI.AC_I:
                return new AC_I(targetNodes, args);
            case Constants.UI.AC_V:
                return new AC_V(targetNodes, args);
            case Constants.UI.C:
                return new Capacitor(targetNodes, args);
            case Constants.UI.W2:
            case Constants.UI.W21:
                return new DC_V(targetNodes, new Double[]{0.0});
            case Constants.UI.W3:
            case Constants.UI.W4:
                return new Wire(targetNodes , new Double[]{0.0});
            default:
                return new DC_V(targetNodes , args);
        }*/

    }
    public String getImage(){
        return "images/" + String.valueOf(orientation.get()) + "/" + name + ".jpg";
    }
    public void rotate(){
        orientation.inc();
        updateIcon();
    }

    static HashMap<String , String[]> componentsArgs = new HashMap<>();
    static final int MAX_ARGS = 2;
    static{
        componentsArgs.put(Constants.UI.R, new String[]{"Ohms" , ""});
        componentsArgs.put(Constants.UI.DC_V, new String[]{"Volts" , ""});
        componentsArgs.put(Constants.UI.DC_I, new String[]{"Amperes" , ""});
        componentsArgs.put(Constants.UI.AC_V, new String[]{"Hertz" , "Maximum V"});
        componentsArgs.put(Constants.UI.AC_I, new String[]{"Hertz" , "Maximum I"});
        componentsArgs.put(Constants.UI.C, new String[]{"Farads" , ""});
        componentsArgs.put(Constants.UI.W2, new String[]{"",""});
        /*componentsArgs.put(Constants.UI.W21, new String[]{"",""});
        componentsArgs.put(Constants.UI.W3, new String[]{"",""});
        componentsArgs.put(Constants.UI.W4, new String[]{"",""});*/
    }

    static HashMap<String , int[][]> targetPoints = new HashMap<>();
    static{
        targetPoints.put(Constants.UI.R , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        targetPoints.put(Constants.UI.DC_V , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        targetPoints.put(Constants.UI.DC_I , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        targetPoints.put(Constants.UI.AC_V , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        targetPoints.put(Constants.UI.AC_I , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        targetPoints.put(Constants.UI.C , new int[][]{{2,0},{3,1},{0,2},{1,3}});  
        targetPoints.put(Constants.UI.W2 , new int[][]{{2,0},{3,1},{0,2},{1,3}});
        /* 
        targetPoints.put(Constants.UI.W21 , new int[][]{{0,1},{1,2},{2,3},{3,0}});
        targetPoints.put(Constants.UI.W3 , new int[][]{{3,0,1},{0,1 , 2},{1,2,3},{2,3,0}});
        targetPoints.put(Constants.UI.W4 , new int[][]{{0,1,2,3},{0,1,2,3},{0,1,2,3},{0,1,2,3}});
        */

    }

    /*static HashMap<String , String[]> componentsOrientataions = new HashMap<>();
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
    }*/
    /*@Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this){
            ComponentUI block = Frame_1.run();
            this.load(block);
        }
    }*/
}