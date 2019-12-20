import "jest";
import { getOffersSelector } from "./app-selectors";

describe("app-selectors", () => {
  let state;
  beforeEach(() => {
    state = {
      availableOffers: {
        availableOffersList: [],
        offerDetails: []
      }
    };
  });
  test("getOffersSelector return expected state availableOffersList", () => {
    state.availableOffers.availableOffersList = [
      {
        id: 2368,
        name: "testName",
        imageUrl: "testUrl",
        retailerName: "testRetailer"
      }
    ];
    const results = getOffersSelector(state);
    expect(results.availableOffers.availableOffersList).toEqual(
      state.availableOffers.availableOffersList
    );
  });
  test("getOffersSelector return expected state offerDetails", () => {
    state.availableOffers.offerDetails = [
      {
        id: 2368,
        name: "testName",
        imageUrl: "testUrl",
        description: "testDescription",
        expiration: "testExpiration",
        terms: "testTerms"
      }
    ];
    const results = getOffersSelector(state);
    expect(results.availableOffers.offerDetails).toEqual(
      state.availableOffers.offerDetails
    );
  });
});
