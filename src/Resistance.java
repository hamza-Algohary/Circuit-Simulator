public class Resistance extends Component{
    //public Type type = Type.IV;
    double value;
    public Resistance(Point start , Point end , double value){
        this.type = Type.IV;
        this.start = start;
        this.end = end;
        this.value = value;
    }    
    @Override
    public double getValue(){
        return value;
    }
} 