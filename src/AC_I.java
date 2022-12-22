public class AC_I extends Component{
    public Type type = Type.I;
    double frequency , amplitude;
    public AC_I(Point start , Point end , double frequency , double amplitude){
        type = Type.I;
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