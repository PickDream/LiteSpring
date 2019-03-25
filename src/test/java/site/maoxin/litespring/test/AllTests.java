package site.maoxin.litespring.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import site.maoxin.litespring.test.v1.BeanFactoryTest;
import site.maoxin.litespring.test.v1.V1AllTestSuit;
import site.maoxin.litespring.test.v2.V2AllTestSuit;
import site.maoxin.litespring.test.v3.V3AllTestSuit;

/**
 * @author Maoxin
 * @ClassName AllTests
 * @date 3/25/2019
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTestSuit.class,
        V2AllTestSuit.class,
        V3AllTestSuit.class
})
public class AllTests {
}
