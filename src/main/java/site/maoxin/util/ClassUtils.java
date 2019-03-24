package site.maoxin.util;

import java.util.HashMap;
import java.util.Map;

public abstract class ClassUtils {
    /**
     * 包装类型到基本类型的映射
     * Map with primitive wrapper type as key and corresponding primitive
     * type as value, for example: Integer.class -> int.class.
     */
    private static final Map<Class<?>, Class<?>> wrapperToPrimitiveTypeMap = new HashMap<Class<?>, Class<?>>(8);

    /**
     * 基本类型到包装类型的映射
     * Map with primitive type as key and corresponding wrapper
     * type as value, for example: int.class -> Integer.class.
     */
    private static final Map<Class<?>, Class<?>> primitiveTypeToWrapperMap = new HashMap<Class<?>, Class<?>>(8);

    /**
     * 初始化基本类型到包装类型的关系
     * */
    static {
        wrapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
        wrapperToPrimitiveTypeMap.put(Byte.class, byte.class);
        wrapperToPrimitiveTypeMap.put(Character.class, char.class);
        wrapperToPrimitiveTypeMap.put(Double.class, double.class);
        wrapperToPrimitiveTypeMap.put(Float.class, float.class);
        wrapperToPrimitiveTypeMap.put(Integer.class, int.class);
        wrapperToPrimitiveTypeMap.put(Long.class, long.class);
        wrapperToPrimitiveTypeMap.put(Short.class, short.class);

        for (Map.Entry<Class<?>, Class<?>> entry : wrapperToPrimitiveTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());

        }

    }
    /**
     * 获取默认的类加载器
     * 先尝试去获取线程上下文类加载器
     * 接下来获取APPClassLoader
     **/
    public static ClassLoader getDefaultClassLoader(){

        ClassLoader cl = null;
        try {
            //得到当前进程的线程上下文类加载器
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex){

        }
        if (cl==null){
            //得到加载该类的加载器
            cl = ClassLoader.class.getClassLoader();

            if (cl==null){
                try{
                    //得到APPClassLoader
                    cl = ClassLoader.getSystemClassLoader();
                }catch (Throwable ex){

                }
            }

        }
        return cl;
    }
    /**
     *判断原来的value对象能否被转换为对应的type类型
     * */
    public static boolean isAssignableValue(Class<?> type, Object value) {
        Assert.notNull(type, "Type must not be null");
        return (value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive());
    }
    /**
     * 判断原来的value类型能否被转换为对应的type类型
     * @param lhsType 目标类型
     * @param rhsType 源类型
     *
     * */
    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        //传入参数的检查
        Assert.notNull(lhsType, "Left-hand side type must not be null");
        Assert.notNull(rhsType, "Right-hand side type must not be null");
        //首先判断lhsType派生出rhsType么？
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        //isPrimitive 判断是否为基本类型
        if (lhsType.isPrimitive()) {
            Class<?> resolvedPrimitive = wrapperToPrimitiveTypeMap.get(rhsType);
            if (resolvedPrimitive != null && lhsType.equals(resolvedPrimitive)) {
                return true;
            }
        }
        else {
            Class<?> resolvedWrapper = primitiveTypeToWrapperMap.get(rhsType);
            if (resolvedWrapper != null && lhsType.isAssignableFrom(resolvedWrapper)) {
                return true;
            }
        }
        return false;
    }
}
