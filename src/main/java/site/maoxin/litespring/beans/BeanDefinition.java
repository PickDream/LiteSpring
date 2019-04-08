package site.maoxin.litespring.beans;


import java.util.List;

/**
 * Bean对象基本接口
 * */
public interface BeanDefinition {
    String SCOPE_SINGLETON = "signalton";

    String SCOPE_PROTOTYPE = "prototype";

    String SCOPE_DEFAULT = "";

    String getBeanClassName();

    boolean isSignleton();

    String getScope();

    boolean isPrototype();

    void setScope(String scope);

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getId();

    boolean hasConstructorArgumentValues();

    void setBeanClassName(String beanClassName);

    Class<?> getBeanClass() throws IllegalStateException ;

    Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException;

    boolean hasBeanClass();
}
