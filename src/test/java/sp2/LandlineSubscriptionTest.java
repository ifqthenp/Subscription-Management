package sp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * {@code LandlineSubscriptionTest} test class.
 */
public class LandlineSubscriptionTest {

    private LandlineSubscription landlineSub;

    @Before
    public void setUp() throws Exception {
        landlineSub = new LandlineSubscription("Tony Tester", "+44 7700 900400",
                "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test
    public void endPeriodCallsResetConsumptionAndSetsMinutesToZero() throws Exception {
        landlineSub.makeCall(10);
        landlineSub.endPeriod();
        int expectedMinutes = 0;
        assertThat(landlineSub.getCallMinutes(), equalTo(expectedMinutes));
    }

    @Test
    public void getSubscriberReturnsThisSubscriberName() throws Exception {
        assertThat(landlineSub.getSubscriber(), equalTo("Tony Tester"));
    }

    @Test
    public void getSubscriptionNameOfSubscriptionClassReturnsSubscriptionName() throws Exception {
        assertEquals("Subscription names do not match",
                "Landline telephone +44 7700 900400", landlineSub.getSubscriptionName());
    }

    @Test
    public void resetConsumptionOfPhoneSubscriptionSuperClassResetsMinutesToZero() throws Exception {
        landlineSub.makeCall(15);
        landlineSub.makeCall(60);
        landlineSub.resetConsumption();
        assertEquals("Minutes must be reset to zero", 0, landlineSub.getCallMinutes());
    }

    @Test
    public void computeTotalChargeInPenceReturnsCorrectTotalCharge() throws Exception {
        landlineSub.makeCall(10);
        int minutes = landlineSub.getCallMinutes();
        int standingCharge = landlineSub.getStandingChargeInPence();
        assertThat(landlineSub.computeTotalChargeInPence(),
                equalTo(standingCharge + (2 * minutes)));
    }

    @Test
    public void getPhoneNumberReturnsCurrentSubscriberPhoneNumber() throws Exception {
        assertThat(landlineSub.getPhoneNumber(), equalTo("+44 7700 900400"));
    }

    @Test
    public void makeCallAndGetCallMinutesRegisterCorrectAmountOfMinutes() throws Exception {
        int minutes1 = 5;
        int minutes2 = 10;
        landlineSub.makeCall(minutes1);
        landlineSub.makeCall(minutes2);
        assertThat(landlineSub.getCallMinutes(), equalTo(minutes1 + minutes2));
    }

    @Test
    public void getAddressReturnsAddressOfCurrentSubscriber() throws Exception {
        assertThat(landlineSub.getAddress(), equalTo("1 Infinite Loop, Cupertino, CA 95014, USA"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void landlineSubscriptionThrowsIllegalArgumentExceptionIfSubscriberIsNull() throws Exception {
        landlineSub = new LandlineSubscription(null, "+44 7700 900400",
                "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void landlineSubscriptionThrowsIllegalArgumentExceptionIfPhoneNumberIsNull() throws Exception {
        landlineSub = new LandlineSubscription("Tony Tester", null,
                "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void landlineSubscriptionThrowsIllegalArgumentExceptionIfAddressIsNull() throws Exception {
        landlineSub = new LandlineSubscription("Tony Tester", "+44 7700 900400", null);
    }

    @After
    public void tearDown() throws Exception {
        landlineSub = null;
    }
}
