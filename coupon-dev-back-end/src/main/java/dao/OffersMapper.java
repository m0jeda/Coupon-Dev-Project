package dao;

import api.OfferInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffersMapper {

    public OfferInfo map(final ResultSet r) throws SQLException {
        final Integer id = r.getInt("id");
        final String name = r.getString("name");
        final String imageUrl = r.getString("image_url");
        final String retailerName = r.getString("retailerName");

        return new OfferInfo(id, name, imageUrl, retailerName);
    }
}
