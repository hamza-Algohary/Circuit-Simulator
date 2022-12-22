public class Component {
    public Component(Point start , Point end /*, double args[]*/){
        this.start = start;
        this.end = end;
        //this.args = args;
    }
    enum Type{IV , V ,I}
    Type type;
    boolean isGround = false;
    public Component(){}
    public Point start = new Point();
    public Point end = new Point();
    //double args[];
    //public boolean delayed = false;
    public boolean isDelayed(){
        return false;
    }
    CircuitSimulator simulator;
    boolean hasVar(){
        return type.equals(Type.IV) || type.equals(Type.V);
    }
    boolean hasEquation(){
        return type.equals(Type.IV) || type.equals(Type.V);
    }
    boolean hasConstant(){
        return type.equals(Type.I);
    }
    public String getBranchName(){
        return start.toString() + ":" + end.toString();
    }
    public double getValue() throws Exception{
        throw new Exception("Empty Component");
    }
    public void setState(double I , double v1 , double v2){

    }
    public double getConstant(Point currentNode)throws Exception{
        if(!type.equals(Type.I))
            throw new Exception("Non-I components don't have constants");
        if(currentNode.equals(end)){
            return getValue();
        }else{
            return getValue()*-1;
        }
    }
    public Variable getVar(Point currentNode)throws Exception{
        /*if(type == Type.I)
            throw new Exception("Invalid Operation on non-IV component.");*/
        String name = "";
        double value = 0;
        if(type == Type.IV){
            value = -1/getValue();
            if(currentNode.equals(start)){
                name = getVendName();
            }else{
                name = getVstartName();
            }
        }else if(type == Type.V){
            name = getIName();
            if(currentNode.equals(start)){
                value = /*getValue()*/1;
            }else{
                value = -1;//getValue();
            }
        }
        
        return new Variable(name,value);    
    }
    public Equation getEquation()throws Exception{
        Equation equation = new Equation();
        switch(type){
            case IV:
                equation.constant = 0;
                equation.vars.add(new Variable(getIName(), -1));
                equation.vars.add(new Variable(getVendName(), -1.0/getValue()));
                equation.vars.add(new Variable(getVstartName(), 1.0/getValue()));                   
                break;
            case I:
                throw new Exception("I components don't have equations");
            case V:
                equation.constant = getValue();
                equation.vars.add(new Variable(getVendName(), 1));
                equation.vars.add(new Variable(getVstartName(), -1));
                break;
        }
        return equation;
    }
    public String getIName(){
        return "I"+getBranchName();
    }
    public String getVstartName(){
        return "V"+start;
    }
    public String getVendName(){
        return "V"+end;
    }
}
