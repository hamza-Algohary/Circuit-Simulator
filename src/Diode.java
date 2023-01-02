import java.util.Arrays;

public class Diode extends Component {
    Diode(Point[] points , Double args[]){
        this.points = Arrays.asList(points);
        this.args = Arrays.asList(args);
        //type = Type.V;
    }
    @Override
    public boolean isDelayed(){
        return true;
    }

    private double getVd(){
        return this.args.get(0);
    }

    @Override
    public double getValue(){
        if(simulator.isDelayedComputation()){
            return getVd();
        }else{
            return 0;
        }

    }
    
}
