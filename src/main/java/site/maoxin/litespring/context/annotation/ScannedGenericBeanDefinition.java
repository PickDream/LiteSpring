package site.maoxin.litespring.context.annotation;

import site.maoxin.litespring.beans.factory.annotation.AnnotatedBeanDefinition;
import site.maoxin.litespring.beans.factory.support.GenericBeanDefinition;
import site.maoxin.litespring.core.type.AnnotationMetadata;

/**
 * @author Maoxin
 * @ClassName ScannedGenericBeanDefinition
 * @date 3/30/2019
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;


    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();

        this.metadata = metadata;

        setBeanClassName(this.metadata.getClassName());
    }


    public final AnnotationMetadata getMetadata() {
        return this.metadata;
    }
}
