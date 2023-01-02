public class Wire extends Component {
    public Wire(Point[] points , Double args[]){
        super(points , args);
        type = Type.W;    
    }
    @Override
    public double getValue(){
        return 0;
    }
    @Override
    public Equation[] getEquations()throws Exception{
        Equation equations[] = new Equation[points.size()];
        for(int i=1 ; i<points.size() ; i++){
            Equation equation = new Equation();
            equation.constant = getValue();
            equation.vars.add(new Variable("V"+points.get(0), 1));
            equation.vars.add(new Variable("V"+points.get(i), -1));
            equations[i-1] = equation;
        }
        return equations;
    }
    @Override
    public boolean hasVar(){
        return false;
    }
    public boolean getVar()throws Exception{
        throw new Exception("No single I in this component");
    }
    
}
