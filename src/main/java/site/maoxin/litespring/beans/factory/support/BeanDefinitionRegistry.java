package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;

/**
 * 包含对BeanDefinition的注册于设置接口
 * */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId,BeanDefinition bd);
}
