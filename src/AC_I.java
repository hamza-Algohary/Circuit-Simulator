public class AC_I extends Component{
    public Type type = Type.I;
    Double getFrequency(){return args.get(0);}
    Double getAmplitude(){return args.get(1);}
    public AC_I(Point[] points , Double args[]){
        super(points, args);
        type = Type.I;
    }
    @Override
    public double getValue(){
        //Asin(2PI*F*t)
        return getAmplitude()*Math.sin(2*Math.PI*getFrequency()*simulator.getCurrentTimeSecond());
    }
}