package site.maoxin.litespring.tx;

import site.maoxin.litespring.util.MessageTracker;

/**
 * @author Maoxin
 * @ClassName TransactionManager
 * @date 4/9/2019
 */
public class TransactionManager {
    public void start(){
        System.out.println("start tx");
        MessageTracker.addMsg("start tx");
    }
    public void commit(){
        System.out.println("commit tx");
        MessageTracker.addMsg("commit tx");
    }
    public void rollback(){
        System.out.println("rollback tx");
        MessageTracker.addMsg("rollback tx");
    }
}
