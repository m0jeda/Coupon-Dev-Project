import { call, put, takeLatest } from "redux-saga/effects";
import * as actions from "./app-actions";

export function* getOffersSaga() {
  try {
    const response = yield call(fetch, `http://localhost:8080/offers`);
    const data = yield call([response, response.json]);
    yield put({ type: actions.RECV_GET_OFFERS, offersPayload: data });
  } catch (exception) {
    yield put({ type: actions.XHR_ERR_GET_OFFERS, error: exception });
  }
}

export function* getOfferDetailsSaga(action) {
  try {
    const { offerId } = action;
    const response = yield call(
      fetch,
      `http://localhost:8080/offers/details?offerId=${offerId}`
    );
    const data = yield call([response, response.json]);
    yield put({
      type: actions.RECV_GET_OFFER_DETAILS,
      offerDetailsPayload: data
    });
  } catch (exception) {
    yield put({ type: actions.XHR_ERR_GET_OFFER_DETAILS, error: exception });
  }
}

export function* containerSagas() {
  yield takeLatest(actions.REQ_GET_OFFERS, getOffersSaga);
  yield takeLatest(actions.REQ_GET_OFFER_DETAILS, getOfferDetailsSaga);
}
