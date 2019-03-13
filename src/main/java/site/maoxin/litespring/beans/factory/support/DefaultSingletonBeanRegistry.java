package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.factory.config.SingletonBeanRegistry;
import site.maoxin.util.Assert;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Maoxin
 * @ClassName DefaultSingletonBeanRegistry
 * @date 3/13/2019
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> signeltonObjects = new ConcurrentHashMap<>(64);
    @Override
    public void registerSingleton(String beanName, Object bean) {
        Assert.notNull(beanName,"beanName must not be null!");
        Object object = signeltonObjects.get(beanName);
        if (Objects.nonNull(object)){
            throw new IllegalStateException("Could not register object["+signeltonObjects+
                    "] under bean name '"+beanName + "':there is already object[" +object +"]");
        }
        this.signeltonObjects.put(beanName,bean);
    }

    @Override
    public Object getSignelton(String beanName) {
        return this.signeltonObjects.get(beanName);
    }
}
