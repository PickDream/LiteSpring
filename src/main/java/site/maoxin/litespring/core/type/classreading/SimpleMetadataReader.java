package site.maoxin.litespring.core.type.classreading;

import org.springframework.asm.ClassReader;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.core.type.AnnotationMetadata;
import site.maoxin.litespring.core.type.ClassMetadata;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Maoxin
 * @ClassName SimpleMetadataReader
 * @date 3/30/2019
 */
public class SimpleMetadataReader implements MetadataReader {

    private final Resource resource;

    private final ClassMetadata classMetadata;

    private final AnnotationMetadata annotationMetadata;

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }


    /**
     * 封装ASM的处理，只返回暴露好的接口
     * */
    public SimpleMetadataReader(Resource resource) throws IOException {

        InputStream is = new BufferedInputStream(resource.getInputStream());
        ClassReader classReader;

        try {
            classReader = new ClassReader(is);
        }
        finally {
            is.close();
        }

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        this.annotationMetadata = visitor;
        this.classMetadata = visitor;
        this.resource = resource;
    }
}
