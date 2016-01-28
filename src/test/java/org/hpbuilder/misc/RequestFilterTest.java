package org.hpbuilder.misc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;

/**
 * Created by didi on 13.10.2015.
 */
public class RequestFilterTest {

    private final Logger log = LoggerFactory.getLogger(RequestFilterTest.class);

    @Test
    public void testHtmlRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        //MediaType type = MediaType.parse("image/blub");

        //log.info("jpeg type", MediaType.JPEG.parameters());
    }
}
