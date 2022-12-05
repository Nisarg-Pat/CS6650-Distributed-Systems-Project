import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import './App.css';
import HomeScreen from "./components/HomeScreen";
import SignupScreen from "./components/SignupScreen";

import usersReducer from "./data/users/users-reducer";
import {configureStore} from "@reduxjs/toolkit";
import {Provider} from "react-redux";
import LoginScreen from "./components/LoginScreen";

const store = configureStore({
    reducer:{
        users: usersReducer
    }
})

function App() {
  return (
    <div className="container mt-4 mb-4">
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route index element={<HomeScreen/>}/>
                    <Route path={"/signup"} element={<SignupScreen/>}/>
                    <Route path={"/login"} element={<LoginScreen/>}/>
                </Routes>
            </BrowserRouter>
        </Provider>

    </div>
  );
}

export default App;
