package site.maoxin.litespring.beans;

/**
 * @author
 * @ClassName PropertyValue
 * @date 3/16/2019
 */
public class PropertyValue {
    private final String name;

    //value字段对应着两种properties的描述,一种是ref,一种是value
    private final Object value;

    private Object convertedValue;

    private boolean converted = false;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public synchronized boolean isConverted(){
        return this.converted;
    }

    public synchronized void setConvertedValue(Object convertedValue){
        this.converted = true;
        this.convertedValue = convertedValue;
    }

    public synchronized Object getConvertedValue(){
        return this.convertedValue;
    }
}
