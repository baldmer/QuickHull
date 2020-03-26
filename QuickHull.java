
import java.util.ArrayList;
import java.util.Scanner;

public class QuickHull
{
    public void hullSet(Point A, Point B, ArrayList<Point> set, ArrayList<Point> hull)
    {
        int insertPosition = hull.indexOf(B);
        
        if (set.size() == 0)
            return;
        
        if (set.size() == 1)
        {
            Point p = set.get(0);
            set.remove(p);

            hull.add(insertPosition, p);

            return;
        }
        
        double dist = 0.0;
        int farthestPointIndex = -1;
        
        int index = 0;
        
        for (Point p : set)
        {
            double distance =  p.getDistanceToLine(A, B);
            if (distance > dist)
            {
                dist = distance;
                farthestPointIndex = index;
            }
            index ++;
        }
        
        Point P = set.get(farthestPointIndex);
        set.remove(P);
        hull.add(insertPosition, P);
        
        // Determine points on the right of the line traced by points AP
        ArrayList<Point> rightPointsAP = new ArrayList<Point>();
        index = 0;
        
        for (Point q : set){
            if (q.isRightOfLine(A, P))
                rightPointsAP.add(q);
        }
        
        // Determine points on the right of the line traced by points PB
        ArrayList<Point> rightPointsPB = new ArrayList<Point>();
        index = 0;
        
        for (Point q : set)
            if (q.isRightOfLine(P, B))
                rightPointsPB.add(q);
                
        hullSet(A, P, rightPointsAP, hull);
        hullSet(P, B, rightPointsPB, hull);
    }

    public ArrayList<Point> run(ArrayList<Point> points)
    {
        ArrayList<Point> convexHull = new ArrayList<Point>();
        
        if (points.size() < 3)
            return points;
        
        int minPoint = -1, maxPoint = -1;
        
        // make sure to take the very (minX, maxX)
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        
        int index = 0;
        
        for (Point p : points)
        {
            int x = p.getX();
                    
            if (x < minX)
            {
                minX = x;
                minPoint = index;
            }
            if (x > maxX)
            {
                maxX = x;
                maxPoint = index;
            }
            index ++;
        }
        
        // initialize convexHull with the min/max point on the x axis.
        Point A = points.get(minPoint);
        Point B = points.get(maxPoint);
        
        convexHull.add(A);
        convexHull.add(B);
        
        // discard from the original points
        points.remove(A);
        points.remove(B);
        
        // the original set is splitted by the line formed by point (A,B).
        ArrayList<Point> leftPoints = new ArrayList<Point>();
        ArrayList<Point> rightPoints = new ArrayList<Point>();
        
        for (Point p : points)
        {
            if (p.isRightOfLine(A, B))
                rightPoints.add(p);
            else
                leftPoints.add(p);
        }
        
        hullSet(A, B, rightPoints, convexHull);
        hullSet(B, A, leftPoints, convexHull);
        
        return convexHull;
    }
}
