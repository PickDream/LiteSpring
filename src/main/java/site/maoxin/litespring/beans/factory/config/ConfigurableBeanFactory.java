package site.maoxin.litespring.beans.factory.config;

import site.maoxin.litespring.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {

    void setClassLoader(ClassLoader classLoader);

    ClassLoader getClassLoader();
}
