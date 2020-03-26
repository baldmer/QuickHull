
import java.util.ArrayList;

public class GrainSize
{
    public static void main(String args[])
    {
        String line = "3150,1486,3155,1486,3157,1489,3159,1492,3162,1494,3164,1497,3168,1498,3170,1501,3172,1504,3173,1508,3169,1509,3165,1508,3161,1507,3158,1507,3154,1506,3153,1502,3151,1499,3151,1494,3149,1491,3149,1488";
        
        // collect the points
        ArrayList<Point> points = new ArrayList<Point>();
        String[] values = line.split(",");
        
        for (int i = 0; i < values.length; i += 2){
            int x = Integer.parseInt(values[i]);
            int y = Integer.parseInt(values[i + 1]);
            
            Point p = new Point(x, y);
            points.add(p);
        }
        
        // run Quick Hull algorithm
        QuickHull quickHull = new QuickHull();
        ArrayList<Point> convexHull = quickHull.run(points);
        
        // do something with the Convex Hull (calculate size of object)
        double diameter = 0.0;
        int convexHullSize = convexHull.size();
        
        for (int i = 0; i < convexHullSize; i ++){
            for (int j = 0; j < convexHullSize; j ++){
            
                Point p1 = convexHull.get(i);
                Point p2 = convexHull.get(j);
                
                double distance = p1.euclideanDistance(p2);
                        
                if (distance > diameter)
                    diameter = distance;
            }
        }
        
        System.out.printf("The size of the object is: %s \n", diameter);
    }
}
