package site.maoxin.litespring.test.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.context.support.ClassPathXMLApplicationContext;
import site.maoxin.litespring.service.v5.PetStoreService;
import site.maoxin.litespring.util.MessageTracker;

import java.util.List;

/**
 * @author maoxin
 * @ClassName ApplicationContextTest5
 * @date 4/9/2019
 */
public class ApplicationContextTest5 {

    @Before
    public void setUp(){
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceOrder(){
        ApplicationContext ctx = new ClassPathXMLApplicationContext("store-v5.xml");
        PetStoreService service = (PetStoreService) ctx.getBean("petstore");
        service.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3,msgs.size());
        Assert.assertEquals("start tx",msgs.get(0));
        Assert.assertEquals("place order",msgs.get(1));
        Assert.assertEquals("commit tx",msgs.get(2));

    }
}
