package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SwirlTransform implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = x * x + y * y;
        return new Point(
            x * sin(r) - y * cos(r),
            x * cos(r) + y * sin(r)
        );
    }
}
