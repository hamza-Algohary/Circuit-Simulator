public class DC_V extends Component{
    //Double value;
    public DC_V(Point[] points , Double args[]){
        super(points , args);
        type = Type.V;    
    }
    @Override
    public double getValue(){
        return args.get(0);
    }
} 