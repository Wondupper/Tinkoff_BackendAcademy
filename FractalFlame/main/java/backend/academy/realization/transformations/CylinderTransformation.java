package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;
import static java.lang.Math.sin;

public class CylinderTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = sin(point.x());
        double newY = point.y();
        return new Point(newX, newY);
    }
}
