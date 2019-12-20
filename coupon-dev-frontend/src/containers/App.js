import * as React from "react";
import { connect } from "react-redux";
import Popup from "reactjs-popup";
import "./App.css";
import logo from "../logo.svg";
import { getOffers, getOfferDetails } from "./app-actions";
import { getOffersSelector } from "./app-selectors";

class App extends React.Component {
  componentDidMount() {
    this.props.getOffers();
  }
  getDetails(offerDetails) {
    if (offerDetails) {
      return (
        <div className="offer-details">
          {offerDetails[0].name}
          <br />
          Description: {offerDetails[0].description}
          <p />
          Expires: {offerDetails[0].expiration}
          <p />
          Terms: {offerDetails[0].terms}
        </div>
      );
    } else {
      return undefined;
    }
  }
  render() {
    const { availableOffers } = this.props;
    return !Boolean(availableOffers.availableOffersList) ? (
      <div className="App-load">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>Loading...</h1>
      </div>
    ) : (
      <div className="App">
        <header className="App-header">
          <div className="App-header-overlay">
            <h1>welcome to</h1>
            <h3>COUPONS</h3>
          </div>
        </header>
        <div className="App-gallery-row">
          {availableOffers.availableOffersList.map(o => {
            return (
              <div className="gallery">
                <img src={o.imageUrl} alt="offer" />
                <div className="gallery-info">
                  <div className="gallery-retailer">{o.retailerName}</div>
                  <div className="offer-name">{o.name}</div>
                  <Popup
                    trigger={<button className="button">More Details</button>}
                    on="hover"
                    onOpen={() => this.props.getOfferDetails(o.id)}
                  >
                    {<div>{this.getDetails(availableOffers.offerDetails)}</div>}
                  </Popup>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}
const mapDispatchToProps = {
  getOffers,
  getOfferDetails
};

export default connect(
  getOffersSelector,
  mapDispatchToProps
)(App);
export { App };
