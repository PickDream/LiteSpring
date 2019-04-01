package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.context.annotation.ScannedGenericBeanDefinition;
import site.maoxin.litespring.core.annotation.AnnotationAttributes;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.core.type.AnnotationMetadata;
import site.maoxin.litespring.stereotype.Component;

/**
 * @author Maoxin
 * @ClassName XMLBeanDefinationReaderTest
 * @date 4/1/2019
 */
public class XMLBeanDefinationReaderTest {
    @Test
    public void testParseScanedBean(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("store-v4.xml");
        reader.loadBeanDefinitions(resource);
        String annotation = Component.class.getName();
        //下面的代码和ClassPathBeanDefinitionScannerTest重复，该怎么处理？
        {
            BeanDefinition bd = factory.getBeanDefinition("petStore");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();


            Assert.assertTrue(amd.hasAnnotation(annotation));
            AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStore", attributes.get("value"));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("accountDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
        {
            BeanDefinition bd = factory.getBeanDefinition("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
