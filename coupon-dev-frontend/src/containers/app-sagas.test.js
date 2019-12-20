import "jest";
import { call } from "redux-saga/effects";
import { getOffersSaga, getOfferDetailsSaga } from "./app-sagas";
import { REQ_GET_OFFERS, REQ_GET_OFFER_DETAILS } from "./app-actions";

describe("app-sagas", () => {
  test("REQ_GET_OFFERS dispatches appropriate action to store on success", () => {
    const action = {
      type: REQ_GET_OFFERS
    };
    const iterator = getOffersSaga(action);
    expect(iterator.next().value).toEqual(
      call(fetch, `http://localhost:8080/offers`)
    );
  });
  test("REQ_GET_OFFERS dispatches appropriate action to store on success", () => {
    const action = {
      type: REQ_GET_OFFER_DETAILS,
      offerId: 1
    };
    const iterator = getOfferDetailsSaga(action);
    expect(iterator.next().value).toEqual(
      call(fetch, `http://localhost:8080/offers/details?offerId=1`)
    );
  });
});
