import java.util.HashMap;

public class Main{
    public static void main(String args[]) throws Exception{
        /*double Aarr[][] = {
            {1 ,0 ,0, 0, 0, 0},
            {-1, 1, 0, 0, 0, 0},
            {0 ,-0.2 ,0.7, -0.4, 0, -0.1},
            {0 ,0 , -0.4 , 0.8, -0.4, 0},
            {0 ,0, 0, -0.4 ,0.6, -0.2},
            {-0.2 ,0 ,-0.1, 0, -0.2, 0.5},
        };
        double Barr[] = {0, 30, 0, 0, 0, 0};
        HashMap<String , Double> vars = new HashMap<>();
        String varNames[] = {"v0","v1","v2","v3","v4","v5"};
        try{
            vars = Algebra.solve(varNames , Aarr, Barr);
        }catch(Exception e){
            System.out.println(e.toString());
        }
        vars.forEach((String name , Double value)->{
            System.out.println(name + " = " + value);
        });*/
        /*Point p1 = new Point(1 , 1);
        Point p2 = new Point(1 , 1);
        System.out.println(p1.equals(p2));
        HashMap<Point, String> pMap = new HashMap<>();
        pMap.put(p1, "hello");
        System.out.println(pMap.containsKey(p1));
        */
        Component[] components = {
            new DC_V(new Point(0 , 0) , new Point(0 , 1) , 15),
            new Resistance(new Point(0 , 1) , new Point(1 , 1) , 5),
            new Resistance(new Point(0 , 0) , new Point(1 , 0) , 5),
            new Resistance(new Point(1 , 0) , new Point(1 , 1) , 5)
        };
        /*Component[] components = {
            new DC_V(new Point(0 , 0) , new Point(1 , 0) , 0),
            new Resistance(new Point(1 , 0) , new Point(2 , 0) , 12000),
            new DC_V(new Point(2 , 0) , new Point(3 , 0) , 0),

            new Resistance(new Point(0 , 0), new Point(0 , 1), 4000),
            new Resistance(new Point(1 , 0), new Point(1 , 1), 6000),
            new Resistance(new Point(2 , 0), new Point(2 , 1), 18000),
            new Resistance(new Point(3 , 0), new Point(3 , 1), 6000),

            new DC_V(new Point(1 , 1), new Point(2 , 1), 0),

            new DC_V(new Point(0 , 1), new Point(0 , 2), 0),
            new DC_V(new Point(2 , 1), new Point(2 , 2), -42),
            new DC_V(new Point(3 , 1), new Point(3 , 2), 0),

            new DC_V(new Point(0 , 2), new Point(1 , 2), 0),
            new DC_V(new Point(1 , 2), new Point(2 , 2), 0),
            new DC_V(new Point(2 , 2), new Point(3 , 2), 0),     
        };*/

        CircuitSimulator simulator = new CircuitSimulator(components);
        HashMap<String , Double> varNames = new HashMap<>();
        try{
            varNames = simulator.solve();      
        }catch(Exception e){
            throw e;
        }
        varNames.forEach((String name , Double value)->{
            System.out.println(name + " = " + value);
        });
        System.out.println(varNames.get("I0,0:1,0"));
        /*System.out.println(components[0].getEquation());
        System.out.println(components[1].getEquation());
        System.out.println(components[2].getEquation());
        System.out.println(components[3].getEquation());
        System.out.println(simulator.nodes.length);
        System.out.println(simulator.nodes[0]);
        System.out.println(simulator.nodes[1]);
        System.out.println(simulator.nodes[2]);
        System.out.println(simulator.nodes[3]);
        System.out.println(simulator.nodes[0].getEquation());
        varNames.forEach((String name, Double value)->{
            System.out.println(name + " = " + value);
        });*/
        
    }
}