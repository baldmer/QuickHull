
public class Point 
{
    private int x;
    private int y;

    /**
    * Create a new point in R^2.
    *
    * @param x the x-coordinate
    * @param y the y-coordinate
    */
    Point(int x, int y) 
    {
        this.x = x;
        this.y = y;
    }


    public void setX(int x) 
    {
        this.x = x;
    }


    public void setY(int y) 
    { 
        this.y = y;
    }


    public int getX() 
    { 
        return x;
    }


    public int getY()
    {
        return y;
    }    


    /**
    * Calculate the cross product of three points.
    * @param A point located in the very left side.
    * @param B point located in the very right side.
    * @return cross product.
    */
    private double crossProduct(Point A, Point B)
    {
        return (B.x - A.x) * (this.y - A.y) - (B.y - A.y) * (this.x - A.x);
    }


    /**
    * Determine the area in which point P(this) is located according to the line
    * traced between point A and point B. 
    * @param A point located in the very left side.
    * @param B point located in the very right side.
    * @return Returns true if P is located in the right side of the line.
    */
    public boolean isRightOfLine(Point A, Point B)
    {
        return Double.compare(crossProduct(A, B), 0) > 0;
    }

    
    /**
    * Calculate the distance of (this) point to the line which is formed by points A and B.
    * @param A
    * @param B
    * @return The distance to the line.
    */
    public double getDistanceToLine(Point A, Point B) 
    {
        double cp;
        
        cp = (B.getX() - A.getX()) * (A.getY() - this.y) - (B.getY() - A.getY())*(A.getX() - this.x);
            
        if (cp < 0)
            cp = -cp;
            
        return cp;
    }

    
    /**
    * Calculate the euclidean distance between two points ('this' and B)
    * @param B the second point.
    * @return The euclideanDistance distance
    */
    public double euclideanDistance(Point B) 
    {
        return Math.sqrt(Math.pow(B.getX() - this.x, 2) + Math.pow(B.getY() - this.y, 2));
    }
} 
