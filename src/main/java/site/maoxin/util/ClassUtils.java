package site.maoxin.util;

public abstract class ClassUtils {
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
}
