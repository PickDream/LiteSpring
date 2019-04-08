package site.maoxin.litespring.beans.factory.annotation;

/**
 * @author Maoxin
 * @ClassName AutowiredAnnotationProcessor
 * @date 4/8/2019
 */

import site.maoxin.litespring.beans.BeansException;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.config.AutowireCapableBeanFactory;
import site.maoxin.litespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import site.maoxin.litespring.core.annotation.AnnotationUtils;
import site.maoxin.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * AutowiredAnnotationProcessor类可以将一个
 * Class封装为InjectionMetaData,其需要传入一个Factory作为Inject的来源
 *
 * 调用InjectionMetadata的inject方法，就可以完成injection
 *
 * */
public class AutowiredAnnotationProcessor implements InstantiationAwareBeanPostProcessor {
    private AutowireCapableBeanFactory beanFactory;
    private String requiredParameterName = "required";
    private boolean requiredParameterValue = true;

    //记录可以自动装配的的注解类型
    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>();

    public AutowiredAnnotationProcessor(){
        autowiredAnnotationTypes.add(Autowired.class);
    }

    public void setBeanFactory(AutowireCapableBeanFactory beanFactory){
        this.beanFactory = beanFactory;
    }

    public InjectionMetadata buildAutowiringMetadata(Class<?> clazz){

        LinkedList<InjectionElement> elements = new LinkedList<>();
        Class<?> targetClass = clazz;
        //
        do {
            LinkedList<InjectionElement> currElement = new LinkedList<>();
            //处理字段注入
            for (Field field:targetClass.getDeclaredFields()){
                //判断field是否包含@AutoWire注解
                Annotation ann = findAutowiredAnnotation(field);
                //判断是否为空
                if (ann!=null){
                    //排除静态
                    if (Modifier.isStatic(field.getModifiers()))
                        continue;
                    boolean required = determineRequiredStatus(ann);
                    currElement.add(new AutowiredFieldElement(field, required,beanFactory));
                }
            }
            //处理方法注入
            for (Method method : targetClass.getDeclaredMethods()) {
                //TODO 处理方法注入
            }
            elements.addAll(0,currElement);
            //切换为父类
            targetClass = targetClass.getSuperclass();
        }while (targetClass!=null&&targetClass != Object.class);
        return new InjectionMetadata(clazz,elements);
    }

    /**
     * 获取指定的自动装配注解的Annotation信息
     * */
    private Annotation findAutowiredAnnotation(AccessibleObject ao) {

        for (Class<? extends Annotation> type : this.autowiredAnnotationTypes) {
            //AnnotationUtils方法主要是能够方便获取注解信息
            Annotation ann = AnnotationUtils.getAnnotation(ao, type);
            if (ann != null) {
                return ann;
            }
        }
        return null;
    }

    /**
     *  判断RequiredStatus状态
     *  这个需要注意的是AutoWire的定义，其需要拿到该方法，并且调用来获得required的值
     *  public @interface Autowired {
     *     boolean required() default true;
     * }
     * */
    protected boolean determineRequiredStatus(Annotation ann) {
        try {
            Method method = ReflectionUtils.findMethod(ann.annotationType(), this.requiredParameterName);
            if (method == null) {
                // Annotations like @Inject and @Value don't have a method (attribute) named "required"
                // -> default to required status
                return true;
            }
            return (this.requiredParameterValue == (Boolean) ReflectionUtils.invokeMethod(method, ann));
        }
        catch (Exception ex) {
            // An exception was thrown during reflective invocation of the required attribute
            // -> default to required status
            return true;
        }
    }

    @Override
    public Object beforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    @Override
    public boolean afterInstantiation(Object bean, String beanName) throws BeansException {
        return false;
    }

    /**
     * 指向postProcessPropertyValues时候对bean的初始化，
     * 传入的Bean是未经过初始化的，通过该类进行初始化
     * */
    @Override
    public void postProcessPropertyValues(Object bean, String beanName) throws BeansException {
        InjectionMetadata metadata = buildAutowiringMetadata(bean.getClass());
        try {
            metadata.inject(bean);
        }
        catch (Throwable ex) {
            throw new BeanCreationException(beanName, "Injection of autowired dependencies failed", ex);
        }
    }

    @Override
    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object afterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}
