package dao;

import api.OfferDetails;
import api.OfferInfo;

import java.util.List;

public interface OffersDao {

    List<OfferInfo> getOffers();

    List<OfferDetails> getOfferDetails(Integer offerId);
}
