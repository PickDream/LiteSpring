package site.maoxin.litespring.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import site.maoxin.litespring.context.ApplicationContext;
import site.maoxin.litespring.test.v1.ApplicationContextTest;

/**
 * @author Maoxin
 * @ClassName V3AllTests
 * @date 3/25/2019
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV3.class,
        BeanDefinitionTestV3.class,
        ConstructorResolverTest.class
})
public class V3AllTestSuit {
}
