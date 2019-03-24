package site.maoxin.litespring.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import site.maoxin.litespring.beans.propertyeditors.CustomBooleanEditor;

/**
 * @author Maoxin
 * @ClassName V2AllTestSuit
 * @date 3/24/2019
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class
})
public class V2AllTestSuit {
}
