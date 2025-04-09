package backend.academy.realization.transformations;

import backend.academy.realization.model.Point;

public class LinearTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        return new Point(
            point.x(),
            point.y()
        );
    }
}
