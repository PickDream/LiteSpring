package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.core.annotation.AnnotationAttributes;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.type.AnnotationMetadata;
import site.maoxin.litespring.core.type.classreading.MetadataReader;
import site.maoxin.litespring.core.type.classreading.SimpleMetadataReader;
import site.maoxin.litespring.stereotype.Component;

import java.io.IOException;

/**
 * MetadataReader 主要是为了向下屏蔽ASM的细节实现
 * @author Maoxin
 * @ClassName MetadataReaderTest
 * @date 3/30/2019
 */

public class MetadataReaderTest {
    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("site/maoxin/litespring/service/v4/PetStoreService.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
        //注意：不需要单独使用ClassMetadata
        //ClassMetadata clzMetadata = reader.getClassMetadata();
        AnnotationMetadata amd = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(amd.hasAnnotation(annotation));
        AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        //注：下面对class metadata的测试并不充分
        Assert.assertFalse(amd.isAbstract());
        Assert.assertFalse(amd.isFinal());
        Assert.assertEquals("site.maoxin.litespring.service.v4.PetStoreService", amd.getClassName());

    }
}
