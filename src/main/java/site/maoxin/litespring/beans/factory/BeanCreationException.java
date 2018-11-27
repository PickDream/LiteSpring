package site.maoxin.litespring.beans.factory;

import site.maoxin.litespring.beans.BeansException;

public class BeanCreationException extends BeansException {

    public BeanCreationException(String msg){
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
