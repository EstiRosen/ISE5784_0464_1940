package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private final double DELTA = 0.000001;
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0) };
        Triangle tri = new Triangle(new Point(0, 0, 1),
                       new Point(1, 0, 0),
                       new Point(0, 1, 0));
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tri.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = tri.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1,
                    result.length(),
                    DELTA,
                    "Triangle's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 2; ++i)
            assertEquals(0d,
                        result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i-1])),
                        DELTA,
                        "Triangle's normal is not orthogonal to one of the edges");
    }

    @Test
    void testFindIntersections() {
    }
}