package site.maoxin.litespring.beans.factory.config;

import site.maoxin.util.Assert;

import java.lang.reflect.Field;

/**
 * @author Maoxin
 * @ClassName DependencyDescriptor
 * @date 4/8/2019
 */
public class DependencyDescriptor {

    private Field field;
    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        Assert.notNull(field, "Field must not be null");
        this.field = field;
        this.required = required;

    }
    //返回要注入类型对应的Class类型
    public Class<?> getDependencyType(){
        if(this.field != null){
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }

    public boolean isRequired() {
        return this.required;
    }
}
