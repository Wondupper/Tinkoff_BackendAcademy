package backend.academy.realization.renders;

import backend.academy.realization.model.AffineTransformation;
import backend.academy.realization.model.FractalImage;
import backend.academy.realization.model.Rect;
import backend.academy.realization.transformations.Transformation;
import java.util.List;

public class ConsistentFractalRenderer extends AbstractRenderer {
    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        AffineTransformation[] transformations = AffineTransformation.getRandomTransformations(samples);
        for (int num = 0; num < samples; num++) {
            startRender(
                canvas,
                world,
                variations,
                samples,
                iterPerSample,
                symmetry,
                transformations
            );
        }
        return canvas;
    }
}
