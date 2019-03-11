package site.maoxin.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.context.support.ClassPathXMLApplicationContext;
import site.maoxin.litespring.service.v1.PetStoreService;

/**
 * ApplicationContext
 * @author Maoxin
 * @date 3/11/2019
 */
public class ApplicationContextTest{
    @Test
    public void testGetBean(){
        ApplicationContext ac = new ClassPathXMLApplicationContext("store-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ac.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
