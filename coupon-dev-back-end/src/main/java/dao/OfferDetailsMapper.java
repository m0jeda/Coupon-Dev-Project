package dao;

import api.OfferDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferDetailsMapper {

    public OfferDetails map(final ResultSet r) throws SQLException {
        final Integer id = r.getInt("id");
        final String name = r.getString("name");
        final String description = r.getString("description");
        final String terms = r.getString("terms");
        final String imageUrl = r.getString("image_url");
        final String expiration = r.getString("expiration");

        return new OfferDetails(id, name, description, terms, imageUrl, expiration);
    }
}
