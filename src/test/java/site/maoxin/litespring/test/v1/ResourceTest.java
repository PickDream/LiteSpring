package site.maoxin.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.FileSystemResource;
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
        Resource r = new ClassPathResource("store-v1.xml");
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
    @Test
    public void testfilePathResource()throws IOException{
        Resource r = new FileSystemResource("D:\\doc\\LiteSpring\\src\\test\\resources\\store-v1.xml");
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
