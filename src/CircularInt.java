public class CircularInt{
    int down , up , value;
    public CircularInt(int down , int up){
        this.down = down;
        this.up = up;
        this.value = down;
    }
    public void inc(){
        if(value < up) value++;
        else if(value == up) value = down;
    }
    public int get(){
        return value;
    }
}