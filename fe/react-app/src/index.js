import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';

// import App from './App';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>
// );

// import LibraryPage from './pages/sample/LibraryPage';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <LibraryPage />
//   </React.StrictMode>
// );

// import ButtonPage from './pages/material/ButtonPage';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <ButtonPage />
//   </React.StrictMode>
// );

// import {CommentPage as Cp} from './pages/sample/CommentPage';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <Cp />
//   </React.StrictMode>
// );

// import CapacityPage from './pages/reactive/CapacityPage';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <CapacityPage />
//   </React.StrictMode>
// );

// import EventPage from './pages/event/EventPage';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <EventPage />
//   </React.StrictMode>
// );

// import TestRouterApp from './TestRouterApp';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <TestRouterApp />
//   </React.StrictMode>
// );

import RenderingPage from './pages/rendering/RenderingPage';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <RenderingPage />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
