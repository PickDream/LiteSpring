package site.maoxin.litespring.service.v3;

/**
 * @author
 * @ClassName PetStoreService
 * @date 3/25/2019
 */
public class PetStoreService {
    private AccountDao accountDao;
    private int version;

    private ItemDao itemDao;
    public PetStoreService(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.version = version;
        this.itemDao = itemDao;
    }

    public PetStoreService(AccountDao accountDao,ItemDao itemDao){
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = -1;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
