public class DC_I extends Component{
    double value;
    public DC_I(Point start , Point end , double value){
        type = Type.I;
        this.start = start;
        this.end = end;
        this.value = value;      
    }
    @Override
    public double getValue(){
        return value;
    }
}