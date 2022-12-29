public class DC_I extends Component{
    public DC_I(Point[] points , Double args[]){
        super(points , args);
        type = Type.I;
    }
    @Override
    public double getValue(){
        return args.get(0);
    }
}