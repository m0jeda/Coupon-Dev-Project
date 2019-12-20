import React from "react";
import ReactDOM from "react-dom";
import { Provider } from "react-redux";
import { Route, BrowserRouter } from "react-router-dom";
import { createStore, combineReducers, applyMiddleware } from "redux";
import createSagaMiddleware from "redux-saga";
import { all } from "redux-saga/effects";
import logger from "redux-logger";
import "./index.css";
import App from "./containers/App";
import * as serviceWorker from "./serviceWorker";
import { offersReducer as availableOffers } from "./containers/app-reducer";
import { containerSagas } from "./containers/app-sagas";

function* rootSaga() {
  yield all([containerSagas()]);
}

const sagaMiddleware = createSagaMiddleware();

let middleware = [logger, sagaMiddleware];

let store = createStore(
  combineReducers({
    availableOffers
  }),
  applyMiddleware(...middleware)
);

sagaMiddleware.run(rootSaga, store.dispatch);

ReactDOM.render(
  <Provider store={store}>
    <BrowserRouter>
      <Route path="/offers" component={App} />
    </BrowserRouter>
  </Provider>,
  document.getElementById("root")
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
