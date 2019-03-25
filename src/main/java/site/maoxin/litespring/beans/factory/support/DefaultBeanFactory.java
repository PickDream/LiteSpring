package site.maoxin.litespring.beans.factory.support;

import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.PropertyValue;
import site.maoxin.litespring.beans.SimpleTypeConverter;
import site.maoxin.litespring.beans.TypeConverter;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.config.ConfigurableBeanFactory;
import site.maoxin.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
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

    /**
     * 包含两个步骤，第一个步骤是创建类实例，第二个步骤是为创建的类的内部进行Setter注入
     * */
    private Object createBean(BeanDefinition bd){
        Object bean = instantiateBean(bd);
        populateBean(bean,bd);
        return bean;
    }

    private void populateBean(Object bean,BeanDefinition bd){
        /**
         * 首先获取PropertyValue的List
         * 判断其是否为空，如果为空就结束
         * 循环PropertyValue的值，通过该对象的name判断bean对象是否有对饮的setter方法,有就指向
         * 在此期间通过判断是ref引用还是value引用来判断执行，这个获取对象的操作可以抽离成Resolver
         * 已经被封装了
         * */
        /**
         * 之后更新,增加类型转换的功能
         * */
        List<PropertyValue> propertyValueList = bd.getPropertyValues();
        if (propertyValueList==null||propertyValueList.size()==0){
            return;
        }
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        TypeConverter typeConverter = new SimpleTypeConverter();
        try{
            for (PropertyValue pv:propertyValueList){
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue= resolver.resolveValueIfNecessary(originalValue);

                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd:pds){
                    if (pd.getName().equals(propertyName)){
                        Object convertedValue = typeConverter.convertIfNecessary(resolvedValue,pd.getPropertyType());
                        pv.setConvertedValue(convertedValue);
                        pd.getWriteMethod().invoke(bean,convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 创建的时候先看是否满足构造器注入
     * */
    private Object instantiateBean(BeanDefinition bd){
        if (bd.hasConstructorArgumentValues()){
            ConstructorResolver constructorResolver = new ConstructorResolver(this);
            return constructorResolver.autowireConstructor(bd);
        }
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
