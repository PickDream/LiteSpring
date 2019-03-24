package site.maoxin.litespring.beans.factory.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import site.maoxin.litespring.beans.BeanDefinition;
import site.maoxin.litespring.beans.PropertyValue;
import site.maoxin.litespring.beans.factory.BeanDefinitionStoreException;
import site.maoxin.litespring.beans.factory.config.RuntimeBeanReference;
import site.maoxin.litespring.beans.factory.config.TypedStringValue;
import site.maoxin.litespring.beans.factory.support.BeanDefinitionRegistry;
import site.maoxin.litespring.beans.factory.support.GenericBeanDefinition;
import site.maoxin.litespring.core.io.Resource;
import site.maoxin.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * XML文件Bean定义的读取器
 * @author Maoxin
 * @date 3/10/2019
 */
public class XmlBeanDefinitionReader  {
    protected final Log logger = LogFactory.getLog(getClass());

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String SCOPE_ATTRIBUTE = "scope";

    public static final String PROPERTY_ELEMENT="property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

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
                if (scope!=null){
                    beanDefinition.setScope(scope);
                }
                //解析Properties标签
                parsePropertyElement(ele,beanDefinition);
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

    private void parsePropertyElement(Element ele, BeanDefinition beanDefinition) {
        Iterator iterator = ele.elementIterator(PROPERTY_ELEMENT);
            while (iterator.hasNext()){
            Element propEle = (Element) iterator.next();
            String propertyName = propEle.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)){
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object parsedObject = parsePropertyValue(propEle,propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName,parsedObject);
            beanDefinition.getPropertyValues().add(propertyValue);
        }
    }

    private Object parsePropertyValue(Element element,String propertyName){
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";
        boolean hasRefAttribute = (element.attribute(REF_ATTRIBUTE)!=null);
        boolean hasValueAttribute = (element.attribute(VALUE_ATTRIBUTE)!=null);

        if (hasRefAttribute){
            String refName = element.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName+" contains empty 'ref' attribute");
            }
            RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference(refName);
            return runtimeBeanReference;
        }

        if (hasValueAttribute){
            TypedStringValue valueHolder = new TypedStringValue(element.attributeValue(VALUE_ATTRIBUTE));
            return valueHolder;
        }
        throw new RuntimeException(elementName + " must specify a ref or value");
    }
}
