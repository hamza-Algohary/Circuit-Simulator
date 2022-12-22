public class DC_V extends Component{
    double value;
    public DC_V(Point start , Point end , double value){
        type = Type.V;
        this.start = start;
        this.end = end;
        this.value = value;     
    }
    @Override
    public double getValue(){
        return value;
    }
} 