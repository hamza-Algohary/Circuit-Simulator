import java.util.HashMap;

public class Node {
    boolean delayed = false;
    Point point = new Point();
    HashMap<Component , Point> components = new HashMap<>();    
    boolean isGround = false;
    
    public Node(Point point , HashMap<Component , Point> components){
        this.point = point;
        this.components = components;
    }

    public String toString(){
        return ""+this.point.x + "," + this.point.y; 
    }
    public double getSumOfConductance()throws Exception{
        double sum = 0;
        if(components == null) System.out.println("null type");
        for(Component component : components.keySet()){
            if(component.getType().equals(Component.Type.IV)){
                sum += 1/component.getValue();
            }
        }
        return sum;
    }
    public boolean hasDelayedComponent(){
        return delayed;
    }
    public Equation getEquation()throws Exception{
        Equation equation = new Equation();
        if(getSumOfConductance()!=0 && !isGround){
            equation.vars.add(new Variable("V"+toString() , getSumOfConductance()));
        }
        for(Component component : components.keySet()){
            if(component.isDelayed()) delayed = true;
            if(component.hasVar()){
                equation.vars.add(component.getVar(this.point));
            }
            if(component.hasConstant()){
                equation.constant += component.getConstant(this.point);
            }
        }
        return equation;
    }
}
