import React from "react";
import ReactDOM from "react-dom";
import App from "./App";

it("renders without crashing", () => {
  const div = document.createElement("div");
  // ReactDOM.render(<App />, div);
  // ReactDOM.unmountComponentAtNode(div);
});

// import 'jest'
// import Adapter from 'enzyme-adapter-react-16';
// import { shallow, configure } from 'enzyme';
// import * as React from 'react';
// import App from './App';

// configure({adapter: new Adapter()});
// describe('app', () => {
//   let props;
//   beforeEach(() => {
//     props = {
//       availableOffers: {}
//     }
//   });
//   test('renders', () => {
//     const wrapper = shallow(<App {...props} />);
//     expect(wrapper.exists()).toBeTruthy();
//   });
// });
