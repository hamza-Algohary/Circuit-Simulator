import org.ejml.data.DMatrixIterator;
import org.ejml.simple.SimpleMatrix;
import java.util.HashMap;
import java.util.Vector;

public class Algebra{
    public static HashMap<String ,Double> solve(String[] varNames , double[][] coeff , double [] constants) throws Exception{
        /*if(varNames.length != coeff.length){
            throw new Exception("Error: number of equations != number of variables");
        }*/
        SimpleMatrix A = new SimpleMatrix(coeff);
        SimpleMatrix B = new SimpleMatrix(constants);
        SimpleMatrix X = A.solve(B);

        DMatrixIterator element = X.iterator(true, 0, 0, X.numRows()-1, 0);

        HashMap<String , Double> varsMap = new HashMap<String , Double>();      
        int i = 0;
        while(element.hasNext()){
            varsMap.put(varNames[i],(Math.ceil(element.next()*1000000)/1000000));
            i++;
        }
        return varsMap;
    }
    public static HashMap<String , Double> solve(LinearSystem system)throws Exception{
        return solve(system.varNames , system.coeff , system.constants);
    }
    public static String[] getAllVarNamesFromEquations(final Equation equations[]){
        Vector<String> names = new Vector<>();
        for(Equation equation : equations){
            for(Variable variable : equation.vars){
                if(!names.contains(variable.name)){
                    names.add(variable.name);
                }
            }
        }
        return names.toArray(new String[0]);
    }
    public static double[] getAllConstantsFromEquations(final Equation equations[]){
        Vector<Double> constants = new Vector<>();
        for(Equation equation : equations){
            constants.add(equation.constant);
        }
        return Utils.convertDoubleArraytodouble(constants.toArray(new Double[0]));
    }

    /*public static LinearSystem equationsToLinearSystem(Equation[] equations){
        LinearSystem system = new LinearSystem();
        system.varNames = getAllVarNamesFromEquations(equations);
        system.constants = getAllConstantsFromEquations(equations);
        system.coeff = new double[equations.length][equations.length];
        for(int i=0 ; i<equations.length ; i++){
            for(Variable var : equations[i].vars){
                int j = Utils.indexOf(system.varNames, var.name);
                system.coeff[i][j] = var.coeffecient;
            }
        }
        return system;
    }*/
    /*public class Equation {
        public String varName;
        public HashMap<String , Double> vars;
        public double constant;
        public Equation() {
        }
        public Equation(String varName, HashMap<String, Double> vars, double constant) {
            this.varName = varName;
            this.vars = vars;
            this.constant = constant;
        }
    }*/
}