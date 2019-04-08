package site.maoxin.litespring.beans.factory.annotation;

import site.maoxin.litespring.beans.factory.config.AutowireCapableBeanFactory;

import java.lang.reflect.Member;

/**
 * @author Maoxin
 * @ClassName InjectionElement
 * @date 4/8/2019
 */

/**
 * 抽象类，完成字段以及构造函数的自动装配过程
 * */
public abstract class InjectionElement {

    //这里使用了Member
    protected Member member;

    protected AutowireCapableBeanFactory factory;

    InjectionElement(Member member,AutowireCapableBeanFactory factory){
        this.member = member;
        this.factory = factory;
    }

    public abstract void inject(Object target);
}
