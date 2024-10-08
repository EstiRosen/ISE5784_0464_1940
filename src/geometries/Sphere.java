package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a sphere in three-dimensional space.
 */
public class Sphere extends RadialGeometry {
    private final Point center; // The center point of the sphere

    /**
     * Constructs a new Sphere instance with the specified radius and center point.
     *
     * @param radius The radius of the sphere.
     * @param center The center point of the sphere.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    /**
     * calculates normal to sphere by given point
     * @param point The point on the geometry's surface.
     * @return normal to sphere by given point
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    /**
     * Finds the intersections of the given ray with the current sphere.
     *
     * @param ray The ray to intersect with the sphere.
     * @return A list of {@link GeoPoint} objects representing the intersection points, or null if there are no intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getHead();  // The starting point of the ray
        Vector v = ray.getDirection();  // The direction vector of the ray

        // Deals with case where ray starts from the center of the sphere
        if (p0.equals(center))
            return List.of(new GeoPoint(this, ray.getPoint(radius)));

        Vector u = center.subtract(p0);  // Vector from ray start point to sphere center
        double tm = v.dotProduct(u);  // Projection of u onto v
        double d = Math.sqrt(u.lengthSquared() - tm * tm);  // Distance from sphere center to ray
        if (d >= radius)  // If the distance is greater than or equal to the radius, no intersection
            return null;
        double th = Math.sqrt(radius * radius - d * d);  // Distance from intersection points to the point closest to the center

        double t1 = tm - th;  // Distance to the first intersection point
        double t2 = tm + th;  // Distance to the second intersection point

        if (alignZero(t1) > 0 && alignZero(t2) > 0) {  // Both intersections are in front of the ray start
            Point p1 = ray.getPoint(t1);  // First intersection point
            Point p2 = ray.getPoint(t2);  // Second intersection point
            return List.of(new GeoPoint(this, p1), new GeoPoint(this, p2));
        } else if (alignZero(t1) > 0) {  // Only the first intersection is in front of the ray start
            Point p1 = ray.getPoint(t1);  // First intersection point
            return List.of(new GeoPoint(this, p1));
        } else if (alignZero(t2) > 0) {  // Only the second intersection is in front of the ray start
            Point p2 = ray.getPoint(t2);  // Second intersection point
            return List.of(new GeoPoint(this, p2));
        }

        return null;  // No intersections in front of the ray start
    }
}

