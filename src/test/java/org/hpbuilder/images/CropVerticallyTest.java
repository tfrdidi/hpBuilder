package org.hpbuilder.images;

import com.google.appengine.api.images.Image;
import org.hpbuilder.misc.Reader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Test class for <code>CropVertically</code>
 */
public class CropVerticallyTest {

    private final Logger log = LoggerFactory.getLogger(CropVerticallyTest.class);

    @Test
    public void testHorizontalCrop() {
        try {
            Image testImage = Reader.getFileContentAsImage("ablauf.jpg");
            CropVertically algorithm = new CropVertically();
            AlgorithmInput algorithmInput = new AlgorithmInput();
            algorithmInput.setType(ImageAlgorithm.Type.Crop);
            Map<ImageAlgorithm.Parameter, Integer> parameters = new HashMap<>();
            parameters.put(ImageAlgorithm.Parameter.Width, 500);
            algorithmInput.setParameters(parameters);
            Image result = algorithm.getTransformation(testImage, algorithmInput, , );
            assertEquals(501, result.getWidth());
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            assertFalse(true);
        }
    }
}
