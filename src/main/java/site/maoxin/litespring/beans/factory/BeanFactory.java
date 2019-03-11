package site.maoxin.litespring.beans.factory;

import site.maoxin.litespring.beans.*;

/**
 * Bean工厂的接口类
 * */
public interface BeanFactory {

    Object getBean(String beanId);
}
