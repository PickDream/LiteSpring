package site.maoxin.litespring.beans.factory.config;

import site.maoxin.litespring.beans.BeansException;

/**
 * @author Maoxin
 * @ClassName BeanPostProcessor
 * @date 4/8/2019
 */
public interface BeanPostProcessor {
    Object beforeInitialization(Object bean, String beanName) throws BeansException;


    Object afterInitialization(Object bean, String beanName) throws BeansException;
}
