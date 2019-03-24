package site.maoxin.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.PropertyValue;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.config.RuntimeBeanReference;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.core.io.ClassPathResource;

import java.util.List;

/**
 *
 * 解析Bean中定了的Properties的对象
 * @author Maoxin
 */
public class BeanDefinitionTestV2 {

    @Test
    public void testGetBeanDefinitions(){
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinitions(new ClassPathResource("store-v2.xml"));

        BeanDefinition bd = factory.getBeanDefinition("petStore");

        List<PropertyValue> pvs = bd.getPropertyValues();

        Assert.assertTrue(pvs.size() == 4);
        {
            PropertyValue pv = this.getPropertyValue("accountDao", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }

        {
            PropertyValue pv = this.getPropertyValue("itemDao", pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);
        }
    }

    private PropertyValue getPropertyValue(String name,List<PropertyValue> pvs){
        for(PropertyValue pv : pvs){
            if(pv.getName().equals(name)){
                return pv;
            }
        }
        return null;
    }
}
