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
        //System.out.println("Q = "+ q + " V = " + -q/getCapacitance());
        return -q/getCapacitance();
    }
    @Override
    public void setState(double I , double v1 , double v2){
        super.setState(I, v1, v2);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!        I = "+I+"      !!!!!!!!!!!!!!!!");
        if(!simulator.isDelayedComputation())
            this.q += I*simulator.getTimeStepSecond();
    }
}