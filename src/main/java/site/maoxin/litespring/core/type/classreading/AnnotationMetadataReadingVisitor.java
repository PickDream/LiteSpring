package site.maoxin.litespring.core.type.classreading;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;
import site.maoxin.litespring.core.annotation.AnnotationAttributes;
import site.maoxin.litespring.core.type.AnnotationMetadata;
import site.maoxin.litespring.core.type.ClassMetadata;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * 该Visitor 扩展自ClassMetadataReadingVisitor,并且增加了对注解信息的获取
 * @author Maoxin
 * @ClassName AnnotationAttributesReadingVisitor
 * @date 3/30/2019
 */
public final class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    private final Set<String> annotationSet = new LinkedHashSet<String>(4);

    private final Map<String, AnnotationAttributes> attributeMap = new LinkedHashMap<>(4);

    public AnnotationMetadataReadingVisitor() {

    }

    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributeMap.get(annotationType);
    }

    /**
     * 第一个参数：表示的是，注解的类型。它使用的是（“L” + “类型路径” + “;”）形式表述。
     *
     * 第二个参数：表示的是，该注解是否在 JVM 中可见。这个参数的具体含义可以理解为：如果为 true 表示虚拟机可见，我们可以通过下面这样的代码获取到注解类型：
     *
     * */
    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        String className = Type.getType(desc).getClassName();
        this.annotationSet.add(className);
        //读取注解的详细的信息
        return new AnnotationAttributesReadingVisitor(className, this.attributeMap);
    }
}
