package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * Algorithm to scale images based on their height
 */
public class ScaleVertically extends AbstractImageAlgorithm {

    /**
     * Scales the image that it keeps its ratio and has the specified height afterwards.
     */
    @Override
    public Transform getTransformation(Image image, int height, int currentWidth, int currentHeight) {
        assert height < currentHeight;
        assert height > 0;
        float ratio = (float) height / (float) currentHeight;
        int width = Math.round((float) currentWidth * ratio);
        return ImagesServiceFactory.makeResize(width, height);
    }
}
