package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = 1 / (x * x + y * y);
        return new Point(
            r * x,
            r * y
        );
    }
}
