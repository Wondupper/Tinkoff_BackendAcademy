package backend.academy.realization.renders;

import backend.academy.realization.model.FractalImage;
import backend.academy.realization.model.Rect;
import backend.academy.realization.transformations.Transformation;
import java.util.List;

@FunctionalInterface
public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    );
}
