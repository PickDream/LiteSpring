package site.maoxin.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.context.support.ClassPathXMLApplicationContext;
import site.maoxin.litespring.service.v3.PetStoreService;

/**
 * 总的测试方法
 * @author Maoxin
 * @ClassName ApplicationContextTestV3
 * @date 3/25/2019
 */
public class ApplicationContextTestV3 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXMLApplicationContext("store-v3.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(1, petStore.getVersion());

    }
}
