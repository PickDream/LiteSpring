package site.maoxin.litespring.test.v4;

import org.junit.Assert;
import org.junit.Test;
import site.maoxin.litespring.beans.factory.annotation.AutowiredFieldElement;
import site.maoxin.litespring.beans.factory.annotation.InjectionElement;
import site.maoxin.litespring.beans.factory.annotation.InjectionMetadata;
import site.maoxin.litespring.beans.factory.support.DefaultBeanFactory;
import site.maoxin.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import site.maoxin.litespring.core.io.ClassPathResource;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.litespring.dao.v4.AccountDao;
import site.maoxin.litespring.dao.v4.ItemDao;
import site.maoxin.litespring.service.v4.PetStoreService;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * @author Maoxin
 * @ClassName InjectionMetedataTest
 * @date 4/8/2019
 */
public class InjectionMetedataTest {
    @Test
    public void testInjection() throws Exception{

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("store-v4.xml");
        reader.loadBeanDefinitions(resource);

        Class<?> clz = PetStoreService.class;
        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
        /**
         * 1. 将具体的Filed或者Constructor封装成一个（抽象类）InjectionElement（这里的元素是抽象，可以指字段，也可以指函数）,
         *    InjectElement包含了一个抽象的inject方法，交给子类去完成具体的注入
         *
         * 2. 将需要注入的元素（InjectionElement）放置到一个LinkList当中去
         *
         * 3. 交给InjectionMetadata中，Injection需要接受两个部分，一个是要注入对象的Class，一个是其待注入的List
         *
         * 4. 将创建得到未注入的对象传入，进行注入
         *
         * 5. 最后进行测试
         * */
        {
            Field f = PetStoreService.class.getDeclaredField("accountDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f,true,factory);
            elements.add(injectionElem);
        }
        {
            Field f = PetStoreService.class.getDeclaredField("itemDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f,true,factory);
            elements.add(injectionElem);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz,elements);

        PetStoreService petStore = new PetStoreService();

        metadata.inject(petStore);

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);

    }
}
