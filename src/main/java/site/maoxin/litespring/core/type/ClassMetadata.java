package site.maoxin.litespring.core.type;

/**
 * @author Maoxin
 * @ClassName ClassMetadata
 * @date 3/30/2019
 */
public interface ClassMetadata {
    /**
     * Return the name of the underlying class.
     */
    String getClassName();

    /**
     * Return whether the underlying class represents an interface.
     */
    boolean isInterface();

    /**
     * Return whether the underlying class is marked as abstract.
     */
    boolean isAbstract();



    /**
     * Return whether the underlying class is marked as 'final'.
     */
    boolean isFinal();



    /**
     * Return whether the underlying class has a super class.
     */
    boolean hasSuperClass();

    /**
     * Return the name of the super class of the underlying class,
     * or {@code null} if there is no super class defined.
     */
    String getSuperClassName();

    /**
     * Return the names of all interfaces that the underlying class
     * implements, or an empty array if there are none.
     */
    String[] getInterfaceNames();


}
