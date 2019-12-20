import "jest";
import {
  getOffers,
  REQ_GET_OFFERS,
  getOfferDetails,
  REQ_GET_OFFER_DETAILS
} from "./app-actions";

describe("app-actions", () => {
  test("getOffers dispatches the correct action", () => {
    const result = getOffers();
    expect(result.type).toBe(REQ_GET_OFFERS);
  });
  test("getOfferDetails dispatches the correct action", () => {
    const result = getOfferDetails(0);
    expect(result.type).toBe(REQ_GET_OFFER_DETAILS);
  });
});
