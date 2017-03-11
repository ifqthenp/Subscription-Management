package sp2;

/**
 * Mobile phone subscription with zero standing charge, but high
 * costs for used services.
 */
public class PayWhatYouUseMobileSubscription extends MobileSubscription {

    /**
     * Subscription concrete name (constant value)
     */
    private static final String SUBSCRIPTION_NAME = "Pay what you use mobile phone";

    /**
     * Charge for all payWhatYouUse subscribers (constant value)
     */
    private static final int PAYWHATYOUUSE_STANDING_CHARGE = 0;

    /**
     * The rate in pence to calculate the minutes used in this subscription
     */
    private static final int MINUTES_RATE = 40;

    /**
     * The rate in pence to calculate the texts sent in this subscription
     */
    private static final int TEXT_RATE = 20;

    /**
     * Constructs a new PayWhatYouUseMobileSubscription according to the
     * parameters.
     *
     * @param subscriber  the name of the subscriber; must not be null
     * @param phoneNumber the phone number; must not be null
     */
    public PayWhatYouUseMobileSubscription(String subscriber, String phoneNumber) {
        super(subscriber, SUBSCRIPTION_NAME + " " + phoneNumber,
                PAYWHATYOUUSE_STANDING_CHARGE, phoneNumber);
    }

    /**
     * Abstract method from Subscription that must be overridden. The total
     * charge is computed as the sum of the following addends:
     * <ul>
     * <li>the product of 40 pence and the number of minutes
     * called over the billing period</li>
     * <li>the product of 20 pence and the number of text
     * messages sent over the billing period</li>
     * </ul>
     *
     * @return the total charge for this subscription in this billing period
     */
    @Override
    public int computeTotalChargeInPence() {
        return MINUTES_RATE * this.getCallMinutes() + TEXT_RATE * this.getTextMessages();
    }
}
