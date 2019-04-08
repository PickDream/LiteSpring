package site.maoxin.litespring.beans.factory.config;

import site.maoxin.litespring.beans.factory.BeanFactory;

import java.util.List;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {

    void setClassLoader(ClassLoader classLoader);

    ClassLoader getClassLoader();

    //以下接口与populateBean的实现有关
    void addBeanPostProcessor(BeanPostProcessor postProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();


}
