package backend.academy.realization.processors;

import backend.academy.realization.model.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image);
}
