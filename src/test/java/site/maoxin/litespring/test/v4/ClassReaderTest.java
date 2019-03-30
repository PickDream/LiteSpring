package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;
import site.maoxin.litespring.core.annotation.AnnotationAttributes;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.type.classreading.AnnotationMetadataReadingVisitor;
import site.maoxin.litespring.core.type.classreading.ClassMetadataReadingVisitor;

import java.io.IOException;

/**
 * @author Maoxin
 * @ClassName ClassReaderTest
 * @date 3/27/2019
 */
public class ClassReaderTest {
    @Test
    public void testGetClasMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("site/maoxin/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();

        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        Assert.assertFalse(visitor.isAbstract());
        Assert.assertFalse(visitor.isInterface());
        Assert.assertFalse(visitor.isFinal());
        Assert.assertEquals("site.maoxin.litespring.service.v4.PetStoreService", visitor.getClassName());
        Assert.assertEquals("java.lang.Object", visitor.getSuperClassName());
        Assert.assertEquals(0, visitor.getInterfaceNames().length);
    }

    @Test
    public void testGetAnnonation() throws Exception{
        ClassPathResource resource = new ClassPathResource("site/maoxin/litespring/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        //ClassReader.SKIP_DEBUG：表示不遍历调试内容，即跳过源文件，源码调试扩展，局部变量表，局部变量类型表和行号表属性，即以下方法既不会被解析也不会被访问
        reader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "site.maoxin.litespring.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("petStore", attributes.get("value"));

    }
}
