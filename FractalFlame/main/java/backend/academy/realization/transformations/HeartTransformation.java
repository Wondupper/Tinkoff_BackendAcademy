package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = sqrt(x * x + y * y);
        double theta = atan2(y, x);
        return new Point(
            r * sin(theta * r),
            -r * cos(theta * r)
        );
    }
}
