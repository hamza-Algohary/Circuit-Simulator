/*import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Simulator {
    int currentTime_ms = 0;
    int timeStep_ms = 1;
    boolean delayedComputation = false;
    public boolean isDelayedComputation(){
        return delayedComputation;
    }
    double getCurrentTimeSecond(){
        return currentTime_ms/1000.0;
    }
    double getTimeStepSecond(){
        return timeStep_ms/1000.0;
    }
    void tick(){
        this.currentTime_ms += timeStep_ms;
    }
    public static Node[] componentsToNodes(Component[] components){
        HashMap<Point , HashMap<Component , Point>> points = new HashMap<>();
        for(Component component : components){
            if(points.containsKey(component.start)){
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
            } 
        }
        List<Node> nodesList = new LinkedList<Node>();
        points.forEach((Point point , HashMap<Component , Point> componentsMap)->{
            nodesList.add(new Node(point, componentsMap));
        });
        return nodesList.toArray(new Node[0]);
    }
    /*public static Equation[] nodesToEquations(Node[] nodes){
        List<Equation> equationsList;
        boolean repeat = false;
        do{
            for(Node node : nodes){
                Algebra.Equation equation = new Algebra.Equation();
                boolean source_exists = false;
                for(Map.Entry<Component, Point> component : node.components.entrySet()){
                    switch(component.getKey().type){
                        case I:

                            break;
                        case IV:

                            break;
                        case V:

                            break;             
                    }
                }
            }
        }while(repeat);
    }*/
    /*public static Equation[] componentsToEquations(Component[] components){

    }*//*
}
*/