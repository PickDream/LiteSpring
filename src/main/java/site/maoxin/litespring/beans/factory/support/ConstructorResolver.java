package site.maoxin.litespring.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.ConstructorArgument;
import site.maoxin.litespring.beans.SimpleTypeConverter;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * 用于在BeanFactory中获取Bean对象时自动对象的构造器注入的实现
 * @author Maoxin
 * @ClassName ConstructorResolver
 * @date 3/25/2019
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());


    private final ConfigurableBeanFactory beanFactory;



    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    public Object autowireConstructor(final BeanDefinition bd) {

        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;

        Class<?> beanClass = null;
        try {
            beanClass = this.beanFactory.getClassLoader().loadClass(bd.getBeanClassName());

        } catch (ClassNotFoundException e) {
            throw new BeanCreationException( bd.getId(), "Instantiation of bean failed, can't resolve class", e);
        }

        //“报考者” candidate 代指待匹配的构造函数
        Constructor<?>[] candidates = beanClass.getConstructors();

        //对Bean最终的类型精选解析
        BeanDefinitionValueResolver valueResolver =
                new BeanDefinitionValueResolver((DefaultBeanFactory)this.beanFactory);

        //获取保存构造器参数的实体对象
        ConstructorArgument cargs = bd.getConstructorArgument();
        //数据类型转换器
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        //对构造函数进行循环判断
        for(int i=0; i<candidates.length;i++){
            //获取构造器参数
            Class<?> [] parameterTypes = candidates[i].getParameterTypes();
            //如果参数的个数不相等的话直接跳出
            if(parameterTypes.length != cargs.getArgumentCount()){
                continue;
            }

            //argsToUse记录最终经过转换之后的要被使用的数组
            argsToUse = new Object[parameterTypes.length];

            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);

            if(result){
                constructorToUse = candidates[i];
                break;
            }

        }


        //找不到一个合适的构造函数
        if(constructorToUse == null){
            throw new BeanCreationException( bd.getId(), "can't find a apporiate constructor");
        }


        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException( bd.getId(), "can't find a create instance using "+constructorToUse);
        }


    }


    private boolean valuesMatchTypes(Class<?> [] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolver valueResolver,
                                     SimpleTypeConverter typeConverter ){

        //循环遍历构造函数的类型
        for(int i=0;i<parameterTypes.length;i++){
            //去除对应的valueHolder
            ConstructorArgument.ValueHolder valueHolder
                    = valueHolders.get(i);
            //获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();

            try{
                //获得真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary( originalValue);
                //如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
                //使用TypeConverter
                //如果转型失败，则抛出异常。说明这个构造函数不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功，记录下来
                argsToUse[i] = convertedValue;
            }catch(Exception e){
                logger.error(e);
                return false;
            }
        }
        return true;
    }

}
