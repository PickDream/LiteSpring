package site.maoxin.litespring.beans.factory.config;

/**
 * @author Maoxin
 * @ClassName AutowireCapableBeanFactory
 * @date 4/8/2019
 */

import site.maoxin.litespring.beans.factory.BeanFactory;

/**
 * 具有自动装配能力的Factory
 * 接口的内容是传入一个由@AutoWire修饰对象抽象出来的依赖描述符
 * */
public interface AutowireCapableBeanFactory extends BeanFactory {
    Object resolveDependency(DependencyDescriptor descriptor);
}
