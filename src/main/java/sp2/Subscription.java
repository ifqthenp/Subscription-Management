package sp2;

/**
 * Abstract superclass to encapsulate common elements and functionalities of
 * subscriptions. Stores the name of the subscriber and of the subscription,
 * and also the standing charge for the subscription. Functionality includes
 * ending a billing period, which involves printing a bill and resetting the
 * internal tracker of services used in this subscription.
 *
 * @author Carsten Fuhs
 */
public abstract class Subscription {

    /**
     * The subscriber, for simplicity represented by a String for their name.
     */
    private String subscriber;

    /**
     * The name of the Subscription (i.e., the service that is subscribed).
     */
    private String subscriptionName;

    /**
     * The standing charge (the "minimum cost" per billing period) of this
     * Subscription.
     */
    private int standingChargeInPence;

    /**
     * Constructs a new Subscription corresponding to the parameters.
     *
     * @param subscriber            the name of the subscriber; must not be null
     * @param subscriptionName      the name of the subscribed service;
     *                              must not be null
     * @param standingChargeInPence the standing charge per billing period,
     *                              expressed in pence; must not be negative
     */
    public Subscription(String subscriber, String subscriptionName, int standingChargeInPence) {
        if (subscriber == null) {
            throw new IllegalArgumentException("Illegal null argument for subscriber.");
        }
        if (subscriptionName == null) {
            throw new IllegalArgumentException("Illegal null argument for subscriptionName.");
        }
        if (standingChargeInPence < 0) {
            throw new IllegalArgumentException("Illegal negative argument for" +
                    " standingChargeInPence: " + standingChargeInPence);
        }
        this.subscriber = subscriber;
        this.subscriptionName = subscriptionName;
        this.standingChargeInPence = standingChargeInPence;
    }

    /**
     * Returns the total charge for the services used in this billing period
     * so far.
     *
     * @return the total charge for the services used in this billing period
     * so far
     */
    public abstract int computeTotalChargeInPence();

    /**
     * Resets the "consumption" of services used with this Subscription.
     */
    public void resetConsumption() {
        // nothing to do here; but subclasses may need to override this
    }

    /**
     * Ends the current billing period. Prints a bill with the total charge
     * for this period and resets the consumption for this period.
     */
    public final void endPeriod() {
        String bill = generateBill();
        System.out.println("======== BILL ========");
        System.out.println(bill);
        resetConsumption();
    }

    /**
     * Returns a String representation of the bill, also based on the services
     * for which charges have been accumulated so far in this billing period.
     *
     * @return a String representation of the bill, also based on the services
     * for which charges have been accumulated so far in this billing period
     */
    public final String generateBill() {
        int totalCharge = computeTotalChargeInPence();
        int pounds = totalCharge / 100;
        int pence = totalCharge % 100;
        String result = "Subscriber: " + this.subscriber + "\n"
                + "Subscription for: " + this.subscriptionName + "\n"
                + "Total charge for this period: GBP " + pounds + "."
                + (pence < 10 ? "0" : "") + pence;
        return result;
    }

    /**
     * Returns the name of the subscriber of this Subscription.
     *
     * @return the subscriber
     */
    public String getSubscriber() {
        return this.subscriber;
    }

    /**
     * Returns the name of the Subscription.
     *
     * @return the subscriptionName
     */
    public String getSubscriptionName() {
        return this.subscriptionName;
    }

    /**
     * Returns the standing charge (in pence) associated with this
     * Subscription.
     *
     * @return the standingChargeInPence
     */
    public int getStandingChargeInPence() {
        return this.standingChargeInPence;
    }
}
