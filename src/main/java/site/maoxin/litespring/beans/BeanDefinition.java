package site.maoxin.litespring.beans;


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
}
