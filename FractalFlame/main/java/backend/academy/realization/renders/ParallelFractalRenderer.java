package backend.academy.realization.renders;

import backend.academy.realization.model.AffineTransformation;
import backend.academy.realization.model.FractalImage;
import backend.academy.realization.model.Rect;
import backend.academy.realization.transformations.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelFractalRenderer extends AbstractRenderer {

    private final int countOfFlows;

    public ParallelFractalRenderer(int countOfFlows) {
        this.countOfFlows = countOfFlows;
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        ExecutorService executorService = Executors.newFixedThreadPool(countOfFlows);
        AffineTransformation[] transformations = AffineTransformation.getRandomTransformations(samples);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int num = 0; num < samples; num++) {
            tasks.add(() -> {
                splitRender(
                    canvas,
                    world,
                    variations,
                    samples,
                    iterPerSample,
                    symmetry,
                    transformations
                );
                return null;
            });
        }
        try {
            executorService.invokeAll(tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
        return canvas;
    }

    private void splitRender(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        AffineTransformation[] transformations
    ) {
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
}
