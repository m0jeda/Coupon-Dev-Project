package resources;

import api.OfferDetails;
import api.OfferInfo;
import com.codahale.metrics.annotation.Timed;
import dao.OffersDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offers")
@Api(value = "Coupon Offers")
@Produces(MediaType.APPLICATION_JSON)
public class OffersResource {
    private final OffersDao offersDao;

    public OffersResource(OffersDao offersDao) {
        this.offersDao = offersDao;
    }

    @GET
    @Timed
    @ApiOperation("List offers from various retailers")
    public List<OfferInfo> getOffers() {
        return offersDao.getOffers();
    }

    @GET
    @Timed
    @Path("/details")
    @ApiOperation("List offer details")
    public List<OfferDetails> getOfferDetails(@QueryParam("offerId") final Integer offerId) {
        if (offerId == null) {
            throw new BadRequestException("No valid offer id specified.");
        }
        return offersDao.getOfferDetails(offerId);
    }
}
