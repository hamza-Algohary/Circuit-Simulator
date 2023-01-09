import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception{
        Vector<Component> components = new Vector<>();
        Vector<Component> monitoredVComponents = new Vector<>();
        Vector<Component> monitoredIComponents = new Vector<>();
        int sample_frequency = 100;
        double sleep_ms = 0.0010;
        if(args.length > 1){
            sample_frequency = Integer.parseInt(args[1]);
        }
        if(args.length > 2){
            sleep_ms = Integer.parseInt(args[2]);
        }
        try{
            File input = new File(args[0]);
            Scanner in = new Scanner(input);
            while(in.hasNextLine()){
                boolean monitorV = false , monitorI = false;
                String name = in.next();
                var start = new Point(in.next());
                var end = new Point(in.next());
                var argumentsStrings = new Vector<>(Arrays.asList(in.nextLine().trim().split("\\ ")));
                for(var v : argumentsStrings){System.out.println(v);}
                if(argumentsStrings.get(argumentsStrings.size()-1).compareTo("mv") == 0){
                    monitorV = true;
                    argumentsStrings = Utils.shortenVector(argumentsStrings, argumentsStrings.size()-1);
                }else if(argumentsStrings.get(argumentsStrings.size()-1).compareTo("mi") == 0){
                    monitorI = true;
                    argumentsStrings = Utils.shortenVector(argumentsStrings, argumentsStrings.size()-1);     
                }
                var component = ComponentFactory.construct(name, new Point[]{start , end}, Utils.stringsToDoubles(argumentsStrings.toArray(new String[0])));
                components.add(component);
                if(monitorV){
                    monitoredVComponents.add(component);
                }else if(monitorI){
                    monitoredIComponents.add(component);
                }
            }
            in.close();
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
            System.exit(1);
        }
        I_Chart2D vo[] = new I_Chart2D[monitoredVComponents.size()];
        I_Chart2D io[] = new I_Chart2D[monitoredIComponents.size()];

        for(int i=0 ; i<monitoredIComponents.size() ; i++){
            io[i] = new I_Chart2D();
        }
        for(int i=0 ; i<monitoredVComponents.size() ; i++){
            vo[i] = new I_Chart2D();
        }
        CircuitSimulator simulator = new CircuitSimulator(components.toArray(new Component[0]));
        File output = new File("result.txt");
        output.createNewFile();
        FileWriter writer = new FileWriter(output);
        var vars = simulator.tick();
        for(var v : vars.entrySet()){
            writer.append((v.getKey() + " = " + v.getValue())+"\n");
        } 
        writer.close(); 
        long counter = 0;      
        do{
            simulator.tick();
            if(counter%sample_frequency==0){
                for(int i=0 ; i<monitoredIComponents.size() ; i++){
                    io[i].addPoint(simulator.currentTime_ms, monitoredIComponents.get(i).getI());
                }
                for(int i=0 ; i<monitoredVComponents.size() ; i++){
                    vo[i].addPoint(simulator.currentTime_ms, monitoredVComponents.get(i).getV());
                }
                //System.out.println(simulator.currentTime_ms);
            }
            TimeUnit.MICROSECONDS.sleep((int)(sleep_ms*1000));
            counter++;
        }while(!monitoredVComponents.isEmpty() || !monitoredIComponents.isEmpty());
        
    }
}
