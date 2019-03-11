package site.maoxin.litespring.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author
 * @date 3/11/2019
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

    String getDescription();
}
