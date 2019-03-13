package site.maoxin.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.service.v1.PetStoreService;

/**
 * @author Maoxin
 * @ClassName BeanScopeTest
 * @date 3/12/2019
 */
public class BeanScopeTest {
    @Test
    public void testSingleton(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        xmlBeanDefinitionReader.loadBeanDefinitions(new ClassPathResource("store-v1.xml"));
        factory.getBeanDefinition("store-v1.xml");
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertTrue(bd.isSignleton());

        Assert.assertFalse(bd.isPrototype());
        Object object = factory.getBean("petStore");
        System.out.println(object);
        PetStoreService petStoreService = (PetStoreService)object;
        PetStoreService petStoreServiceA = (PetStoreService)factory.getBean("petStore");
        Assert.assertNotNull(petStoreServiceA);
        Assert.assertTrue(petStoreService.equals(petStoreServiceA));


    }
}
