package site.maoxin.litespring.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @author Maoxin
 * @ClassName AnnotationUtils
 * @date 4/8/2019
 */
public class AnnotationUtils {
    /**
     * 传入待处理的可以被注解修饰的反射元信息AnnotatedElement，传入希望获取的注解类型
     * */
    public static <T extends Annotation> T getAnnotation(AnnotatedElement ae, Class<T> annotationType){
        T ann = ae.getAnnotation(annotationType);
        //如果ann是null，就尝试获取类上的注解
        if (ann == null) {
            for (Annotation metaAnn : ae.getAnnotations()) {
                ann = metaAnn.annotationType().getAnnotation(annotationType);
                if (ann != null) {
                    break;
                }
            }
        }
        return ann;
    }
}
