public class Point {
    public double x = 0;
    public double y = 0;
    public Point(){}
    public Point(double x , double y){
        this.x = x;
        this.y = y;
    }
    public Point(String str){
        var points = str.split(",", 2);
        this.x = Double.parseDouble(points[0]);
        this.y = Double.parseDouble(points[1]);
    }
    public Point(Point other){this.x = other.x; this.y = other.y;}
    public Point plus(Point other){
        return new Point(this.x + other.x , this.y + other.y);
    }
    public Point minus(Point other){
        return new Point(this.x - other.x , this.y - other.y);

    }
    public Point scale(int c){
        return new Point(this.x * c , this.y*c);
    }
    public Point divideBy(int c){
        return new Point(this.x / c , this.y / c);
    }
    public static Point midPoint(Point p1 , Point p2){
        return p1.plus(p2).divideBy(2);
    }
    public String toString(){
        return ""+x+","+y;
    }
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Point))
            return false;
        if (obj == this)
            return true;
        Point p = (Point) obj;
        //return (this.x == p.x) && (this.y == p.y);
        return Utils.compareDoubles(this.x, p.x, 100) && Utils.compareDoubles(this.y, p.y, 100);
    }
    public void assign(Point p){
        this.x = p.x;
        this.y = p.y;
    }
}
