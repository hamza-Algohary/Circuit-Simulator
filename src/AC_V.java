public class AC_V extends Component{
    double frequency , amplitude;
    Double getFrequency(){return args.get(0);}
    Double getAmplitude(){return args.get(1);}
    public AC_V(Point[] points , Double args[]){
        super(points, args);
        this.type = Type.V;
    }
    @Override
    public double getValue(){
        //Asin(2PI*F*t)
        return getAmplitude()*Math.sin(2*Math.PI*getFrequency()*simulator.getCurrentTimeSecond());
    }
}