package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.factory.config.RuntimeBeanReference;
import site.maoxin.litespring.beans.factory.config.TypedStringValue;

/**
 *
 * 该类的主要作用就是传入Property属性对应的类
 * @author Maoxin
 * @date 3/18/2019
 */
public class BeanDefinitionValueResolver {

    //这里传入一个BeanFactory用其中的rgetBean方法来获取Bean
    private final DefaultBeanFactory beanFactory;

    //构造函数，来创建本类实例
    public BeanDefinitionValueResolver(DefaultBeanFactory beanFactory) {

        this.beanFactory = beanFactory;
    }
    /**
     * resolveValueIfNecessary,传入一个value值，判断该值是不是
     * 真的需要resolve,需要注意的是，这里传入应该是RuntimeBeanReference，
     * 或者是TypeedStringValue这样Properties属性的两个实现类，
     * 传入这两个属性对应的对象，返回真正需要的对象或者值
     * */
    public Object resolveValueIfNecessary(Object value) {

        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;
            String refName = ref.getBeanName();
            Object bean = this.beanFactory.getBean(refName);
            return bean;

        }else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else{
            //TODO
            throw new RuntimeException("the value " + value +" has not implemented");
        }
    }
}
