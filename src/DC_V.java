public class DC_V extends Component{
    //Double value;
    public DC_V(Point[] points , Double args[]){
        super(points , args);
        type = Type.V;    
    }
    @Override
    public double getValue(){
        try{
            return args.get(0);
        }catch(Exception e){
            return 0;
        }
        
    }
} 