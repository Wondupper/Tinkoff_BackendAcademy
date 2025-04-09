package backend.academy;

import backend.academy.realization.FractalFlameGenerator;
import backend.academy.realization.model.ImageFormat;
import backend.academy.realization.model.Rect;
import backend.academy.realization.processors.GammaCorrectionImageProcessor;
import backend.academy.realization.processors.ImageProcessor;
import backend.academy.realization.renders.ParallelFractalRenderer;
import backend.academy.realization.renders.Renderer;
import backend.academy.realization.transformations.CylinderTransformation;
import backend.academy.realization.transformations.SphericalTransformation;
import backend.academy.realization.transformations.Transformation;
import java.nio.file.Path;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class Main {
    public static void main(String[] args) {
        int countOfFlows = 4;
        Renderer renderer = new ParallelFractalRenderer(countOfFlows);
        double x = -2.0;
        double y = -1.0;
        double width = 4.0;
        double height = 2.0;
        Rect rect = new Rect(x, y, width, height);
        List<Transformation> transformations = List.of(
            new SphericalTransformation(),
            new CylinderTransformation()
        );
        ImageProcessor processor = new GammaCorrectionImageProcessor();
        int imageWidth = 1920;
        int imageHeight = 1080;
        FractalFlameGenerator generator =
            new FractalFlameGenerator(imageWidth, imageHeight, renderer, rect, transformations, processor);
        Path path = Path.of("src/main/resources/a");
        int samples = 3;
        int iterPerSample = 10000000;
        int symmetry = 2;
        generator.generateFractalFlame(samples, iterPerSample, symmetry, path, ImageFormat.JPEG);
    }
}
