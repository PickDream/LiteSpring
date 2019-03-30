package site.maoxin.litespring.context.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.BeanDefinitionStoreException;
import site.maoxin.litespring.beans.factory.support.BeanDefinitionRegistry;
import site.maoxin.litespring.beans.factory.support.BeanNameGenerator;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.core.io.support.PackageResourceLoader;
import site.maoxin.litespring.core.type.classreading.MetadataReader;
import site.maoxin.litespring.core.type.classreading.SimpleMetadataReader;
import site.maoxin.litespring.stereotype.Component;
import site.maoxin.util.StringUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 用来读取包扫描路径下的注解并为Factory提供相关的信息
 * @author Maoxin
 * @ClassName ClassPathBeanDefinitionScanner
 * @date 3/30/2019
 */
public class ClassPathBeanDefinitionScanner {
    //构造函数中传入的register
    private final BeanDefinitionRegistry registry;

    private PackageResourceLoader resourceLoader = new PackageResourceLoader();

    protected final Log logger = LogFactory.getLog(getClass());

    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();


    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public void doScan(String packages){
        //1.解析packages中的字符串转换为数组
        //2.循环解析数据获取对应的Resource[]
        //3.对Resource进行解析,得到对应的AnnotationMetadata
        //4.注册到register中去

        String[] basePackages = StringUtils.tokenizeToStringArray(packages,",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage:basePackages){
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate:candidates){
                beanDefinitions.add(candidate);
                registry.registerBeanDefinition(candidate.getId(),candidate);
            }
        }

    }

    public Set<BeanDefinition> findCandidateComponents(String basePackage) {

        Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
        try {

            Resource[] resources = this.resourceLoader.getResources(basePackage);

            for (Resource resource : resources) {
                try {

                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    //只有在标有@Component的注解上
                    if(metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())){
                        ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
                        sbd.setId(beanName);
                        candidates.add(sbd);
                    }
                }
                catch (Throwable ex) {
                    throw new BeanDefinitionStoreException(
                            "Failed to read candidate component class: " + resource, ex);
                }

            }
        }
        catch (IOException ex) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
        }
        return candidates;
    }


}
