package sp2;

/**
 * Represents a phone subscription for landline telephones at a certain
 * address.
 */
public class LandlineSubscription extends PhoneSubscription implements HasAddress {

    /**
     * Charge for landline subscription (constant value for all landline subscribers)
     */
    private static final int LANDLINE_STANDING_CHARGE = 1800;

    /**
     * Subscription concrete name (constant value for all landline subscribers)
     */
    private static final String SUBSCRIPTION_NAME = "Landline telephone";

    /**
     * Constant value to compute total charge in pence for landline subscription
     */
    private static final int LANDLINE_SUBSCRIPTION_CHARGE = 2;

    /**
     * The address of the landline subscriber
     */
    private String address;

    /**
     * Constructs a new LandlineSubscription according to the parameters.
     *
     * @param subscriber  the name of the subscriber; must not be null
     * @param phoneNumber the phone number; must not be null
     * @param address     the address; must not be null
     */
    public LandlineSubscription(String subscriber, String phoneNumber, String address) {
        super(subscriber, SUBSCRIPTION_NAME + " " + phoneNumber,
                LANDLINE_STANDING_CHARGE, phoneNumber);

        if (address == null) {
            throw new IllegalArgumentException("Address is null");
        }
        this.address = address;
    }

    @Override
    public String getAddress() {
        return this.address;
    }

    /**
     * Abstract method from Subscription that must be overridden.
     * The total charge for a landline telephone is the standing
     * charge of 1800 pence plus the product of 2 pence and the
     * number of minutes called over the billing period.
     *
     * @return total charge in pence for billing period
     */
    @Override
    public int computeTotalChargeInPence() {
        return this.getStandingChargeInPence() + (LANDLINE_SUBSCRIPTION_CHARGE * this.getCallMinutes());
    }
}
