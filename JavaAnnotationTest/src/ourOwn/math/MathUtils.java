package ourOwn.math;

import ourOwn.docgen.Doc;

import java.awt.geom.Point2D;

@Doc(
        desc = "Utility class for commonly used math fauntions"
)
public class MathUtils {
    private static final Double EPSILON = 0.0001;

    @Doc(
            desc = "Calculate the area of a triangle",
            params = {"Coordinates of the first vertex"},
            returnVal = "Calcuated are of the traiangle"
    )

    public static Double triangleArea(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
        return 0.0;
    }

    @Doc(
            desc = "Calcualtes the distance bewteen the give npoints",
            params = {"Coordinates of one point", "coordinates of anther point"},
            returnVal = "the number"
    )
    public static Double distance(Point2D.Double a, Point2D.Double b) {
        return 0.0;
    }

    @Doc(
            desc = "display the vlaue of epsilson"
    )
    public static Double[] quadraticRoots(int a, int b, int c) {
        return new Double[]{};
    }


    public static void epsilon() {

    }

    private static boolean arePointsClose(Point2D.Double a, Point2D.Double b) {
        return false;
    }
}