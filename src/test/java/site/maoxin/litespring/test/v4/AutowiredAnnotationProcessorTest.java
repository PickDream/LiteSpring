package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import site.maoxin.litespring.beans.factory.annotation.AutowiredFieldElement;
import site.maoxin.litespring.beans.factory.annotation.InjectionElement;
import site.maoxin.litespring.beans.factory.annotation.InjectionMetadata;
import site.maoxin.litespring.beans.factory.config.DependencyDescriptor;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.dao.v4.AccountDao;
import site.maoxin.litespring.dao.v4.ItemDao;
import site.maoxin.litespring.service.v4.PetStoreService;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author Maoxin
 * @ClassName AutowiredAnnotationProcessorTest
 * @date 4/8/2019
 */


/**
 * AutowiredAnnotationProcessor的目标是对Inject过程的封装，只需要传入一个类的Class,
 * 执行如下操作
 * 1. 根据该类的AutoWire信息生产List
 * 2. 根据生成的List创建
 *
 * */
public class AutowiredAnnotationProcessorTest {

    AccountDao accountDao = new AccountDao();
    ItemDao itemDao = new ItemDao();

    //使用Mock的手法进行测试
    DefaultBeanFactory beanFactory = new DefaultBeanFactory(){
        public Object resolveDependency(DependencyDescriptor descriptor){
            if(descriptor.getDependencyType().equals(AccountDao.class)){
                return accountDao;
            }
            if(descriptor.getDependencyType().equals(ItemDao.class)){
                return itemDao;
            }
            throw new RuntimeException("can't support types except AccountDao and ItemDao");
        }
    };



    @Test
    public void testGetInjectionMetadata(){

        AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();

        processor.setBeanFactory(beanFactory);
        InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(PetStoreService.class);
        List<InjectionElement> elements = injectionMetadata.getInjectionElements();
        Assert.assertEquals(2, elements.size());

        assertFieldExists(elements,"accountDao");
        assertFieldExists(elements,"itemDao");

        PetStoreService petStore = new PetStoreService();

        injectionMetadata.inject(petStore);

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);


    }

    private void assertFieldExists(List<InjectionElement> elements ,String fieldName){
        for(InjectionElement ele : elements){
            AutowiredFieldElement fieldEle = (AutowiredFieldElement)ele;
            Field f = fieldEle.getField();
            if(f.getName().equals(fieldName)){
                return;
            }
        }
        Assert.fail(fieldName + "does not exist!");
    }


}
