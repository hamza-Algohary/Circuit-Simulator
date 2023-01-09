import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Utils {
    public static double[] convertDoubleArraytodouble(Double array[]){
        double result[] = new double[array.length];
        for(int i = 0;i<array.length;i++){
            result[i] = array[i];
        }
        return result;
    }
    public static <T> int indexOf(T[] array ,  T element){
        for(int i=0 ; i<array.length ; i++){
            if(array[i].equals(element)){
                return i;
            }
        }
        return -1;
    }
    public static <K , V> V getKey(HashMap<K , V> map , K targetKey , V defaultValue){
        for(K key : map.keySet()){
            if(key.equals(targetKey)){
                return map.get(key);
            }
        }
        map.put(targetKey , defaultValue);
        return map.get(targetKey);
    }
    public static boolean compareDoubles(double x , double y , int precision){
        return (int)x*precision ==  (int)y*precision;
    }
    public static <T> void swap(T x , T y){
        T tmp = x;
        x = y;
        y = tmp;
    }
    public static <T> Vector<T> shortenVector(Vector<T> vect , int size){
        Vector<T> vector = new Vector<>();
        for(int i=0 ; i<vect.size() && i<size; i++){
            vector.add(vect.get(i));
        }
        return vector;
    }

    /*public static <T> T[] shortenArray(T[] arr , int size){
        return (T[]) shortenVector(new Vector<T>(Arrays.asList(arr)), size).toArray();
    }*/
    public static Double[] stringsToDoubles(String args[]){
        Double result[] = new Double[args.length];
        for(int i=0;i<args.length;i++){
            result[i] = Double.parseDouble(args[i]);
        }
        return result;
    }
    /*public static String[] split(String str , char delim){
        String[] arr = str.
    }*/
}
