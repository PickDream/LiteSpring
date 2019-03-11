package site.maoxin.litespring.context.support;

import site.maoxin.litespring.beans.factory.BeanFactory;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;

/**
 * @author maoxin
 * @date 3/11/2019
 */
public class ClassPathXMLApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;
    public ClassPathXMLApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
