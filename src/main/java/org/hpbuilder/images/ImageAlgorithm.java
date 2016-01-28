package org.hpbuilder.images;

import com.google.appengine.api.images.Image;

/**
 * Interface for all image algorithms.
 */
public interface ImageAlgorithm {

    Image run(Image image, int parameter);

}
