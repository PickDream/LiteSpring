package site.maoxin.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.SimpleTypeConverter;
import site.maoxin.litespring.beans.TypeConverter;
import site.maoxin.litespring.beans.TypeMismatchException;

import static org.junit.Assert.fail;

/**
 * @author Maoxin
 * @ClassName TypeConverterTest
 * @date 3/24/2019
 */
public class TypeConverterTest {
    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3,i.intValue());

        try{
            converter.convertIfNecessary("3.1", Integer.class);
        }catch(TypeMismatchException e){
            return;
        }
        fail();
    }
    @Test
    public void testConvertStringToBoolean(){
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertEquals(true,b.booleanValue());

        try{
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        }catch(TypeMismatchException e){
            return;
        }
        fail();
    }
}
