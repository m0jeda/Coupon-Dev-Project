package dao;

import api.OfferInfo;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OffersMapperTest {

    @Test
    public void testMappingValidResultSet() throws SQLException {
        OffersMapper offersMapper = new OffersMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt(eq("id"))).thenReturn(1);
        when(resultSet.getString(eq("name"))).thenReturn("testName");
        when(resultSet.getString(eq("image_url"))).thenReturn("testImageUrl");
        when(resultSet.getString(eq("retailerName"))).thenReturn("testRetailerName");

        OfferInfo offerInfo = offersMapper.map(resultSet);

        Assert.assertEquals(offerInfo.getId().intValue(), 1);
        Assert.assertEquals(offerInfo.getName(), "testName");
        Assert.assertEquals(offerInfo.getImageUrl(), "testImageUrl");
        Assert.assertEquals(offerInfo.getRetailerName(), "testRetailerName");
    }
}