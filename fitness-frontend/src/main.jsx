// import React from 'react'
// import ReactDOM from 'react-dom/client'

// import { Provider } from 'react-redux'
// // import store from './store/store'
// import store from "./store/store";


// import App from './App'

// // As of React 18
// const root = ReactDOM.createRoot(document.getElementById('root'))
// root.render(
//   <Provider store={store}>
//     <App />
//   </Provider>,
// )


// import React from 'react';
// import ReactDOM from 'react-dom/client';
// import { Provider } from 'react-redux';
// // import { store } from './store/store';
// import { store } from './store/store';

// // import store from './store';
// import App from './App';

// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <Provider store={store}>
//     <App />
//   </Provider>
// );

// import React from "react";
// import ReactDOM from "react-dom/client";
// import { Provider } from "react-redux";
// import { store } from "./store/store";
// import App from "./App";
// import { AuthProvider } from "react-oauth2-code-pkce";
// import { authConfig } from "./authConfig";

// ReactDOM.createRoot(document.getElementById("root"))
// root.render(
//   <AuthProvider authConfig={authConfig}>
//     <Provider store={store}>
//       <App />
//     </Provider>
//   </AuthProvider>
// );

// import React from "react";
// import ReactDOM from "react-dom/client";
// import { Provider } from "react-redux";
// import { store } from "./store/store";
// import App from "./App";
// import { AuthProvider } from "react-oauth2-code-pkce";
// import { authConfig } from "./authConfig";

// const root = ReactDOM.createRoot(document.getElementById("root"));

// root.render(
//   <AuthProvider authConfig={authConfig}
//                 loadingComponent={<div>Loading...</div>}>
//     <Provider store={store}>
//       <App />
//     </Provider>
//   </AuthProvider>,
// );


import React from "react";
import ReactDOM from "react-dom/client";
import { Provider } from "react-redux";
import { store } from "./store/store";
import App from "./App";
import { AuthProvider } from "react-oauth2-code-pkce";
import { authConfig } from "./authConfig";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <AuthProvider authConfig={authConfig} loadingComponent={<div>Loading...</div>}>
    <Provider store={store}>
      <App />
    </Provider>
  </AuthProvider>
);




