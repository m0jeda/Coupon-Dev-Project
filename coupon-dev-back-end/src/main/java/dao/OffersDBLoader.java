package dao;

import api.OfferDetails;
import api.OfferInfo;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OffersDBLoader implements OffersDao {
    private static final String GET_OFFERS = "select DISTINCT o.id, o.name, o.image_url, r.name as retailerName from offers o, retailers r, retailer_offers ro where ro.retailer_id = r.id and ro.offer_id = o.id";
    private static final String GET_OFFER_DETAILS = "select * from offers where id = ?";
    private static final Logger LOGGER = LoggerFactory.getLogger(OffersDBLoader.class);
    private final Jdbi jdbi;
    private final OffersMapper offersMapper;
    private final OfferDetailsMapper offerDetailsMapper;

    public OffersDBLoader(Jdbi jdbi, OffersMapper offersMapper, OfferDetailsMapper offerDetailsMapper) {
        this.jdbi = jdbi;
        this.offersMapper = offersMapper;
        this.offerDetailsMapper = offerDetailsMapper;
    }

    @Override
    public List<OfferInfo> getOffers() {
        return jdbi.withHandle(handle -> {
            try (PreparedStatement statement = handle.getConnection().prepareStatement(GET_OFFERS)) {
                try (ResultSet results = statement.executeQuery()) {
                    Set<OfferInfo> offers = new HashSet<>();
                    while (results.next()) {
                        LOGGER.trace("processing next result");
                        OfferInfo offerInfo = offersMapper.map(results);
                        if (!offers.contains(offerInfo)) {
                            offers.add(offerInfo);
                        }
                    }
                    ArrayList<OfferInfo> offerValues = new ArrayList<>();
                    offerValues.addAll(offers);
                    return offerValues;
                }
            } catch (Exception e) {
                LOGGER.error("Error getting offers'");
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<OfferDetails> getOfferDetails(Integer offerId) {
        return jdbi.withHandle(handle -> {
            try (PreparedStatement statement = handle.getConnection().prepareStatement(GET_OFFER_DETAILS)) {
                statement.setInt(1, offerId);
                try (ResultSet results = statement.executeQuery()) {
                    Set<OfferDetails> offerDetailsSet = new HashSet<>();
                    while(results.next()) {
                        LOGGER.trace("processing next result");
                        OfferDetails offerDetails = offerDetailsMapper.map(results);
                        if (!offerDetailsSet.contains(offerDetails)) {
                            offerDetailsSet.add(offerDetails);
                        }
                    }
                    ArrayList<OfferDetails> offerDetailsValues = new ArrayList<>();
                    offerDetailsValues.addAll(offerDetailsSet);
                    return offerDetailsValues;
                }
            } catch (Exception e) {
                LOGGER.error("Error getting offer details'");
                throw new RuntimeException(e);
            }
        });
    }
}
