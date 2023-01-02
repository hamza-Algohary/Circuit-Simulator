import java.util.HashMap;

public class LinearSystem{
    String varNames[] = new String[0];
    double coeff[][] = new double[0][0];
    double constants[] = new double[0];
    boolean isSolvable(){
        return (varNames.length == coeff.length) && (coeff.length == constants.length);
    }
    public HashMap<String , Double> solve()throws Exception{
        //if(!isSolvable()) throw new Exception("Unsolvable system");
        return Algebra.solve(varNames, coeff, constants);
    }
    public LinearSystem(){
        
    }
    public LinearSystem(Equation[] equations)throws Exception{
        this.varNames = Algebra.getAllVarNamesFromEquations(equations);
        this.constants = Algebra.getAllConstantsFromEquations(equations);
        this.coeff = new double[equations.length][equations.length];
        //if(equations.length < varNames.length){
            /*for(Equation e: equations){
                System.out.println(e.toString());
            }*/

            /*for(String var : varNames){
                System.out.println(var);
            }*/
            /*System.out.println("-----------------");
            System.out.println(equations.length);
            System.out.println(varNames.length);
            System.out.println(constants.length);
            System.out.println(coeff.length);*/
            //throw new Exception("equations.length < varNames.length");
        //}
        for(Equation equation : equations){
            //System.out.println(equation);
        }
        for(int i=0 ; i<equations.length ; i++){
            for(Variable var : equations[i].vars){
                int j = Utils.indexOf(this.varNames, var.name);
                this.coeff[i][j] = var.coeffecient;
                //System.out.println("" + i + "" + j);
            }
        }
    }
}
