package net.miklosovic.thesis.ejb.dao;

/**
 * @author Stefan Miklosovic
 */
public class DAOUtils {

    /**
     * Checks if object is null object
     * 
     * @param o object to check
     * @throws IllegalArgumentException if object is null
     */
    static void notNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("Object to create can not be a null object");
        }
    }

}
