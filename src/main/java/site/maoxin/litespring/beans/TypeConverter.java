package site.maoxin.litespring.beans;

/**
 * @author Maoxin
 * @ClassName TypeConverter
 * @date 3/24/2019
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object object,Class<T> requiredType) throws TypeMismatchException;
}
