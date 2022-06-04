// ID: 318811304

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Levi
 * Class Operation : the class represent properties and methods of Rectangle.
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Function Name : Rectangle.
     * Function Operation : constructor.
     * @param upperLeft the point.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Function Name : Rectangle.
     * Function Operation : constructor.
     * @param x the x value of upperLeft point.
     * @param y the y value of upperLeft point.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Function Name : getWidth.
     * Function Operation : the function return the width.
     * @return : double width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Function Name : getHeight.
     * Function Operation : the function return the height.
     * @return : double height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Function Name : getUpperLeft.
     * Function Operation : the function return the UpperLeft point.
     * @return : Point upperLeft.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Function Name : intersectionPoints.
     * Function Operation : the function return list of
     * intersection Points between the rectangle and the line.
     * @param line the line.
     * @return : java.util.List<Point> intersections list.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        Line upperLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY());
        Line lowerLine = new Line(this.upperLeft.getX(), this.upperLeft.getY() + height,
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        Line rightLine = new Line(this.upperLeft.getX() + width, this.upperLeft.getY(),
                this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        Line leftLine = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + height);
        List<Point> intersections = new ArrayList<>();

        // check intersections with 4 ribs of the rectangle.
        if (upperLine.isIntersecting(line)) {
            intersections.add(upperLine.intersectionWith(line));
        }
        if (lowerLine.isIntersecting(line)) {
            intersections.add(lowerLine.intersectionWith(line));
        }
        if (rightLine.isIntersecting(line)) {
            intersections.add(rightLine.intersectionWith(line));
        }
        if (leftLine.isIntersecting(line)) {
            intersections.add(leftLine.intersectionWith(line));
        }
        // remove duplicates.
        return removeDuplicates(intersections);
    }

    /**
     * Function Name : removeDuplicates.
     * Function Operation : the function return list of
     * intersection Points after remove duplicates.
     * @param intersections the list.
     * @return : java.util.List<Point> list (after remove duplicates).
     */
    private java.util.List<Point> removeDuplicates(List<Point> intersections) {
        for (int i = 0; i < intersections.size(); i++) {
            for (int j = i + 1; j < intersections.size(); j++) {
                if ((intersections.get(j)).equals((intersections.get(i)))) {
                    intersections.remove(intersections.get(j));
                }
            }
        }
        return intersections;
    }
}
