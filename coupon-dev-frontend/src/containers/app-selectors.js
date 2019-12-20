import { createSelector } from "reselect";

const offersSelector = state => {
  return state.availableOffers ? state.availableOffers : {};
};

export const getOffersSelector = createSelector(
  offersSelector,
  availableOffers => {
    return { availableOffers };
  }
);
