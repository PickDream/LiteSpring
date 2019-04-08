package site.maoxin.litespring.beans.factory.annotation;

/**
 * @author Maoxin
 * @ClassName InjectionMetadata
 * @date 4/8/2019
 */

import java.util.List;

/**
 * InjectionMetadata包含了Inject的方法
 * */
public class InjectionMetadata {
    private final Class<?> targetClass;
    private List<InjectionElement> injectionElements;

    public InjectionMetadata(Class<?> targetClass, List<InjectionElement> injectionElements) {
        this.targetClass = targetClass;
        this.injectionElements = injectionElements;
    }

    public List<InjectionElement> getInjectionElements() {
        return injectionElements;
    }

    public void inject(Object target) {
        if (injectionElements == null || injectionElements.isEmpty()) {
            return;
        }
        for (InjectionElement ele : injectionElements) {

            ele.inject(target);
        }
    }
}
