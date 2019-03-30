package site.maoxin.litespring.core.type.classreading;

import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;
import site.maoxin.litespring.core.annotation.AnnotationAttributes;
import site.maoxin.litespring.core.type.ClassMetadata;

import java.util.Map;

/**
 * @author Maoxin
 * @ClassName AnnotationAttributesReadingVisitor
 * @date 3/30/2019
 */
public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
    //代指整个注解,全类名
    private final String annotationType;

    private final Map<String, AnnotationAttributes> attributesMap;

    AnnotationAttributes attributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap){
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }


    /**
     * 获取注解属性名和属性值，将这些都加入到attribute，会调用多次
     * */
    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.attributes.put(attributeName, attributeValue);
    }

    @Override
    public void visitEnd() {
        this.attributesMap.put(annotationType,attributes);
    }
}
