package sp2;

/**
 * Mobile phone subscription with high standing charge, but low
 * costs for used services. Additionally has a "cost airbag", an
 * upper bound for the charges that can be incurred in a single
 * billing period: only a bounded number of call minutes and of
 * text messages are charged, everything beyond that is free.
 */
public class PowerUserMobileSubscription extends MobileSubscription implements BoundedCharge {

    /**
     * Subscription concrete name (constant value)
     */
    private static final String SUBSCRIPTION_NAME = "Mobile power user";

    /**
     * Charge for all "Mobile power user" subscribers (constant value)
     */
    private static final int POWERUSER_STANDING_CHARGE = 4000;

    /**
     * Number of minutes below this limit to charge
     */
    private static final int MINUTES_UPPER_LIMIT = 1800;

    /**
     * The rate in pence to charge for minutes used
     */
    private static final int MINUTES_RATE = 10;

    /**
     * Number of texts below this limit to charge
     */
    private static final int TEXTS_UPPER_LIMIT = 900;

    /**
     * The rate in pence to charge for texts used
     */
    private static final int TEXTS_RATE = 8;

    /**
     * Maximum charge for minutes used that is applicable for this subscription
     */
    private static final int MINUTES_MAX_CHARGE = MINUTES_UPPER_LIMIT * MINUTES_RATE;

    /**
     * Maximum charge for texts sent that is applicable for this subscription
     */
    private static final int TEXTS_MAX_CHARGE = TEXTS_UPPER_LIMIT * TEXTS_RATE;

    /**
     * Constructs a new PowerUserMobileSubscription according to the
     * given parameters.
     *
     * @param subscriber  the name of the subscriber; must not be null
     * @param phoneNumber the phone number used for this subscription;
     *                    must not be null
     */
    public PowerUserMobileSubscription(String subscriber, String phoneNumber) {
        super(subscriber, SUBSCRIPTION_NAME + " " + phoneNumber,
                POWERUSER_STANDING_CHARGE, phoneNumber);
    }

    /**
     * The maximum total charge for a PowerUserMobileSubscription is the same
     * as the total charge for 1800 minutes of calls and 900 text messages.
     *
     * @return the maximum charge in pence that may be incurred during a
     * billing period
     */
    @Override
    public int getMaxChargeInPence() {
        return MINUTES_MAX_CHARGE + TEXTS_MAX_CHARGE + POWERUSER_STANDING_CHARGE;
    }

    /**
     * The total charge is computed as the sum of the following three addends:
     * <ul>
     * <li>the standing charge of 4000 pence</li>
     * <li>the product of 10 pence and the minimum of 1800 and the number
     * of minutes called over the billing period</li>
     * <li>the product of 8 pence and the minimum of 900 and the number
     * of text messages sent over the billing period</li>
     * </ul>
     *
     * @return the total charge for this subscription in this billing period
     */
    @Override
    public int computeTotalChargeInPence() {
        int totalMinutes = getCallMinutes();
        int totalTexts = getTextMessages();
        boolean minutesUnderusedAndTextsOverused =
                totalMinutes < MINUTES_UPPER_LIMIT && totalTexts >= TEXTS_UPPER_LIMIT;
        boolean minutesOverusedAndTextsUnderused =
                totalMinutes >= MINUTES_UPPER_LIMIT && totalTexts < TEXTS_UPPER_LIMIT;
        boolean minutesUnderusedAndTextsUnderused =
                totalMinutes < MINUTES_UPPER_LIMIT && totalTexts < TEXTS_UPPER_LIMIT;

        if (minutesUnderusedAndTextsOverused) {
            return totalMinutes * MINUTES_RATE + TEXTS_MAX_CHARGE + POWERUSER_STANDING_CHARGE;
        }
        if (minutesOverusedAndTextsUnderused) {
            return totalTexts * TEXTS_RATE + MINUTES_MAX_CHARGE + POWERUSER_STANDING_CHARGE;
        }
        if (minutesUnderusedAndTextsUnderused) {
            return (totalMinutes * MINUTES_RATE) + (totalTexts * TEXTS_RATE) + POWERUSER_STANDING_CHARGE;
        }

        /* Maximum charge is default return when minutes and texts overused */
        return this.getMaxChargeInPence();
    }
}
