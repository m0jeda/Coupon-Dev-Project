import "jest";
import {
  RECV_GET_OFFERS,
  REQ_GET_OFFERS,
  REQ_GET_OFFER_DETAILS,
  XHR_ERR_GET_OFFERS,
  XHR_ERR_GET_OFFER_DETAILS,
  RECV_GET_OFFER_DETAILS
} from "./app-actions";
import { offersReducer } from "./app-reducer";

describe("app-reducer", () => {
  let state;
  beforeEach(() => {
    state = {
      availableOffersList: [],
      offerDetails: []
    };
  });
  test("REQ_GET_OFFERS does not update state", () => {
    const action = {
      type: REQ_GET_OFFERS
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList).toEqual([]);
    expect(result.offerDetails).toEqual([]);
  });
  test("RECV_GET_OFFERS updates state properly", () => {
    const action = {
      type: RECV_GET_OFFERS,
      offersPayload: [
        {
          id: 2368,
          name: "testName",
          imageUrl: "testUrl",
          retailerName: "testRetailer"
        }
      ]
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList[0].id).toBe(2368);
    expect(result.availableOffersList[0].name).toBe("testName");
    expect(result.availableOffersList[0].imageUrl).toBe("testUrl");
    expect(result.availableOffersList[0].retailerName).toBe("testRetailer");
    expect(result.offerDetails).toEqual([]);
  });
  test("XHR_ERR_GET_OFFERS does not update state", () => {
    const action = {
      type: XHR_ERR_GET_OFFERS
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList).toEqual([]);
    expect(result.offerDetails).toEqual([]);
  });
  test("REQ_GET_OFFER_DETAILS does not update state", () => {
    const action = {
      type: REQ_GET_OFFER_DETAILS
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList).toEqual([]);
    expect(result.offerDetails).toEqual([]);
  });
  test("RECV_GET_OFFER_DETAILS updates state properly", () => {
    const action = {
      type: RECV_GET_OFFER_DETAILS,
      offerDetailsPayload: [
        {
          id: 2368,
          name: "testName",
          imageUrl: "testUrl",
          description: "testDescription",
          expiration: "testExpiration",
          terms: "testTerms"
        }
      ]
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList).toEqual([]);
    expect(result.offerDetails[0].id).toBe(2368);
    expect(result.offerDetails[0].name).toBe("testName");
    expect(result.offerDetails[0].imageUrl).toBe("testUrl");
    expect(result.offerDetails[0].description).toBe("testDescription");
    expect(result.offerDetails[0].expiration).toBe("testExpiration");
    expect(result.offerDetails[0].terms).toBe("testTerms");
  });
  test("XHR_ERR_GET_OFFER_DETAILS does not update state", () => {
    const action = {
      type: XHR_ERR_GET_OFFER_DETAILS
    };
    const result = offersReducer(state, action);
    expect(result.availableOffersList).toEqual([]);
    expect(result.offerDetails).toEqual([]);
  });
});
