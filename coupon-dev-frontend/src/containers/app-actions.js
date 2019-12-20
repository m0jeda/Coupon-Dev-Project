const GET_OFFERS = "GET_OFFERS";
export const REQ_GET_OFFERS = `REQ_${GET_OFFERS}`;
export const RECV_GET_OFFERS = `RECV_${GET_OFFERS}`;
export const XHR_ERR_GET_OFFERS = `XHR_ERR_${GET_OFFERS}`;
const GET_OFFER_DETAILS = "GET_OFFERS_DETAILS";
export const REQ_GET_OFFER_DETAILS = `REQ_${GET_OFFER_DETAILS}`;
export const RECV_GET_OFFER_DETAILS = `RECV_${GET_OFFER_DETAILS}`;
export const XHR_ERR_GET_OFFER_DETAILS = `XHR_ERR_${GET_OFFER_DETAILS}`;

export const getOffers = () => {
  return {
    type: REQ_GET_OFFERS
  };
};

export const getOfferDetails = offerId => {
  return {
    type: REQ_GET_OFFER_DETAILS,
    offerId
  };
};
