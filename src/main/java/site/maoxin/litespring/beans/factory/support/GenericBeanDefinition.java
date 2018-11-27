package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;


/**
 * BeanDefinition通用实现类
 * */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;

    public GenericBeanDefinition(String id,String beanClassName){
        this.id = id;
        this.beanClassName = beanClassName;
    }
    public String getBeanClassName() {
        return this.beanClassName;
    }
}
