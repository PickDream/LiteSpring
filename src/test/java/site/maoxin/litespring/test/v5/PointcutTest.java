package site.maoxin.litespring.test.v5;

/**
 * @author Maoxin
 * @ClassName PointcutTest
 * @date 4/9/2019
 */

/**
 *  这个部分最重要的就是对PointCut表达式的抽象
 *  如何抽象? 首先对于expression 而言就是Method是否match一些Method,
 * */
public class PointcutTest {
    public void testPointcut(){
        String expression = "execution(* site.maoxin.litespring.service.v5.*.placeOrder(..))";

    }
}
