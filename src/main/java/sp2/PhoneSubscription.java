package sp2;

/**
 * Abstract superclass for subscriptions to phone services.
 */
public abstract class PhoneSubscription extends Subscription {

    /**
     * The phone number of the subscriber
     */
    private String phoneNumber;

    /**
     * Minutes to register duration of the phone call
     */
    private int minutes;

    /**
     * Constructs a new Subscription corresponding to the parameters.
     *
     * @param subscriber            the name of the subscriber; must not be null
     * @param subscriptionName      the name of the subscribed service;
     *                              must not be null
     * @param standingChargeInPence the standing charge per billing period,
     * @param phoneNumber           the phone number of the subscriber
     */
    public PhoneSubscription(String subscriber, String subscriptionName,
                             int standingChargeInPence, String phoneNumber) {
        super(subscriber, subscriptionName, standingChargeInPence);

        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number is null");
        }
        this.phoneNumber = phoneNumber;
        this.minutes = 0;
    }

    /**
     * Gets the phone number.
     *
     * @return phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Registers that a phone call of the given number
     * of minutes was made.
     *
     * @param minutes duration of the actual phone call in minutes
     */
    public final void makeCall(int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Illegal argument minutes < 0");
        }
        this.minutes += minutes;
    }

    /**
     * Resets the consumption of minutes used for any phone
     * subscription that extends PhoneSubscription class.
     */
    @Override
    public void resetConsumption() {
        this.minutes = 0;
    }

    /**
     * Gets the total duration in minutes of the calls registered
     * since the last reset or initial construction.
     *
     * @return total duration in minutes of the calls registered
     * since the last reset or initial construction
     */
    public int getCallMinutes() {
        return this.minutes;
    }
}
