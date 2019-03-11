package site.maoxin.litespring.context.support;

import site.maoxin.litespring.beans.factory.BeanFactory;
import site.maoxin.litespring.beans.factory.support.BeanDefinitionRegistry;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.core.io.FileSystemResource;
import site.maoxin.litespring.core.io.Resource;

/**
 * @author
 * @ClassName FileSystemXmlApplicationContext
 * @date 3/11/2019
 */
public class FileSystemXmlApplicationContext implements ApplicationContext {

    private BeanFactory factory;
    public FileSystemXmlApplicationContext(String filePath) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
        Resource resource = new FileSystemResource(filePath);
        reader.loadBeanDefinitions(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return this.factory.getBean(beanId);
    }
}
