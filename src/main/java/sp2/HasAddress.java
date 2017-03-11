package sp2;

/**
 * Interface for objects that can be queried for their (real-world) address.
 *
 * @author Carsten Fuhs
 */
public interface HasAddress {

    /**
     * Returns the address of the object.
     *
     * @return the address of the object
     */
    String getAddress();
}
