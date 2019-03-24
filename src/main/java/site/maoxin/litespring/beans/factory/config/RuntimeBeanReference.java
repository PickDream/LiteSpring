package site.maoxin.litespring.beans.factory.config;

/**
 * @author
 * @ClassName RuntimeBeanReference
 * @date 3/16/2019
 */
public class RuntimeBeanReference {
    private final String beanName;
    public RuntimeBeanReference(String beanName){
        this.beanName = beanName;
    }
    public String getBeanName(){
        return beanName;
    }
}
