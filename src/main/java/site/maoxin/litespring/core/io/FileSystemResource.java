package site.maoxin.litespring.core.io;

import site.maoxin.util.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Maoxin
 * @ClassName FileSystemResource
 * @date 3/11/2019
 */
public class FileSystemResource implements Resource {

    private String path;
    private File file;

    public FileSystemResource(String filePath){
        Assert.notNull(filePath,"传入路径不能为空!");
        this.path = filePath;
        this.file = new File(filePath);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "file ["+this.file.getAbsolutePath()+"]";
    }
}
