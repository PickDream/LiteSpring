package site.maoxin.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.factory.config.RuntimeBeanReference;
import site.maoxin.litespring.beans.factory.config.TypedStringValue;
import site.maoxin.litespring.beans.factory.support.BeanDefinitionValueResolver;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.dao.v2.AccountDao;

/**
 * Resolver类的测试
 *
 * @author Maoxin
 */
public class BeanDefinitionValueResolverTest {

    @Test
    public void testResolveRuntimeBeanReference() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("store-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");

        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }
    @Test
    public void testResolveTypedStringValue() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("store-v2.xml"));

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);

        TypedStringValue stringValue = new TypedStringValue("test");
        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);

    }
}