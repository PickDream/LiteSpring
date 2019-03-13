package site.maoxin.litespring.beans.factory.config;

/**
 * @author Maoxin
 * @ClassName SingletonBeanRegistry
 * @date 3/13/2019
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName,Object bean);
    Object getSignelton(String beanName);
}
