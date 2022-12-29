public class Resistance extends Component{
    //public Type type = Type.IV;
    Double value;
    public Resistance(Point[] points , Double args[]){
        super(points , args);
        this.type = Type.IV;
    }    
    @Override
    public double getValue(){
        return args.get(0);
    }
} 