import java.util.Arrays;

public class Diode extends Component {
    Diode(Point[] points , Double args[]){
        this.points = Arrays.asList(points);
        this.args = Arrays.asList(args);
        //type = Type.V;
        type = Type.OTHER;
    }
    private boolean pass = false;
    @Override
    public boolean isDelayed(){
        return true;
    }

    private double getVd(){
        return this.args.get(0);
    }

    @Override
    public double getValue(){
        if(getType()==Type.I){
            return 0;
        }
        return -getVd();
    }

    @Override 
    public Type getType(){
        if(!simulator.isDelayedComputation()){
            return Type.I;
        }else{
            if(!pass){
                return Type.I;
            }else{
                return Type.V;
            }
        }
    }

    /*@Override
    public boolean hasConstant(){
        if(simulator.isDelayedComputation()){
            return true;
        }
        return !pass;
    }
    @Override
    public boolean hasVar(){
        if(simulator.isDelayedComputation()){
            return false;
        }
        return pass;
    }    
    @Override
    public boolean hasEquation(){
        if(simulator.isDelayedComputation()){
            return false;
        }
        return pass;
    }*/
    /*@Override
    public double getValue(){
        if(simulator.isDelayedComputation()){
            return getVd();
        }else{
            return 0;
        }
    }*/
    @Override
    public void setState(double I , double v1 , double v2){
        super.setState(I, v1, v2);
        if(!simulator.isDelayedComputation()){
            if(getVd()>0)
                pass =  v1-v2 > getVd();
            else
                pass = v2-v1 > getVd();
        }

    }
    /*
    @Override
    public double getConstant(Point start)throws Exception{
        if(!hasConstant()) throw new Exception("No constant");
        return 0;
    }

    @Override
    public Variable getVar(Point start)throws Exception{
        if(!hasVar()) throw new Exception("No Variable");

    }*/
    
}
