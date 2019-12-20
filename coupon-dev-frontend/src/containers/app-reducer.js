import {
  REQ_GET_OFFERS,
  RECV_GET_OFFERS,
  XHR_ERR_GET_OFFERS,
  RECV_GET_OFFER_DETAILS,
  XHR_ERR_GET_OFFER_DETAILS,
  REQ_GET_OFFER_DETAILS
} from "./app-actions";

export const defaultState = {
  availableOffersList: undefined,
  offerDetails: undefined
};

export const offersReducer = (state, action) => {
  const newState = Object.assign({}, state);
  switch (action.type) {
    case REQ_GET_OFFERS:
      break;
    case RECV_GET_OFFERS:
      newState.availableOffersList = action.offersPayload;
      break;
    case XHR_ERR_GET_OFFERS:
      break;
    case REQ_GET_OFFER_DETAILS:
      break;
    case RECV_GET_OFFER_DETAILS:
      newState.offerDetails = action.offerDetailsPayload;
      break;
    case XHR_ERR_GET_OFFER_DETAILS:
      break;
    default:
      return newState;
  }
  return newState;
};
