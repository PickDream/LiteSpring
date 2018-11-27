package site.maoxin.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.BeanDefinitionStoreException;
import site.maoxin.litespring.beans.factory.BeanFactory;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.service.v1.PetStoreService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {
    @Test
    public void testGetBean(){

        BeanFactory factory = new DefaultBeanFactory("store-v1.xml");

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        assertEquals("site.maoxin.litespring.service.v1.PetStoreService",bd.getBeanClassName());

        PetStoreService petStoreService  = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean(){
        BeanFactory factory = new DefaultBeanFactory("store-v1.xml");
        try{
            factory.getBean("invalidBean");
        }catch (BeanCreationException e){
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testInvalidXML(){
        try{
            BeanFactory factory = new DefaultBeanFactory("???.xml");
        }catch (BeanDefinitionStoreException e){
            return;
        }
        Assert.fail("expect BeanCreationException");
    }
}
