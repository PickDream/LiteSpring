package site.maoxin.litespring.beans.factory;

import site.maoxin.litespring.beans.*;

public interface BeanFactory {

    BeanDefinition getBeanDefinition(String petStore);

    Object getBean(String petStore);
}
