package site.maoxin.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Maoxin
 * @date 3/11/2019
 */
public class ResourceTest {
    @Test
    public void testClassPathResource() throws IOException {
        Resource r = new ClassPathResource("");
        InputStream is = null;
        try{
            is = r.getInputStream();
            Assert.assertNotNull(is);
        }finally {
            if (is!=null){
                is.close();
            }
        }
    }
}
