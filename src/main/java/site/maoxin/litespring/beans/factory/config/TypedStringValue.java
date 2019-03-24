package site.maoxin.litespring.beans.factory.config;

/**
 * @author Maoxin
 * @date 3/18/2019
 */
public class TypedStringValue {
    private String value;
    public TypedStringValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}