package site.maoxin.litespring.beans.factory;

import site.maoxin.litespring.beans.BeansException;

/**
 * 创建Bean时出现的异常
 * */
public class BeanCreationException extends BeansException {

    public BeanCreationException(String msg){
        super(msg);
    }

    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
