import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CircuitSimulator{
    public Component components[] = new Component[0];
    public Node nodes[] = new Node[0];
    int currentTime_ms = 0;
    int timeStep_ms = 1;
    boolean delayedComputation = false;
    public boolean isDelayedComputation(){return delayedComputation;}
    public boolean hasDelayedComponents(){
        for(Component c : components){if(c.isDelayed()) return true;}
        for(Node n : nodes){if(n.hasDelayedComponent()) return true;}
        return false;
    }
    double getCurrentTimeSecond(){return currentTime_ms/1000.0;}
    double getTimeStepSecond(){return timeStep_ms/1000.0; }
    void tick(){
        this.currentTime_ms += timeStep_ms;
    }
    public CircuitSimulator(Component components[]){
        this.components = components;
        this.nodes = componentsToNodes(components);
    }
    public void selectRandomGround(){
        for(Component c : components){
            if( c.type == Component.Type.V ){
                c.isGround = true;
                break;
            }
        }
    }
    public Equation[] getEquations()throws Exception{
        Vector<Equation> equationsVector = new Vector<>();
        nodes[0].isGround = true;
        //selectRandomGround();
        for(Node node : nodes){
            equationsVector.add(node.getEquation());
        }
        for(Component component : components){
            equationsVector.add(component.getEquation());
        }
        Equation groundEquation = new Equation();
        groundEquation.constant = 0;
        groundEquation.vars.add(new Variable("V"+nodes[0].point.toString(), 1));
        //equationsVector.add(groundEquation);
        //equationsVector.get(0).vars.get(0).coeffecient = 0;
        return equationsVector.toArray(new Equation[0]);    
    }
    public HashMap<String , Double> solve()throws Exception{
        int n=1;
        if(hasDelayedComponents()) n=2; 
        HashMap<String , Double> varNames = new HashMap<>();
        for(int i=0;i<n;i++){
            LinearSystem system = new LinearSystem(getEquations());
            varNames = system.solve();
            for(Component component : components){
                component.setState(varNames.get(component.getIName()), varNames.get(component.getVstartName()), varNames.get(component.getVendName()));
            }
            delayedComputation = true;
        }
        delayedComputation = false;
        return varNames;
    }

    public static Node[] componentsToNodes(Component components[]){
        HashMap<Point , HashMap<Component , Point>> points = new HashMap<>();
        for(Component component : components){
            Utils.getKey(Utils.getKey(points, component.start , new HashMap<>()) , component , new Point()).assign(component.end);
            Utils.getKey(Utils.getKey(points, component.end , new HashMap<>()) , component , new Point()).assign(component.start);
            /*if(points.containsKey(component.start)){
                points.get(component.start).put(component , component.end);
            }else{
                points.put(component.start, new HashMap<>());
                points.get(component.start).put(component, component.end);
            }
            if(points.containsKey(component.end)){
                points.get(component.end).put(component , component.start);
            }else{
                points.put(component.end, new HashMap<>());
                points.get(component.end).put(component, component.start);
            } */
        }
        List<Node> nodesList = new LinkedList<Node>();
        points.forEach((Point point , HashMap<Component , Point> componentsMap)->{
            nodesList.add(new Node(point, componentsMap));
        });
        return nodesList.toArray(new Node[0]);        
    }
}
