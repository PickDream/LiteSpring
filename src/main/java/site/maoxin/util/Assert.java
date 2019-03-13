package site.maoxin.util;

/**
 * @author Maoxin
 * @date 3/12/2019
 */
public class Assert {
    public static void notNull(Object object,String message){
        if (object==null){
            throw new IllegalArgumentException(message);
        }
    }
}
