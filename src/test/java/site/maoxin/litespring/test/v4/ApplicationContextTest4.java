package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.context.support.ClassPathXMLApplicationContext;
import site.maoxin.litespring.service.v3.PetStoreService;

/**
 * 顶层测试类
 * @author Maoxin
 * @ClassName ApplicationContextTest4
 * @date 3/25/2019
 */
public class ApplicationContextTest4 {
    @Test
    public void testGetBeanProperty() {
        ApplicationContext ctx = new ClassPathXMLApplicationContext("store-v4.xml");
        PetStoreService petStore = (PetStoreService)ctx.getBean("petStore");

        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }
}
