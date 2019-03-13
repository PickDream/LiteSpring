package site.maoxin.litespring.context.support;

import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;

/**
 * @author maoxin
 * @date 3/11/2019
 */
public class ClassPathXMLApplicationContext extends AbstractApplicationContext {

    public ClassPathXMLApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResource(String path) {
        return new ClassPathResource(path,getClassLoader());
    }
}
