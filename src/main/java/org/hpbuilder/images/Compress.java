package org.hpbuilder.images;

import com.google.appengine.api.images.*;

/**
 * Compresses the image with the specified parameter
 */
public class Compress implements ImageAlgorithm {

    /**
     * @param parameter quality: 0-bad,  100-good
     */
    @Override
    public Image run(Image image, int parameter) {
        OutputSettings settings = new OutputSettings(ImagesService.OutputEncoding.JPEG);
        settings.setQuality(parameter);
        Transform compress = ImagesServiceFactory.makeResize(image.getWidth(), image.getHeight());
        ImagesService imagesService = ImagesServiceFactory.getImagesService();
        return imagesService.applyTransform(compress, image, settings);
    }
}
