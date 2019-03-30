package site.maoxin.litespring.service.v4;

import site.maoxin.litespring.beans.factory.annotation.Autowired;
import site.maoxin.litespring.dao.v4.AccountDao;
import site.maoxin.litespring.dao.v4.ItemDao;
import site.maoxin.litespring.stereotype.Component;

/**
 * @author Maoxin
 * @ClassName PetStoreService
 * @date 3/25/2019
 */

@Component("petStore")
public class PetStoreService {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }}
