package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;


/**
 * Algorithm to crop images vertically
 */
public class CropVertically extends AbstractImageAlgorithm {

    /**
     * The Algorithms crops the center:
     * An 1000x500 Image that should get cropped in width to 500, looses its left and right 250 Pixels.
     */
    @Override
    public Transform getTransformation(Image image, int height, int currentWidth, int currentHeight) {
        assert height < currentHeight;
        float verticalCrop = (((float)currentHeight - (float)height) / 2) / (float)currentHeight;
        return ImagesServiceFactory.makeCrop(0, verticalCrop, 1, 1-verticalCrop);
    }

}
