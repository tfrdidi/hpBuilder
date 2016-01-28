package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * Algorithm to crop images horizontally.
 */
public class CropHorizontally extends AbstractImageAlgorithm {

    /**
     * The Algorithms crops the center:
     * An 500x1000 Image that should get cropped in height to 500, looses its upper and lower 250 Pixels.
     */
    @Override
    public Transform getTransformation(Image image, int width, int currentWidth, int currentHeight) {
        assert width < currentWidth;
        float horizontalCrop = (((float)currentWidth - (float)width) / 2) / (float)currentWidth;
        return ImagesServiceFactory.makeCrop(horizontalCrop, 0, 1-horizontalCrop, 1);
    }
}
