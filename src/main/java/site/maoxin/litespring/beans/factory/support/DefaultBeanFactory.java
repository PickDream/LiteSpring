package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.config.ConfigurableBeanFactory;
import site.maoxin.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//解析XML文件
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory,BeanDefinitionRegistry {

    private ClassLoader classLoader;

    //使用线程安全的Map
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    /**
     * 外部注册XMLBeanDefinitionReader开放的接口
     * */
    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanId,bd);
    }

    /**
     * 通过beanId来生成对象
     * */
    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);
        if (bd==null)
            throw new BeanCreationException("Bean Definition does not exist");
        if (bd.isSignleton()){
            Object bean = this.getSignelton(beanId);
            if (bean==null){
                bean = createBean(bd);
                this.registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd){
        ClassLoader cl = this.getClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
            throw new BeanCreationException("create bean for "+beanClassName+" failed",e);
        }
    }
    @Override
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getClassLoader() {
        if (classLoader==null){
            classLoader = ClassUtils.getDefaultClassLoader();
        }
        return classLoader;
    }
}
