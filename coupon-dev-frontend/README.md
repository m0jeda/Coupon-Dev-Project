# Coupon Dev Project - Frontend

The application has a JavaScript (JS) frontend with the React JS library to build a SPA.
The redux-saga library is used to make aynchronous calls to the REST API calls and fetch the data. The state of the application is managed using the JS library Redux.

## Root Directory
The skeleton of this React app was created using [https://github.com/facebook/create-react-app](https://github.com/facebook/create-react-app).
This directory holds package-json for dependencies and scripts for the app.

## src Directory
This is the entry point of the app. The index.js file sets up the SPA. The redux store and redux-saga are configured and the App component renders here.

## components Directory
Since the app is simple, all the components for the page are sourced and rendered in the App.js file.
As the app scales, it will be helpful to extract certain components such as the popup as it's own file in this directory.

## containers Directory
The bulk of the code and flow of the application is here. 

### App.css
App.js renders the SPA. Most of the styling for the SPA is in App.css. Flexbox is used for aligning the components.

### App.js
When the page first loads, it will make a REST API call to receive all the offers' information. As the data is fetched, a loading page is shown. Once the data is received, a header is displayed. Below that, all the offers are displayed in a gallery view using cards. A card displays the offer image, the retailer, and the name of the offer. A button labeled 'More Details' can be hovered over with a mouse to get the individual's offer details. A popup is rendered with the information. When the popup is rendered, a second REST API call is made using the offer's id to get the offer details. mapDispatchToProps is used to dispatch the actions getOffers and getOfferDetails. connect is used to "connect" the App component to the redux store.

The popup component resource can be found here [https://react-popup.elazizi.com/home/](https://react-popup.elazizi.com/home/).

### app-actions.js
The actions for the SPA are here. When the functions getOffers or getOfferDetails are called, a REQ action is returned. There are also RECV actions for receiving the data from the API calls. There is an XHR action for any errors returned from the API call. getOfferDetails takes in the offerId as a parameter.

### app-reducer.js
The reducer will update the state of the application based on the different actions. The state doesn't change for REQ actions. For RECV actions, the state is updated with the payload received from the API calls. Currently, nothing is done for XHR actions. To enhance this application, an error object should be added to the state. When an XHR action is seen, the error object will be updated. If in the App.js an error is seen, it should render an alert message saying there was an issue retrieving offers data.

### app-saga.js
The redux-saga library is used to make the API calls. The action type and payload are correctly returned to the reducer. If there is an error, the action type will be XHR and the error is returned. getOfferDetailsSaga takes in the action as the parameter to extract the offerId needed to make the API call.

### app-selectors.js
The offersSelector listens for the state to be updated and then it is used as a parameter for getOffersSelector to return availableOffers as a prop to App.js.

### Trade-offs
I chose React for the frontend because of its many pros such as the Virtual DOM that makes updates really quick to create a highly dynamic UI. Also the one-direction flow creates stable code by not letting child elements affect parent elements.

Redux was used since it provides a centralized data store that different components can access so state objects do not need to be passed along.

redux-saga provides a simple way to make asynchronous API calls to fetch data. It also has access to the redux app state and can dispatch redux actions.

Jest was used as the JS testing framework due to its simplicity and advantageous. It allows for asynchronous testing and has a watch mode.

### Additional features
The error alert mentioned above is a good additional feature.
Another addition is a way to track, search, and filter offers.

# Getting Started

## Install Node

Run the following command if you do not have node and npm installed. Assuming you have Homebrew installed.

```sh
brew install node
```

I am using node version v11.14.0 and npm 6.9.0

## Available Scripts

In this directory, you can run:

### `npm install`

To download all the node_modules needed for the app.

### `npm start`

Runs the app in the development mode.<br>
Open [http://localhost:3000/offers](http://localhost:3000/offers) to view it in the browser.

The page will reload if you make edits.<br>
You will also see any lint errors in the console.

When you first run this command, it will open the [http://localhost:3000](http://localhost:3000). You must navigate to the /offers route. An enhancement is to load the correct url upon npm start.

If you stop the app and restart it, you may see an error about the port already being in use.
Run the command
```sh
lsof -i :3000
```
to find the process still running and run the command
```sh
kill -9 <pid>
```
to stop the process and free up the port.

### `npm test`

Launches the test runner in the interactive watch mode.<br>

In App.test.js I was trying to test that the component renders correctly. The tests are commented out because I was seeing the error 

`Invariant Violation: Could not find "store" in the context of "Connect(App)". Either wrap the root component in a <Provider>, or pass a custom React context provider to <Provider> and the corresponding React context consumer to Connect(App) in connect options.`

This error is occurring because the Root Container (index.js) does not have the connect line in App.js:74. To fix and run this test, some refactoring needs to be explicit that App has access to the store which is defined in index.js:33.

If the npm tests keep running infinitely, close the process and stop the frontend and backend processes. Try running `npm test` again.

### `npm run prettier:format`

Fix any ESlint or prettier errors.<br>

### `npm run build`

Builds the app for production to the `build` folder.<br>
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>
Your app is ready to be deployed!

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can’t go back!**

If you aren’t satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (Webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you’re on your own.

You don’t have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn’t feel obligated to use this feature. However we understand that this tool wouldn’t be useful if you couldn’t customize it when you are ready for it.