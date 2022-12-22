import java.util.HashMap;

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
}
