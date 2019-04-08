# LiteSpring

`liteSpring` 是按照码农翻身公众号刘欣老师的资料学习完成的项目,旨在通过写一个类似Spring框架的过程去理解Spring本身以及加深对设计模式的理解.

liteSpring 主要完成如下主要模块

+ ApplicationContext
    + 将读取XML文件与`BeanFactory`解耦
    + 分离资源接口
+ Setter注入
    + 类型转换功能的设计与实现
+ 构造器注入
    + 寻找合适的构造器
+ Spring相关注解
    + 利用`asm`去完成对注解的解析
    + 实现对带有`@Autowire`注解的对象进行注入
    + 完成对Bean生命周期HOOK功能的设计
+ Spring AOP的实现
    + 学习实现中

这个学习项目包含了一些笔记资料，在[Wiki](https://github.com/PickDream/LiteSpring/wiki)中进行查阅

笔记资料在完成学习后统一更新


