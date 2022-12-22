public class Variable{
    public Variable(String name , double coeffecient){
        this.name = name;
        this.coeffecient = coeffecient;
    }
    String name = new String();
    double coeffecient;
    public String toString(){
        return "" + coeffecient + name;
    }
    //@Override 
}
