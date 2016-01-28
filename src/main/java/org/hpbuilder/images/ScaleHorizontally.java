package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

/**
 * Algorithm to scale images based on their width
 */
public class ScaleHorizontally extends AbstractImageAlgorithm {

    /**
     * Scales the image that it keeps its ratio and has the specified width afterwards.
     */
    @Override
    public Transform getTransformation(Image image, int width, int currentWidth, int currentHeight) {
        assert width < currentWidth;
        assert width > 0;
        float ratio = (float) width / (float) currentWidth;
        int height = Math.round((float)currentHeight * ratio);
        return ImagesServiceFactory.makeResize(width, height);
    }
}
