import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Component {
    public Component(Point[] points , Double args[]){
        //setStart(points[0]);
        //setEnd(points[1]);
        this.args = Arrays.asList(args);
        this.points = Arrays.asList(points);
    }
    private double stateI = 0 , stateV = 0;
    public double getI(){
        return stateI;
    }
    public double getV(){
        return stateV;
    }
    // returns true if they weren't initially sorted, false otherwise.
    /*protected boolean sortStartAndEnd(){
        if(getStart().x > getEnd().x || getStart().y > getEnd().y){
            Utils.swap(points.get(0) , points.get(1));
            return true;
        }
        return false;
    }*/
    protected boolean isSorted(){
        if(getStart().x > getEnd().x || getStart().y > getEnd().y){
            return false;
        }
        return true;       
    }
    public String imageName = "";
    enum Type{IV , V ,I , OTHER}
    Type type;
    boolean isGround = false;
    public Component(){}
    public Point getStart(){return points.get(0);}
    public Point getEnd(){return points.get(1);}    
    //public void setStart(Point p){points.set(0 , p);}
    //public void setEnd(Point p){points.set(1 , p);}
    //public Point start = new Point();
    //public Point end = new Point();
    public Type getType(){
        return type;
    }
    public List<Point> points = new LinkedList<>(); //Arrays.asList(new Point[10]);
    public List<Double> args = new LinkedList<>(); //Arrays.asList(new Double[10]);
    /*static Component constructComponent(String name , double args[]){
        switch(name){
            case Constants.R:
                return new Resistance(new Point(0,0) , new Point(0,0) , args[0]);
            case Constants.DC_I:
                return new DC_I(new Point(0,0) , new Point(0,0) , args[0]);
            case Constants.DC_V:
                return new DC_V(new Point(0,0) , new Point(0,0) , args[0]);
            case Constants.AC_I:
                return new AC_I(new Point(0,0) , new Point(0,0) , args[0] , args[1]);
            case Constants.AC_V:
                return new AC_V(new Point(0,0) , new Point(0,0) , args[0] , args[1]);
            }
    }*/
    //double args[];
    //public boolean delayed = false;
    public boolean isDelayed(){
        return false;
    }
    CircuitSimulator simulator;
    boolean hasVar(){
        return getType().equals(Type.IV) || getType().equals(Type.V);
    }
    boolean hasEquation(){
        return getType().equals(Type.IV) || getType().equals(Type.V);
    }
    boolean hasConstant(){
        return getType().equals(Type.I);
    }
    /*public String getBranchName(){
        return getStart().toString() + ":" + getEnd().toString();
    }*/
    public double getValue() throws Exception{
        throw new Exception("Empty Component");
    }
    public void setState(double I , double v1 , double v2){
        stateI = I;
        stateV = v1 - v2;
    }
    public double getConstant(Point currentNode)throws Exception{
        if(!getType().equals(Type.I))
            throw new Exception("Non-I components don't have constants");
        if(currentNode.equals(getEnd())){
            return getValue();
        }else{
            return getValue()*-1;
        }
    }
    public Variable getVar(Point currentNode)throws Exception{
        if(getType() == Type.I)
            throw new Exception("Invalid Operation on non-IV component.");
        String name = "";
        double value = 0;
        if(getType() == Type.IV){
            value = -1/getValue();
            if(currentNode.equals(getStart())){
                name = getVendName();
            }else{
                name = getVstartName();
            }
        }else if(getType() == Type.V){
            name = getIName();
            if(currentNode.equals(getStart())){
                value = /*getValue()*/1;
            }else{
                value = -1;//getValue();
            }
        }
        
        return new Variable(name,value);    
    }
    public Equation[] getEquations()throws Exception{
        Equation equation = new Equation();
        switch(getType()){
            case IV:
                equation.constant = 0;
                equation.vars.add(new Variable(getIName(), isSorted()?-1:1));
                equation.vars.add(new Variable(getVendName(), -1.0/getValue()));
                equation.vars.add(new Variable(getVstartName(), 1.0/getValue()));                   
                break;
            case I:
                throw new Exception("I components don't have equations");
            case V:
                equation.constant = getValue();
                equation.vars.add(new Variable(getVendName(), 1));
                equation.vars.add(new Variable(getVstartName(), -1));
                break;
        }
        Equation equations[] = new Equation[]{equation};
        return equations;
    }
    public final String getIName(){
        //return "I"+getBranchName();
        if(isSorted()){
            return "I"+getStart()+":"+getEnd();
        }
        return "I"+getEnd()+":"+getStart();
    }
    public final String getVstartName(){
        return "V"+getStart();
    }
    public final String getVendName(){
        return "V"+getEnd();
    }
}
