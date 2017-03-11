package sp2;

/**
 * "Driver class" to exercise some of the functionalities provided by the
 * Subscription hierarchy.
 *
 * @author Carsten Fuhs
 */
public class SubscriptionMain {

    /* Some constants */

    /**
     * Example call duration in minutes.
     */
    private static int MINUTES1 = 10;

    /**
     * (Different) example call duration in minutes.
     */
    private static int MINUTES2 = 10000;

    /**
     * Example number of text messages.
     */
    private static int MESSAGES1 = 2;

    /**
     * (Different) example number of text messages.
     */
    private static int MESSAGES2 = 2000;


    /* Some helper methods to generate test objects */

    /**
     * @return an exemplary NewspaperSubscription
     */
    public static NewspaperSubscription mkNewspaper1() {
        return new NewspaperSubscription("Sherlock Holmes", "The Strand Magazine",
                10, "221B Baker Street");
    }

    /**
     * @return an exemplary NewspaperSubscription
     */
    public static NewspaperSubscription mkNewspaper2() {
        return new NewspaperSubscription("George Birkbeck", "The Lamp and Owl",
                100, "Malet Street");
    }

    /**
     * @return an exemplary LandlineSubscription
     */
    public static LandlineSubscription mkLandline() {
        // The phone numbers are inspired by
        // https://en.wikipedia.org/wiki/Fictitious_telephone_number#United_Kingdom
        return new LandlineSubscription("Jane Doe", "02079460001", "43 Gordon Square");
    }

    /**
     * @return an exemplary PayWhatYouUseMobileSubscription subscription
     */
    public static PayWhatYouUseMobileSubscription mkPayWhatYouUse() {
        return new PayWhatYouUseMobileSubscription("John Doe", "07700900002");
    }

    /**
     * @return an exemplary PowerUserMobileSubscription subscription
     */
    public static PowerUserMobileSubscription mkPowerUser() {
        return new PowerUserMobileSubscription("Kim Doe", "07700900003");
    }


    /* Methods that exercise objects in (and related to) the Subscription
     * hierarchy
     */

    /**
     * Exercises some of the functionality of the class NewspaperSubscription.
     */
    public static void runNewspapers() {
        NewspaperSubscription paper1 = mkNewspaper1();
        paper1.endPeriod();
        paper1.endPeriod();

        NewspaperSubscription paper2 = mkNewspaper2();
        paper2.endPeriod();
        paper2.endPeriod();
    }

    /**
     * Exercises some of the functionality of a LandlineSubscription.
     */
    public static void runLandline() {
        LandlineSubscription landline = mkLandline();
        landline.makeCall(MINUTES1);
        landline.makeCall(MINUTES2);
        landline.endPeriod();

        landline.makeCall(MINUTES2);
        landline.makeCall(MINUTES1);
        landline.endPeriod();
    }

    /**
     * Exercises some of the functionality of a PayWhatYouUseMobileSubscription.
     */
    public static void runPayWhatYouUse() {
        PayWhatYouUseMobileSubscription payWhatYouUse = mkPayWhatYouUse();
        payWhatYouUse.makeCall(MINUTES1);
        payWhatYouUse.sendTextMessages(MESSAGES1);
        payWhatYouUse.makeCall(MINUTES2);
        payWhatYouUse.sendTextMessages(MESSAGES2);
        payWhatYouUse.endPeriod();

        payWhatYouUse.sendTextMessages(MESSAGES1);
        payWhatYouUse.makeCall(MINUTES2);
        payWhatYouUse.sendTextMessages(MESSAGES2);
        payWhatYouUse.makeCall(MINUTES1);
        payWhatYouUse.endPeriod();
    }

    /**
     * Exercises some of the functionality of a PowerUserMobileSubscription.
     */
    public static void runPowerUser() {
        PowerUserMobileSubscription payWhatYouUse = mkPowerUser();
        payWhatYouUse.sendTextMessages(MESSAGES2);
        payWhatYouUse.makeCall(MINUTES2);
        payWhatYouUse.sendTextMessages(MESSAGES1);
        payWhatYouUse.makeCall(MINUTES1);
        payWhatYouUse.endPeriod();

        payWhatYouUse.sendTextMessages(MESSAGES1);
        payWhatYouUse.sendTextMessages(MESSAGES2);
        payWhatYouUse.makeCall(MINUTES1);
        payWhatYouUse.makeCall(MINUTES2);
        payWhatYouUse.endPeriod();
    }

    /**
     * Exercises some of the functionality of HasAddress objects.
     */
    public static void runHasAddress() {
        HasAddress[] hasAddresses = {
                mkNewspaper1(),
                mkLandline(),
                mkNewspaper2()
        };
        for (HasAddress hasAddress : hasAddresses) {
            System.out.println("Address: " + hasAddress.getAddress());
        }
    }

    /**
     * Exercises some of the functionality of BoundedCharge objects.
     */
    public static void runBoundedCharge() {
        BoundedCharge[] boundedCharges = {
                mkNewspaper1(),
                mkPowerUser(),
                mkNewspaper2()
        };
        for (BoundedCharge bounded : boundedCharges) {
            System.out.println("Max charge in pence: " + bounded.getMaxChargeInPence());
        }
    }


    /**
     * Main method for exercising classes from the Subscription hierarchy.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        runNewspapers();
        runLandline();
        runPayWhatYouUse();
        runPowerUser();
        runHasAddress();
        runBoundedCharge();
    }
}
