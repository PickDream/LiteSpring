package site.maoxin.litespring.beans.factory.support;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.factory.BeanCreationException;
import site.maoxin.litespring.beans.factory.BeanDefinitionStoreException;
import site.maoxin.litespring.beans.factory.BeanFactory;
import site.maoxin.util.ClassUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//解析XML文件
public class DefaultBeanFactory implements BeanFactory  {

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    //使用线程安全的Map
    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public DefaultBeanFactory(String configFile) {
        loadbeanDefinition(configFile);
    }

    private void loadbeanDefinition(String configFile){
        InputStream is = null;
        //得到IO流，来加载对应的资源
        try {
            ClassLoader cl = ClassUtils.getDefaultClassLoader();
            is = cl.getResourceAsStream(configFile);
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);
            Element root = document.getRootElement();
            Iterator<Element> iter = root.elementIterator();
            while (iter.hasNext()){
                Element ele = iter.next();
                String id = ele.attributeValue(ID_ATTRIBUTE);
                String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(id,beanClassName);
                this.beanDefinitionMap.put(id,beanDefinition);
            }

        } catch (DocumentException e) {
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

    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    /**
     * 通过beanId来生成对象
     * */
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = this.getBeanDefinition(beanId);
        if (beanDefinition==null)
            throw new BeanCreationException("Bean Definition does not exist");
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = cl.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
