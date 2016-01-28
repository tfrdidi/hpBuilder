package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by didi on 10.01.2016.
 */
public abstract class AbstractImageAlgorithm implements ImageAlgorithm {

    final Logger log = LoggerFactory.getLogger(AbstractImageAlgorithm.class);

    protected ImagesService imagesService = ImagesServiceFactory.getImagesService();

    @Override
    public Image run(Image image, int parameter) {

        assert image != null;
        assert parameter > 0;

        log.debug("start image processing");
        Transform transform = getTransformation(image, parameter, image.getWidth(), image.getHeight());
        Image result = imagesService.applyTransform(transform, image);
        log.debug("end image processing");
        return result;
    }

    public abstract Transform getTransformation(Image image, int parameter, int currentWidth, int currentHeight);
}
