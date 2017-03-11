package sp2;

/**
 * Interface for services that cost only a bounded maximum charge during a
 * billing period, independent of the length of that period. Allows to query
 * this maximum possible charge.
 *
 * @author Carsten Fuhs
 */
public interface BoundedCharge {

    /**
     * Returns the maximum charge that may be incurred during a billing period,
     * expressed in pence.
     *
     * @return the maximum charge in pence that may be incurred during a
     * billing period
     */
    int getMaxChargeInPence();
}
