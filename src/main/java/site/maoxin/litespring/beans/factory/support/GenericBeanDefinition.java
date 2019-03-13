package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;


/**
 * BeanDefinition通用实现类
 * */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;

    public GenericBeanDefinition(String id,String beanClassName){
        this.id = id;
        this.beanClassName = beanClassName;
    }
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public boolean isSignleton() {
        return singleton;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public boolean isPrototype() {
        return prototype;
    }

    @Override
    public void setScope(String scope) {
        scope = scope==null?"":scope;
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope)||SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
