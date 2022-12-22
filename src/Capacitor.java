public class Capacitor extends Component{
    double q = 0;
    double capacitance;
    public Capacitor(Point start , Point end , double capacitance){
        type = Type.V;
        this.start = start;
        this.end = end;
        this.capacitance = capacitance;
    }
    @Override
    public double getValue(){
        // V = Q/C
        return q/capacitance;
    }
    @Override
    public void setState(double I , double v1 , double v2){
        if(!simulator.isDelayedComputation())
            this.q += I*simulator.getTimeStepSecond();
    }
}