// ID: 318811304

/**
 * @author Ben Levi
 * Class Operation : the class represent properties and methods of point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Function Name : Point.
     * Function Operation : constructor.
     * @param x the x value.
     * @param y the y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function Name : distance.
     * Function Operation : the function calculate distance between 2 points.
     * @param other the second point.
     * @return : double distance.
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Function Name : equals.
     * Function Operation : the function check if 2 points are equals.
     * @param other the second point.
     * @return : boolean true \ false.
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    /**
     * Function Name : getX.
     * Function Operation : the function return x value of this point.
     * @return : double x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Function Name : getY.
     * Function Operation : the function return y value of this point.
     * @return : double y value.
     */
    public double getY() {
        return this.y;
    }
}
