package sp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * {@code NewspaperSubscriptionTest} test class.
 */
public class NewspaperSubscriptionTest {

    private NewspaperSubscription newspaperSubscription;

    @Before
    public void setUp() throws Exception {
        newspaperSubscription = new NewspaperSubscription("Tony Tester",
                "Tester Daily", 100, "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test
    public void resetConsumptionHasNoEffectInNewspaperSubscriptionClass() throws Exception {
        newspaperSubscription.resetConsumption();
        assertFalse(newspaperSubscription.computeTotalChargeInPence() != 100);
        assertFalse(newspaperSubscription.getMaxChargeInPence() != 100);
    }

    @Test
    public void getSubscriberReturnsSubscribersName() throws Exception {
        assertThat("Tony Tester", equalTo(newspaperSubscription.getSubscriber()));
    }

    @Test
    public void getSubscriptionNameReturnsTheTitleOfNewspaper() throws Exception {
        assertThat("Tester Daily", equalTo(newspaperSubscription.getSubscriptionName()));
    }

    @Test
    public void getStandingChargeInPenceReturnsThisSubscriptionStandingChargeInPence() throws Exception {
        assertThat(100, equalTo(newspaperSubscription.getStandingChargeInPence()));
    }

    @Test
    public void getsNewspaperSubscriptionAddress() throws Exception {
        assertEquals("Addresses do not match", "1 Infinite Loop, Cupertino, CA 95014, USA",
                newspaperSubscription.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfSubscriberIsNull() throws Exception {
        newspaperSubscription = new NewspaperSubscription(null,
                "Tester Daily", 100, "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfNewspaperIsNull() throws Exception {
        newspaperSubscription = new NewspaperSubscription("Tony Tester",
                null, 100, "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfStandingChargeLessThanZero() throws Exception {
        newspaperSubscription = new NewspaperSubscription("Tony Tester",
                "Tester Daily", -1, "1 Infinite Loop, Cupertino, CA 95014, USA");
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsIllegalArgumentExceptionIfAddressIsNull() throws Exception {
        newspaperSubscription = new NewspaperSubscription("Tony Tester",
                "Tester Daily", 100, null);
    }

    @Test
    public void getsMaxChargeInPence() throws Exception {
        assertEquals(100, newspaperSubscription.getMaxChargeInPence());
    }

    @Test
    public void computeTotalChargeInPenceEqualsToGetMaxChargeInPence() throws Exception {
        int maxCharge = newspaperSubscription.getMaxChargeInPence();
        int totalCharge = newspaperSubscription.computeTotalChargeInPence();
        assertThat(maxCharge, is(totalCharge));
    }

    @After
    public void tearDown() throws Exception {
        newspaperSubscription = null;
    }
}
