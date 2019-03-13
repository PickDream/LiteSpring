package site.maoxin.litespring.beans.factory.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.BeanDefinitionStoreException;
import site.maoxin.litespring.beans.factory.support.BeanDefinitionRegistry;
import site.maoxin.litespring.beans.factory.support.GenericBeanDefinition;
import site.maoxin.litespring.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * XML文件Bean定义的读取器
 * @author Maoxin
 * @date 3/10/2019
 */
public class XmlBeanDefinitionReader  {
    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    BeanDefinitionRegistry registry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource){
        InputStream is = null;
        //得到IO流，来加载对应的资源
        try {
            is = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);
            Element root = document.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()){
                Element ele = iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                String scope = ele.attributeValue(SCOPE_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                beanDefinition.setScope(scope);
                //注册
                registry.registerBeanDefinition(id,beanDefinition);
            }

        } catch (DocumentException|IOException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML Document");
        }finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
