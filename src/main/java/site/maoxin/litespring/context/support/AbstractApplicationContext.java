package site.maoxin.litespring.context.support;

import site.maoxin.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import site.maoxin.litespring.beans.factory.config.ConfigurableBeanFactory;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.core.io.Resource;

/**
 *
 * @author Maoxin
 * @date 3/12/2019
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    public AbstractApplicationContext(String configFile){
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResource(configFile);
        reader.loadBeanDefinitions(resource);
        factory.setClassLoader(this.getClassLoader());
        registerBeanPostProcessor(factory);
    }
    public Object getBean(String beanId){
        return factory.getBean(beanId);
    }
    protected abstract Resource getResource(String path);

    public void setClassLoader(ClassLoader classLoader) {
        this.factory.setClassLoader(classLoader);
    }

    public ClassLoader getClassLoader() {
        return this.factory.getClassLoader();
    }

    protected void registerBeanPostProcessor(ConfigurableBeanFactory factory){

        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
        processor.setBeanFactory(factory);
        factory.addBeanPostProcessor(processor);
    }
}
