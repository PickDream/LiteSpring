package site.maoxin.litespring.service.v5;

import site.maoxin.litespring.beans.factory.annotation.Autowired;
import site.maoxin.litespring.dao.v5.AccountDao;
import site.maoxin.litespring.dao.v5.ItemDao;
import site.maoxin.litespring.stereotype.Component;
import site.maoxin.litespring.util.MessageTracker;

/**
 * @author Maoxin
 * @ClassName PetStoreService
 * @date 4/9/2019
 */

@Component("petStore")
public class PetStoreService {
    @Autowired
    AccountDao accountDao;
    @Autowired
    ItemDao itemDao;

    public PetStoreService() {

    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void placeOrder(){
        System.out.println("place order");
        MessageTracker.addMsg("place order");

    }
}
