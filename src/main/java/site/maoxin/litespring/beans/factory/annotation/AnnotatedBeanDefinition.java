package site.maoxin.litespring.beans.factory.annotation;

import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.core.type.AnnotationMetadata;

/**
 *
 * 扩展自传统的读取方式
 * @author Maoxin
 * @ClassName AnnotatedBeanDefinition
 * @date 3/30/2019
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
