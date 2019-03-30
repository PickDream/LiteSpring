package site.maoxin.litespring.core.io.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import site.maoxin.litespring.core.io.FileSystemResource;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.util.Assert;
import site.maoxin.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Maoxin
 * @ClassName PackageResourceLoader
 * @date 3/25/2019
 */
public class PackageResourceLoader {

    private static final Log logger = LogFactory.getLog(PackageResourceLoader.class);

    private ClassLoader classLoader;

    public PackageResourceLoader() {
        this.classLoader = ClassUtils.getDefaultClassLoader();
    }

    public PackageResourceLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Resource[] getResources(String basepackage) throws IOException{
        Assert.notNull(basepackage, "basePackage  must not be null");
        String location = ClassUtils.convertClassNameToResourcePath(basepackage);
        ClassLoader cl = getClassLoader();
        URL url = cl.getResource(location);
        File rootDir = new File(url.getFile());
        //递归读取包对应的文件系统下的资源
        Set<File> matchingFiles =retrieveMatchingFiles(rootDir);
        Resource[] result = new FileSystemResource[matchingFiles.size()];
        int i = 0;
        for (File file:matchingFiles){
            result[i++] = new FileSystemResource(file);
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir) throws IOException{
        //1. 判断根目录/文件是否存在,如果不存在就直接返回EmptySet
        if (!rootDir.exists()){
            // Silently skip non-existing directories.
            if (logger.isDebugEnabled()) {
                logger.debug("Skipping [" + rootDir.getAbsolutePath() + "] because it does not exist");
            }
            return Collections.emptySet();
        }
        //2. 该函数是为包扫描，排除为文件的可能性
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.
            if (logger.isWarnEnabled()) {
                logger.warn("Skipping [" + rootDir.getAbsolutePath() + "] because it does not denote a directory");
            }
            return Collections.emptySet();
        }
        //3. 判断该文件夹是否是可以读取的
        if (!rootDir.canRead()) {
            if (logger.isWarnEnabled()) {
                logger.warn("Cannot search for matching files underneath directory [" + rootDir.getAbsolutePath() +
                        "] because the application is not allowed to read the directory");
            }
            return Collections.emptySet();
        }
        //创建收纳递归获取文件的Set
        Set<File> result = new LinkedHashSet<File>(8);
        doRetrieveMatchingFiles(rootDir, result);
        return result;
    }
    protected void doRetrieveMatchingFiles(File dir, Set<File> result) throws IOException {
        File[] fileList = dir.listFiles();
        //递归终止条件
        if (fileList == null){
            if (logger.isWarnEnabled()) {
                logger.warn("Could not retrieve contents of directory [" + dir.getAbsolutePath() + "]");
            }
            return;
        }
        for (File content:fileList){
            if (content.isDirectory() ) {
                if (!content.canRead()) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Skipping subdirectory [" + dir.getAbsolutePath() +
                                "] because the application is not allowed to read the directory");
                    }
                }
                else {
                    doRetrieveMatchingFiles(content, result);
                }
            } else{
                result.add(content);
            }
        }
    }
}
