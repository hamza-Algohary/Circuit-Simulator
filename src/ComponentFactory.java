public class ComponentFactory {
    public static Component construct(String name , Point[] targetNodes , Double args[]) throws Exception {
        if(Constants.R.compareTo(name) == 0){
            return new Resistance(targetNodes, args);
        }else if(Constants.DC_V.compareTo(name) == 0){
            return new DC_V(targetNodes, args);
        }else if(Constants.DC_I.compareTo(name) == 0){
            return new DC_I(targetNodes, args);
        }else if(Constants.AC_V.compareTo(name) == 0){
            return new AC_V(targetNodes, args);
        }else if(Constants.AC_I.compareTo(name) == 0){
            return new AC_I(targetNodes, args);
        }else if(Constants.C.compareTo(name) == 0){
            return new Capacitor(targetNodes, args);
        }else if(Constants.D.compareTo(name) == 0){
            return new Diode(targetNodes , args);
        }else if(Constants.W.compareTo(name) == 0){
            return new DC_V(targetNodes, new Double[]{0.0});
        }else{
            throw new Exception("Unknown Component\n");
        }
    }
}
