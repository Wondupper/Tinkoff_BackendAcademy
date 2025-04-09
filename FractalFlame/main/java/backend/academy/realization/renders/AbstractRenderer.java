package backend.academy.realization.renders;

import backend.academy.realization.model.AffineTransformation;
import backend.academy.realization.model.FractalImage;
import backend.academy.realization.model.Pixel;
import backend.academy.realization.model.Point;
import backend.academy.realization.model.Rect;
import backend.academy.realization.transformations.Transformation;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ParameterAssignment")
public abstract class AbstractRenderer implements Renderer {
    protected static final int START_DRAW = -20;

    protected Point applyTransformationToPoint(Point p, AffineTransformation t) {
        double newX = t.a() * p.x() + t.b() * p.y() + t.c();
        double newY = t.d() * p.x() + t.e() * p.y() + t.f();
        return new Point(newX, newY);
    }

    protected void setPixelColor(Pixel pixel, AffineTransformation affineTransformation) {
        if (pixel.getHitCount() == 0) {
            pixel.setR(affineTransformation.color().getRed());
            pixel.setG(affineTransformation.color().getGreen());
            pixel.setB(affineTransformation.color().getBlue());
        } else {
            pixel.setR((pixel.getR() + affineTransformation.color().getRed()) / 2);
            pixel.setG((pixel.getG() + affineTransformation.color().getGreen()) / 2);
            pixel.setB((pixel.getB() + affineTransformation.color().getBlue()) / 2);
        }
    }

    protected Optional<Pixel> transformPixel(FractalImage canvas, Point pwr, Rect world) {
        return canvas.getPixel(
            (int) (((pwr.x() - world.x())) * canvas.width() / world.width()),
            (int) ((pwr.y() - world.y()) * canvas.height() / world.height())
        );
    }

    protected void setPixelHitCountSync(
        Optional<Pixel> pixelOptional,
        AffineTransformation[] transformations,
        int indexTransformation
    ) {
        Pixel pixel = pixelOptional.get();
        synchronized (pixel) {
            setPixelColor(pixel, transformations[indexTransformation]);
            pixel.setHitCount(pixel.getHitCount() + 1);
        }
    }

    protected void setPixelHitCount(
        Optional<Pixel> pixelOptional,
        AffineTransformation[] transformations,
        int indexTransformation
    ) {
        Pixel pixel = pixelOptional.get();
        setPixelColor(pixel, transformations[indexTransformation]);
        pixel.setHitCount(pixel.getHitCount() + 1);
    }

    protected Point transformPoint(Point pw, List<Transformation> variations) {
        int variationIndex = ThreadLocalRandom.current().nextInt(0, variations.size());
        Transformation variation = variations.get(variationIndex);
        return variation.apply(pw);
    }

    protected void startRender(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        int iterPerSample,
        int symmetry,
        AffineTransformation[] transformations
    ) {
        Point pw = world.randomPoint();
        for (int step = START_DRAW; step < iterPerSample; step++) {
            int indexTransformation = ThreadLocalRandom.current().nextInt(0, samples);
            pw = applyTransformationToPoint(pw, transformations[indexTransformation]);
            if (step < 0) {
                continue;
            }
            pw = transformPoint(pw, variations);
            double theta = 0.0;
            for (int s = 0; s < symmetry; theta += 2 * Math.PI / symmetry, s++) {
                Point pwr = Point.getRotatedPoint(pw, theta);
                if (!world.containsPoint(pwr)) {
                    continue;
                }
                Optional<Pixel> pixelOptional = transformPixel(canvas, pwr, world);
                if (pixelOptional.isPresent()) {
                    setPixelHitCount(pixelOptional, transformations, indexTransformation);
                }
            }
        }
    }
}
