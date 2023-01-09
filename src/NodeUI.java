public class NodeUI {
    //double x , y;
    /**/
    public static Point[] getPoints(int[] arr , double x , double y){
        Point wireNodes[] = new Point[]{
            new Point(x+1 , y+0.5),
            new Point(x+0.5 , y),
            new Point(x , y+0.5),
            new Point(x+1 , y+1)
        };
        Point points[] = new Point[arr.length];
        for(int i=0 ; i<arr.length ; i++){
            points[i] = wireNodes[arr[i]];
        }
        return points;
    }
}
