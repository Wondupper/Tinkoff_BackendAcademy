package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;
import static java.lang.Math.sqrt;

public class CrossTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(1.0 / ((x * x - y * y) * (x * x - y * y)));
        double newX = x * r;
        double newY = y * r;
        return new Point(newX, newY);
    }
}
