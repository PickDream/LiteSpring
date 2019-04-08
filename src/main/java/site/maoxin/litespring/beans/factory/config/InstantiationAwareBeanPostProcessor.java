package site.maoxin.litespring.beans.factory.config;

import site.maoxin.litespring.beans.BeansException;

/**
 * @author Maoxin
 * @ClassName InstantiationAwareBeanPostProcessor
 * @date 4/8/2019
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    boolean afterInstantiation(Object bean, String beanName) throws BeansException;

    void postProcessPropertyValues(Object bean, String beanName)
            throws BeansException;
}
