package site.maoxin.litespring.test.v4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import site.maoxin.litespring.core.type.classreading.MetadataReader;

/**
 * @author Maoxin
 * @ClassName V4AllTestSuit
 * @date 4/1/2019
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTest4.class,
        ClassPathBeanDefinitionScannerTest.class,
        ClassReaderTest.class,
        MetadataReaderTest.class,
        PackageResourceLoaderTest.class,
        XMLBeanDefinationReaderTest.class
})
public class V4AllTestSuit {
}
