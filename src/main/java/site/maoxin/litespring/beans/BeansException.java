package site.maoxin.litespring.beans;

//Bean创建的根异常类，其子异常类分别定义了两个阶段的的异常，一是从XML读取类的信息的时候，而是在创建对象的时候
public class BeansException extends RuntimeException{

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg,Throwable cause){
        super(msg,cause);
    }
}
