package sp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * {@code PowerUserMobileSubscriptionTest} test class.
 */
public class PowerUserMobileSubscriptionTest {

    private PowerUserMobileSubscription powerUser;

    @Before
    public void setUp() throws Exception {
        powerUser = new PowerUserMobileSubscription("Tony Tester", "020 7946 0648");
    }

    @Test
    public void endPeriodResetsMinutesAndTextsUsedToZeroForTheBillingPeriod() throws Exception {
        powerUser.makeCall(50);
        powerUser.makeCall(50);
        powerUser.sendTextMessages(100);
        powerUser.sendTextMessages(100);
        powerUser.endPeriod();
        int actualMinutes = powerUser.getCallMinutes();
        int actualTexts = powerUser.getTextMessages();
        assertThat(actualMinutes, is(0));
        assertThat(actualTexts, is(0));
    }

    @Test
    public void getSubscriberReturnsThisSubscriberName() throws Exception {
        String actualSubscriber = powerUser.getSubscriber();
        String expectedSubscriber = "Tony Tester";
        assertThat(actualSubscriber, is(expectedSubscriber));
    }

    @Test
    public void getSubscriptionNameReturnsThisSubscriptionName() throws Exception {
        String actualSubscriptionName = powerUser.getSubscriptionName();
        String expectedSubscriptionName = "Mobile power user 020 7946 0648";
        assertThat(actualSubscriptionName, is(expectedSubscriptionName));
    }

    @Test
    public void getStandingChargeInPenceReturnsStandingChargeForThisSubscription() throws Exception {
        int actualStandingCharge = powerUser.getStandingChargeInPence();
        int expectedStandingCharge = 4000;
        assertThat(actualStandingCharge, is(expectedStandingCharge));
    }

    @Test
    public void computeTotalChargeInPenceComputesUsingRatesIfNoneOfUpperLimitsReached() throws Exception {
        powerUser.makeCall(500);
        powerUser.sendTextMessages(100);
        powerUser.makeCall(500);

        assertThat(powerUser.computeTotalChargeInPence(), is(14800));
    }

    @Test
    public void computeTotalChargeInPenceReturnsGetMaxChargeInPenceIfBothUpperLimitsReached() throws Exception {
        /* If minutes >= 1800 and texts >= 900 computeTotalChargeInPence() returns getMaxChargeInPence() */
        powerUser.makeCall(1000);
        powerUser.sendTextMessages(1000);
        powerUser.makeCall(1000);

        assertThat(powerUser.computeTotalChargeInPence(), is(29200));
    }

    @Test
    public void computeTotalChargeInPenceComputesOnlyTextsUsingRatesIfMinutesUpperLimitReached() throws Exception {
        /* If minutes >= 1800 this becomes their default value to charge */
        powerUser.makeCall(1000);
        powerUser.sendTextMessages(100);
        powerUser.makeCall(1000);

        assertThat(powerUser.computeTotalChargeInPence(), is(22800));
    }

    @Test
    public void computeTotalChargeInPenceComputesOnlyMinutesUsingRatesIfTextsUpperLimitReached() throws Exception {
        /* If messages >= 900 this becomes their default value to charge */
        powerUser.makeCall(500);
        powerUser.sendTextMessages(1900);
        powerUser.makeCall(500);

        assertThat(powerUser.computeTotalChargeInPence(), is(21200));
    }

    @Test
    public void computeTotalChargeInPenceReturnsMaxChargeInPenceIfUpperLimitReached() throws Exception {
        powerUser.makeCall(1000);
        powerUser.sendTextMessages(500);
        powerUser.makeCall(2000);
        powerUser.sendTextMessages(500);
        assertThat(powerUser.computeTotalChargeInPence(), is(powerUser.getMaxChargeInPence()));
    }

    @Test
    public void getMaxChargeInPenceReturnsCorrectMaxCharge() throws Exception {
        int maxMinutes = 1800;
        int minutesPenceRate = 10;
        int maxTexts = 900;
        int textsPenceRate = 8;
        int standingChargeInPence = 4000;
        int maxChargeInPence = (maxMinutes * minutesPenceRate) +
                (maxTexts * textsPenceRate) + standingChargeInPence;

        assertThat(powerUser.getMaxChargeInPence(), is(maxChargeInPence));
    }

    @After
    public void tearDown() throws Exception {
        powerUser = null;
    }
}
