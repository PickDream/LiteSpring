package site.maoxin.litespring.beans.factory;

import site.maoxin.litespring.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg){
        super(msg);
    }
    public BeanDefinitionStoreException(String msg,Throwable cause) {
        super(msg,cause);
    }
}
