import java.util.Vector;

public class  CircularArray <T>{
    Vector<T> ints = new Vector<>();
    void setVector(Vector<T> ints){
        this.ints = ints;
    }
    Vector<T> getVector(int begin , int size){
        Vector<T> result = new Vector<>();
        for(int i=begin ; i< ints.size() ;i++){
            result.add(ints.get(i));
        }
        for(int i=0 ; i<begin ; i++){
            result.add(ints.get(i));
        }
        return Utils.shortenVector(result , size);
    }
}
