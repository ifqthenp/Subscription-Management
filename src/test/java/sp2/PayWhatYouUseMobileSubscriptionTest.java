package sp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

/**
 * {@code PayWhatYouUseMobileSubscriptionTest} test class.
 */
public class PayWhatYouUseMobileSubscriptionTest {

    private PayWhatYouUseMobileSubscription payWhatYouUse;

    @Before
    public void setUp() throws Exception {
        payWhatYouUse =
                new PayWhatYouUseMobileSubscription("Tony Tester", "020 7946 0648");
    }

    @Test
    public void endPeriodCallsResetConsumptionAndSetsMinutesAndTextsToZero() throws Exception {
        payWhatYouUse.makeCall(10);
        payWhatYouUse.sendTextMessages(10);
        payWhatYouUse.endPeriod();
        int actualMinutes = payWhatYouUse.getCallMinutes();
        int actualTexts = payWhatYouUse.getTextMessages();

        assertThat(actualMinutes, equalTo(0));
        assertThat(actualTexts, equalTo(0));
    }

    @Test
    public void getSubscriberReturnsExpectedSubscriberName() throws Exception {
        String actualSubscriber = payWhatYouUse.getSubscriber();
        String expectedSubscriber = "Tony Tester";
        assertThat(actualSubscriber, equalTo(expectedSubscriber));
    }

    @Test
    public void getSubscriptionNameReturnsExpectedSubscriptionName() throws Exception {
        String actualSubscription = payWhatYouUse.getSubscriptionName();
        String expectedSubscription = "Pay what you use mobile phone 020 7946 0648";
        assertThat(actualSubscription, equalTo(expectedSubscription));
    }

    @Test
    public void getStandingChargeInPenceReturnsThisStandingCharge() throws Exception {
        int actualChargeInPence = payWhatYouUse.getStandingChargeInPence();
        int expectedChargeInPence = 0;
        assertThat(actualChargeInPence, equalTo(expectedChargeInPence));
    }

    @Test
    public void getPhoneNumberReturnsThisPhoneNumber() throws Exception {
        assertThat("020 7946 0648", equalTo(payWhatYouUse.getPhoneNumber()));
    }

    @Test
    public void resetConsumptionResetsTextsAndMinutesToZero() throws Exception {
        payWhatYouUse.sendTextMessages(10);
        payWhatYouUse.makeCall(30);
        payWhatYouUse.sendTextMessages(20);
        payWhatYouUse.makeCall(40);

        payWhatYouUse.resetConsumption();

        assertThat(payWhatYouUse.getTextMessages(), is(0));
        assertThat(payWhatYouUse.getCallMinutes(), is(0));
    }

    @Test
    public void getTextMessagesReturnsSameValueSetBySendTextMessages() throws Exception {
        payWhatYouUse.sendTextMessages(20);
        assertSame(payWhatYouUse.getTextMessages(), 20);
    }

    @Test
    public void getCallMinutesReturnsSameValueSetByMakeCall() throws Exception {
        payWhatYouUse.makeCall(10);
        assertSame(payWhatYouUse.getCallMinutes(), 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sendTextMessagesThrowsExceptionIfAmountOfMinutesLessThanZero() throws Exception {
        payWhatYouUse.sendTextMessages(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeCallThrowsExceptionIfAmountOfMinutesLessThanZero() throws Exception {
        payWhatYouUse.makeCall(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfSubscriberArgumentToConstructorIsNull() throws Exception {
        payWhatYouUse =
                new PayWhatYouUseMobileSubscription(null, "020 7946 0648");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptionIfPhoneNumberArgumentToConstructorIsNull() throws Exception {
        payWhatYouUse =
                new PayWhatYouUseMobileSubscription("Tony Tester", null);
    }

    @Test
    public void computeTotalChargeInPenceReturnsCorrectChargeForBillingPeriod() throws Exception {
        payWhatYouUse.makeCall(10);
        payWhatYouUse.sendTextMessages(10);
        int minutes = payWhatYouUse.getCallMinutes();
        int texts = payWhatYouUse.getTextMessages();
        int actualCharge = payWhatYouUse.computeTotalChargeInPence();
        int expectedCharge = (40 * minutes) + (20 * texts);
        payWhatYouUse.resetConsumption();
        assertThat(actualCharge, is(expectedCharge));
    }

    @After
    public void tearDown() throws Exception {
        payWhatYouUse = null;
    }
}
