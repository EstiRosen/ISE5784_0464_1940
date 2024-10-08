package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {
    double DELTA = 0.0001;

    /**
     * Test method for Tube getNormal.
     */
    @Test
    void testGetNormal() {
        Tube tube = new Tube(2, new Ray(new Point(0,0,0), new Vector(1,0,0)));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test calculation of normal vector for a point on the surface of the tube
        Vector normal = tube.getNormal(new Point(2,2,0));
        assertEquals(new Vector(0, 1, 0),
                    normal,
                    "ERROR: tube getNormal() does not work correctly");
        // ensure |result| = 1
        assertEquals(1,
                    normal.length(),
                    DELTA,
                    "Polygon's normal is not a unit vector");

        // =============== Boundary Values Tests ==================
        // TC11: Test calculation of normal vector for a point directly opposite the ray's origin (forming a right angle with the axis)
        normal = tube.getNormal(new Point(0, 2, 0));
        assertEquals(new Vector(0, 1, 0),
                    normal,
                    "ERROR: tube getNormal() does not work correctly at the boundary point");
        // ensure |result| = 1
        assertEquals(1,
                    normal.length(),
                    DELTA,
                    "Tube's normal is not a unit vector");
    }
}