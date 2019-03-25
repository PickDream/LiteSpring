package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.ConstructorArgument;
import site.maoxin.litespring.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;


/**
 * BeanDefinition通用实现类
 * */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;
    private String beanClassName;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    private List<PropertyValue> propertyValues = new ArrayList<>();
    private ConstructorArgument constructorArgument = new ConstructorArgument();

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

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !constructorArgument.isEmpty();
    }


}
