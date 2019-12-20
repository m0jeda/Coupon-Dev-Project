package resources;

import api.OfferDetails;
import api.OfferInfo;
import dao.OffersDao;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.BadRequestException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OffersResourceTest {

    @Mock
    private OffersDao offersDao;

    @InjectMocks
    private OffersResource resources;
    private final OfferInfo offerInfo = new OfferInfo(1, "testName", "testImageUrl", "testRetailName");
    private final OfferDetails offerDetails = new OfferDetails(1, "testName", "testDescr", "testTerms", "testImageUrl", "testExpiration");

    @Before
    public void setup() {
        when(offersDao.getOffers()).thenReturn(Arrays.asList(offerInfo));
        when(offersDao.getOfferDetails(1)).thenReturn(Arrays.asList(offerDetails));
    }

    @Test
    public void testGetOffers() {
        List<OfferInfo> offerInfoList = resources.getOffers();
        Assert.assertEquals(Arrays.asList(offerInfo), offerInfoList);
    }

    @Test
    public void testGetOfferDetails() {
        List<OfferDetails> offerDetailsList = resources.getOfferDetails(1);
        Assert.assertEquals(Arrays.asList(offerDetails), offerDetailsList);
    }

    @Test (expected = BadRequestException.class)
    public void testGetOfferDetailsException() {
        resources.getOfferDetails(null);
    }

}