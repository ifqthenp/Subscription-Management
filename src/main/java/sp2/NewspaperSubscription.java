package sp2;

/**
 * A subscription to a newspaper or a magazine.
 */
public class NewspaperSubscription extends Subscription implements HasAddress, BoundedCharge {

    /**
     * Delivery address of the subscriber
     */
    private String address;

    /**
     * Constructs a new NewspaperSubscription based on the parameters.
     *
     * @param subscriber            the name of the subscriber; must not be null
     * @param newspaper             the name of the newspaper (and hence the subscription);
     *                              must not be null
     * @param standingChargeInPence the price of the newspaper per billing
     *                              period; must not be less than 0
     * @param address               the address to which the newspaper is delivered;
     *                              must not be null
     */
    public NewspaperSubscription(String subscriber, String newspaper,
                                 int standingChargeInPence, String address) {
        super(subscriber, newspaper, standingChargeInPence);

        if (address == null) {
            throw new IllegalArgumentException("Illegal argument for address");
        }
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * The maximum total charge for a NewspaperSubscription is
     * the same as the standing charge.
     *
     * @return maximum charge in pence for this newspaper subscription
     */
    @Override
    public int getMaxChargeInPence() {
        return this.getStandingChargeInPence();
    }

    /**
     * For a NewspaperSubscription, the total charge is the same as
     * the standing charge.
     *
     * @return total charge in pence for this newspaper subscription
     */
    @Override
    public int computeTotalChargeInPence() {
        return this.getStandingChargeInPence();
    }
}
