package site.maoxin.litespring.beans.factory.annotation;

/**
 * @author Maoxin
 * @ClassName AutowiredFieldElement
 * @date 4/8/2019
 */

import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.config.AutowireCapableBeanFactory;
import site.maoxin.litespring.beans.factory.config.DependencyDescriptor;
import site.maoxin.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

/**
 * 对于字段注入的类,要想实现注入的话，首先需要知道当前字段是什么，对于字段，是否是必须的，以及Bean
 * Definition容器
 * */
public class AutowiredFieldElement extends InjectionElement {

    private boolean required;

    public AutowiredFieldElement(Field f, boolean required, AutowireCapableBeanFactory factory) {
        //将字段或者函数赋值给了Member这个接口对象（Java Refection API）
        super(f,factory);
        this.required = required;
    }

    public Field getField(){
        return (Field)this.member;
    }

    @Override
    public void inject(Object target) {
        Field field = getField();
        try {
            DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field,required);
            Object value = factory.resolveDependency(dependencyDescriptor);
            if (value!=null){
                //不知道其可见性
                ReflectionUtils.makeAccessible(field);
                field.set(target, value);
            }
        }catch (Throwable ex){
            throw new BeanCreationException("Could not autowire field: " + field, ex);
        }
    }
}
