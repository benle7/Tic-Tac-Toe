// ID: 318811304

import java.util.List;

/**
 * @author Ben Levi
 * Class Operation : the class represent properties and methods of line.
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Function Name : Line.
     * Function Operation : constructor.
     * @param start the first point.
     * @param end the second point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Function Name : Line.
     * Function Operation : constructor(another option).
     * @param x1 the x value of first point.
     * @param y1 the y value of first point.
     * @param x2 the x value of second point.
     * @param y2 the y value of second point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Function Name : length.
     * Function Operation : the function calculate line length.
     * @return : double length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Function Name : middle.
     * Function Operation : the function calculate line middle.
     * @return : Point middle.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2.0;
        double y = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(x, y);
    }

    /**
     * Function Name : start.
     * Function Operation : the function return start point of line.
     * @return : Point start.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Function Name : end.
     * Function Operation : the function return end point of line.
     * @return : Point end.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Function Name : isIntersecting.
     * Function Operation : the function check if 2 lines are intersecting.
     * @param other the second line.
     * @return : boolean true \ false.
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }

    /**
     * Function Name : intersectionWith.
     * Function Operation : the function return intersection point or null.
     * @param other the second line.
     * @return : Point intersection point (or null).
     */
    public Point intersectionWith(Line other) {
        double myStartX = this.start.getX();
        double myStartY = this.start.getY();
        double myEndX = this.end.getX();
        double myEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        // case of 2 lines with no slant
        if ((myStartX == myEndX) && (otherStartX == otherEndX)) {
            return interNoSlant(other);
        }
        // case of 2 lines that only one of them with slant.
        if (((myStartX != myEndX) && (otherStartX == otherEndX))
                || ((myStartX == myEndX) && (otherStartX != otherEndX))) {
            return interOneSlant(other);
        }
        // now both lines have slant so we save them.
        double mySlant = (myStartY - myEndY) / (myStartX - myEndX);
        double otherSlant = (otherStartY - otherEndY) / (otherStartX - otherEndX);
        // case of 2 lines with equal slants.
        if (mySlant == otherSlant) {
            return interSameSlant(other);
        }
        // last case of 2 lines with different slants.
        return interDifSlant(other);
    }

    /**
     * Function Name : closestIntersectionToStartOfLine.
     * Function Operation : the function return closest intersection with rect.
     * @param rect the rectangle.
     * @return : Point closest intersection point (or null).
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        // if have no intersections.
        if (intersections.size() == 0) {
            return null;
        }
        Point closest = intersections.get(0);
        for (int i = 1; i < intersections.size(); i++) {
            if (this.start.distance(intersections.get(i)) < this.start.distance(closest)) {
                closest = intersections.get(i);
            }
        }
        return closest;
    }

    /**
     * Function Name : equals.
     * Function Operation : the function check if 2 lines are equals.
     * @param other the second line.
     * @return : boolean true \ false.
     */
    public boolean equals(Line other) {
        return (((this.start.equals(other.start))
                && (this.end.equals(other.end)))
                || ((this.start.equals(other.end))
                && (this.end.equals(other.start))));
    }

    /**
     * Function Name : interNoSlant.
     * Function Operation : the helper function return intersection point
     * or null, in case of 2 lines with no slant.
     * @param other the second line.
     * @return : Point intersection point (or null).
     */
    private Point interNoSlant(Line other) {
        double myStartX = this.start.getX();
        double myStartY = this.start.getY();
        double myEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndY = other.end.getY();

        /*
         * we in case of lines from type x=? ,
         * so we check if they with equal x value,
         * if yes, so the only option of intersection is
         * max touch min, if not we return null.
         */
        if (myStartX == otherStartX) {
            if (Math.max(myStartY, myEndY) == Math.min(otherStartY, otherEndY)) {
                return new Point(myStartX, Math.max(myStartY, myEndY));
            }
            if (Math.max(otherStartY, otherEndY) == Math.min(myStartY, myEndY)) {
                return new Point(myStartX, Math.min(myStartY, myEndY));
            }
        }
        return null;
    }

    /**
     * Function Name : interOneSlant.
     * Function Operation : the helper function return intersection point
     * or null, in case of 2 lines that only one of them with slant.
     * @param other the second line.
     * @return : Point intersection point (or null).
     */
    private Point interOneSlant(Line other) {
        double myStartX = this.start.getX();
        double myStartY = this.start.getY();
        double myEndX = this.end.getX();
        double myEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        // we in case of one slant so we check the first option.
        if ((myStartX != myEndX) && (otherStartX == otherEndX)) {
            double slant = (myStartY - myEndY) / (myStartX - myEndX);
            // cutting with y of this line.
            double k = myStartY - (slant * myStartX);
            // y value of this line at the constant x value of the other line.
            double y = (slant * otherStartX) + k;

            /*
             * if y between y values of other line,
             * and the x between the x values of this line.
             */
            if ((y <= Math.max(otherStartY, otherEndY))
                    && (y >= Math.min(otherStartY, otherEndY))
                    && (otherStartX <= Math.max(myStartX, myEndX))
                    && (otherStartX >= Math.min(myStartX, myEndX))) {
                return new Point(otherStartX, y);
            }
            return null;
        }
        // we in case of one slant so we check the second option.
        double slant = (otherStartY - otherEndY) / (otherStartX - otherEndX);
        // cutting with y of other line.
        double k = otherStartY - (slant * otherStartX);
        // y value of other line at the constant x value of this line.
        double y = (slant * myStartX) + k;

        /*
         * if y between y values of this line,
         * and the x between the x values of other line.
         */
        if ((y <= Math.max(myStartY, myEndY))
                && (y >= Math.min(myStartY, myEndY))
                && (myStartX <= Math.max(otherStartX, otherEndX))
                && (myStartX >= Math.min(otherStartX, otherEndX))) {
            return new Point(myStartX, y);
        }
        return null;
    }

    /**
     * Function Name : interSameSlant.
     * Function Operation : the helper function return intersection point
     * or null, in case of 2 lines with equal slant.
     * @param other the second line.
     * @return : Point intersection point (or null).
     */
    private Point interSameSlant(Line other) {
        double myStartX = this.start.getX();
        double myStartY = this.start.getY();
        double myEndX = this.end.getX();
        double myEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        // we in case of 2 lines with equal slants so we save it.
        double slant = (myStartY - myEndY) / (myStartX - myEndX);
        // first option of positive slant.
        if (slant >= 0) {
            // this max x,y touch other min x,y.
            if ((Math.max(myStartX, myEndX)
                    == Math.min(otherStartX, otherEndX))
                    && (Math.max(myStartY, myEndY)
                    == Math.min(otherStartY, otherEndY))) {
                return new Point(Math.max(myStartX, myEndX),
                        Math.max(myStartY, myEndY));
            }
            // other max x,y touch this min x,y.
            if ((Math.max(otherStartX, otherEndX)
                    == Math.min(myStartX, myEndX))
                    && (Math.max(otherStartY, otherEndY)
                    == Math.min(myStartY, myEndY))) {
                return new Point(Math.min(myStartX, myEndX),
                        Math.min(myStartY, myEndY));
            }
            return null;
        }
        // second option of negative slant.
        // this max x, min y touch other min x, max y.
        if ((Math.max(myStartX, myEndX)
                == Math.min(otherStartX, otherEndX))
                && (Math.min(myStartY, myEndY)
                == Math.max(otherStartY, otherEndY))) {
            return new Point(Math.max(myStartX, myEndX),
                    Math.min(myStartY, myEndY));
        }
        // other max x, min y touch this min x, max y.
        if ((Math.max(otherStartX, otherEndX)
                == Math.min(myStartX, myEndX))
                && (Math.min(otherStartY, otherEndY)
                == Math.max(myStartY, myEndY))) {
            return new Point(Math.min(myStartX, myEndX),
                    Math.max(myStartY, myEndY));
        }
        return null;
    }

    /**
     * Function Name : interDifSlant.
     * Function Operation : the helper function return intersection point
     * or null, in case of 2 lines with different slant.
     * @param other the second line.
     * @return : Point intersection point (or null).
     */
    private Point interDifSlant(Line other) {
        double myStartX = this.start.getX();
        double myStartY = this.start.getY();
        double myEndX = this.end.getX();
        double myEndY = this.end.getY();
        double otherStartX = other.start.getX();
        double otherStartY = other.start.getY();
        double otherEndX = other.end.getX();
        double otherEndY = other.end.getY();
        // we in case of 2 lines with different slants so we save them.
        double mySlant = (myStartY - myEndY) / (myStartX - myEndX);
        double otherSlant = (otherStartY - otherEndY) / (otherStartX - otherEndX);
        // we save the cutting values with y of each line.
        double myK = myStartY - (mySlant * myStartX);
        double otherK = otherStartY - (otherSlant * otherStartX);
        // we find x value of intersection point.
        double x = (myK - otherK) / (otherSlant - mySlant);
        // we place the x in the equation and find the y value.
        double y = (mySlant * x) + myK;
        // we check that the intersection point is between start,end of the lines.
        if ((x >= Math.min(myStartX, myEndX))
                && (x <= Math.max(myStartX, myEndX))
                && (x >= Math.min(otherStartX, otherEndX))
                && (x <= Math.max(otherStartX, otherEndX))
                && (y >= Math.min(myStartY, myEndY))
                && (y <= Math.max(myStartY, myEndY))
                && (y >= Math.min(otherStartY, otherEndY))
                && (y <= Math.max(otherStartY, otherEndY))) {
            return new Point(x, y);
        }
        return null;
    }
}
