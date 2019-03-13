package site.maoxin.litespring.context.support;

import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.FileSystemResource;
import site.maoxin.litespring.core.io.Resource;

/**
 * @author
 * @ClassName FileSystemXmlApplicationContext
 * @date 3/11/2019
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext{

    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResource(String path) {
        return new FileSystemResource(path);
    }
}
