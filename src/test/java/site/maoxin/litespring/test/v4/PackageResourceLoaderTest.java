package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.core.io.support.PackageResourceLoader;

import java.io.IOException;

/**
 * @author Maoxin
 * @ClassName PackageResourceLoaderTest
 * @date 3/25/2019
 */
public class PackageResourceLoaderTest {
    @Test
    public void testGetResources() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("site.maoxin.litespring.dao.v4");
        Assert.assertEquals(2, resources.length);
    }
}
