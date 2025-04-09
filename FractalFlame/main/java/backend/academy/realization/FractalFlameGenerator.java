package backend.academy.realization;

import backend.academy.realization.model.FractalImage;
import backend.academy.realization.model.ImageFormat;
import backend.academy.realization.model.ImageUtils;
import backend.academy.realization.model.Rect;
import backend.academy.realization.processors.ImageProcessor;
import backend.academy.realization.renders.Renderer;
import backend.academy.realization.transformations.Transformation;
import java.nio.file.Path;
import java.util.List;

public class FractalFlameGenerator {
    private final Renderer renderer;
    private final FractalImage canvas;
    private final Rect world;
    private final List<Transformation> variations;
    private final ImageProcessor imageProcessor;

    public FractalFlameGenerator(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> variations,
        ImageProcessor imageProcessor
    ) {
        this.canvas = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.variations = variations;
        this.imageProcessor = imageProcessor;
    }

    public void generateFractalFlame(
        int samples,
        int iterPerSample,
        int symmetry,
        Path path,
        ImageFormat imageFormat
    ) {
        if (renderer == null) {
            throw new IllegalArgumentException("Не указан рендер");
        }
        if (world == null) {
            throw new IllegalArgumentException("Не указана система координат рисунка");
        }
        if (variations == null || variations.isEmpty()) {
            throw new IllegalArgumentException("Не указаны преобразования");
        }
        if (imageProcessor == null) {
            throw new IllegalArgumentException("Не указан обработчик изображения");
        }
        renderer.render(canvas, world, variations, samples, iterPerSample, symmetry);
        imageProcessor.process(canvas);
        ImageUtils.save(canvas, path, imageFormat);
    }
}
