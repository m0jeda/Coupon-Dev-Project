package dao;

import api.OfferDetails;
import org.junit.Assert;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OfferDetailsMapperTest {
    @Test
    public void testMappingValidResultSet() throws SQLException {
        OfferDetailsMapper offerDetailsMapper = new OfferDetailsMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt(eq("id"))).thenReturn(1);
        when(resultSet.getString(eq("name"))).thenReturn("testName");
        when(resultSet.getString(eq("description"))).thenReturn("testDescription");
        when(resultSet.getString(eq("terms"))).thenReturn("testTerms");
        when(resultSet.getString(eq("image_url"))).thenReturn("testImageUrl");
        when(resultSet.getString(eq("expiration"))).thenReturn("testExpiration");

        OfferDetails offerDetails = offerDetailsMapper.map(resultSet);

        Assert.assertEquals(offerDetails.getId().intValue(), 1);
        Assert.assertEquals(offerDetails.getName(), "testName");
        Assert.assertEquals(offerDetails.getDescription(), "testDescription");
        Assert.assertEquals(offerDetails.getTerms(), "testTerms");
        Assert.assertEquals(offerDetails.getImageUrl(), "testImageUrl");
        Assert.assertEquals(offerDetails.getExpiration(), "testExpiration");
    }
}