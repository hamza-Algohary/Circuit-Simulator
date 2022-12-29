public class Capacitor extends Component{
    Double q = 0.0;
    Double getCapacitance(){
        return args.get(0);
    }
    public Capacitor(Point[] points , Double args[]){
        super(points, args);
        type = Type.V;
    }
    @Override
    public double getValue(){
        // V = Q/C
        return q/getCapacitance();
    }
    @Override
    public void setState(double I , double v1 , double v2){
        if(!simulator.isDelayedComputation())
            this.q += I*simulator.getTimeStepSecond();
    }
}