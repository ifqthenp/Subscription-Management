package sp2;

/**
 * Abstract superclass to represent subscriptions for mobile phones.
 */
public abstract class MobileSubscription extends PhoneSubscription {

    /**
     * Number of text messages sent by the subscriber
     */
    private int textMessages;

    /**
     * Constructs a new Subscription corresponding to the parameters.
     *
     * @param subscriber            the name of the subscriber; must not be null
     * @param subscriptionName      the name of the subscribed service;
     *                              must not be null
     * @param standingChargeInPence the standing charge per billing period,
     * @param phoneNumber           the phone number of the subscriber
     */
    public MobileSubscription(String subscriber, String subscriptionName,
                              int standingChargeInPence, String phoneNumber) {
        super(subscriber, subscriptionName, standingChargeInPence, phoneNumber);
        this.textMessages = 0;
    }

    /**
     * Registers that the given number of text messages was sent.
     *
     * @param number the number of messages sent
     */
    public final void sendTextMessages(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number of messages < 0");
        }
        this.textMessages += number;
    }

    /**
     * Returns the number of text messages sent in the current billing period.
     *
     * @return number of text messages sent in the current billing period
     */
    public int getTextMessages() {
        return this.textMessages;
    }

    /**
     * Resets the consumption of minutes and texts used by any
     * mobile phone subscription that extends MobileSubscription class.
     */
    @Override
    public void resetConsumption() {
        super.resetConsumption();
        this.textMessages = 0;
    }
}
