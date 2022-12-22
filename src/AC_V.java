public class AC_V extends Component{
    double frequency , amplitude;
    public AC_V(Point start , Point end , double frequency , double amplitude){
        this.type = Type.V;
        this.start = start;
        this.end = end;
        this.frequency = frequency;
        this.amplitude = amplitude; 
    }
    @Override
    public double getValue(){
        //Asin(2PI*F*t)
        return amplitude*Math.sin(2*Math.PI*frequency*simulator.getCurrentTimeSecond());
    }
}