import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CircuitSimulator{
    public Component components[] = new Component[0];
    public Node nodes[] = new Node[0];
    double currentTime_ms = 0;
    double timeStep_ms = 0.005;
    boolean delayedComputation = false;
    public boolean isDelayedComputation(){return delayedComputation;}
    public boolean hasDelayedComponents(){
        for(Component c : components){if(c.isDelayed()) return true;}
        for(Node n : nodes){if(n.hasDelayedComponent()) return true;}
        return false;
    }
    double getCurrentTimeSecond(){return currentTime_ms/1000.0;}
    double getTimeStepSecond(){return timeStep_ms/1000.0; }
    public HashMap<String , Double> tick()throws Exception{
        this.currentTime_ms += timeStep_ms;
        return solve();
    }
    public CircuitSimulator(Component components[]){
        //convertWiresToNodes(components);
        this.components = components;
        for(Component component : this.components){
            component.simulator = this;
        }
        this.nodes = componentsToNodes(components);
    }
    //public CircuitSimulator(){}

    /*private void convertWiresToNodes(Component components[])throws Exception{
        for(Component component : components){
            if(component.getType() == Component.Type.W && component.getValue()<0.00000001){
                for(int i=1 ;i<component.points.size();i++){
                    renameNodeInComponents(components, component.points.get(0), component.points.get(i));
                }
                /*boolean first = true;
                Point p1 = component.points.get(0);
                for(Point point : component.points){
                    if(first){
                        first = false;
                        continue;
                    }
                    renameNodeInComponents(components, component.points.get(0), point);                    
                }*/
    /*        }
        }
    }*/
    /*private void renameNodeInComponents(Component components[] , Point toBeRenamed , Point newName){
        for(Component component : components){
            for(Point point : component.points){
                //System.out.println(point.x);
                //System.out.println(point.y);
                //System.out.println(toBeRenamed.x);
                //System.out.println(point.x == toBeRenamed.x);
                if(point.x == toBeRenamed.x && point.y == toBeRenamed.y){
                    point.x = newName.x;
                    point.y = newName.y;
                    point = new Point(toBeRenamed);
                }
            }
        }
    }*/
    public void selectRandomGround(){
        for(Component c : components){
            if( c.getType() == Component.Type.V ){
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
            if(component.hasEquation())
                equationsVector.addAll(new Vector<Equation>(Arrays.asList(component.getEquations())));
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
                /*component.setState(
                    varNames.get(component.getIName()), 
                    varNames.get(component.getVstartName()), 
                    varNames.get(component.getVendName()));*/
                component.setState(Utils.getKey(varNames, component.getIName(), 0.0),
                                   Utils.getKey(varNames, component.getVstartName(), 0.0),
                                   Utils.getKey(varNames, component.getVendName(), 0.0));
            }
            delayedComputation = true;
        }
        delayedComputation = false;
        return varNames;
    }


    /*---------------------------------------------------------------------- */
    /*---------------------------------------------------------------------- */
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */ 
    //       This method needs to be changed to support multiple nodes
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */
    /*---------------------!!!!!!!ATTEMTION!!!!!!!!------------------------- */
    /*---------------------------------------------------------------------- */
    /*---------------------------------------------------------------------- */

    public static Node[] componentsToNodes(Component components[]){
        HashMap<Point , HashMap<Component , Point>> points = new HashMap<>();
        for(Component component : components){
            Utils.getKey(Utils.getKey(points, component.getStart() , new HashMap<>()) , component , new Point()).assign(component.getEnd());
            Utils.getKey(Utils.getKey(points, component.getEnd() , new HashMap<>()) , component , new Point()).assign(component.getStart());
        }
        List<Node> nodesList = new LinkedList<Node>();
        points.forEach((Point point , HashMap<Component , Point> componentsMap)->{
            nodesList.add(new Node(point, componentsMap));
        });
        return nodesList.toArray(new Node[0]);        
    }
}
