package site.maoxin.litespring.test.v2;

import static org.junit.Assert.*;
import org.junit.Test;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.context.support.ClassPathXMLApplicationContext;
import site.maoxin.litespring.dao.v2.AccountDao;
import site.maoxin.litespring.dao.v2.ItemDao;
import site.maoxin.litespring.service.v2.PetStoreServiceV2;

/**
 * @author Maoxin
 * @date 3/16/2019
 */
public class ApplicationContextTestV2 {
    @Test
    public void testGetBeanProperties(){
        ApplicationContext ctx = new ClassPathXMLApplicationContext("store-v2.xml");
        PetStoreServiceV2 petStoreServiceV2 = (PetStoreServiceV2) ctx.getBean("petStore");
        assertNotNull(petStoreServiceV2.getAccountDao());
        assertNotNull(petStoreServiceV2.getItemDao());

        assertTrue(petStoreServiceV2.getAccountDao() instanceof AccountDao);
        assertTrue(petStoreServiceV2.getItemDao() instanceof ItemDao);

        assertEquals(petStoreServiceV2.getOwner(),"maoxin");
        assertEquals(petStoreServiceV2.getVersion(),2);
    }
}
