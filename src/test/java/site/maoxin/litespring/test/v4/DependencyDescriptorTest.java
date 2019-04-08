package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.factory.config.DependencyDescriptor;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.dao.v4.AccountDao;
import site.maoxin.litespring.service.v4.PetStoreService;

import java.lang.reflect.Field;

/**
 * @author Maoxin
 * @ClassName DependencyDescriptorTest
 * @date 4/8/2019
 */
public class DependencyDescriptorTest {

    @Test
    public void testResolveDependency()throws Exception{

        DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        Resource resource = new ClassPathResource("store-v4.xml");
        reader.loadBeanDefinitions(resource);

        Field f = PetStoreService.class.getDeclaredField("accountDao");
        //抽象出一个DependencyDescriptor
        DependencyDescriptor descriptor = new DependencyDescriptor(f,true);

        Object o = defaultBeanFactory.resolveDependency(descriptor);
        Assert.assertTrue(o instanceof AccountDao);
    }
}
