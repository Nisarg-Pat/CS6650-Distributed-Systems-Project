import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import './App.css';
import HomeScreen from "./components/HomeScreen";
import SignupScreen from "./components/SignupScreen";

import usersReducer from "./data/users/users-reducer";
import {configureStore} from "@reduxjs/toolkit";
import {Provider} from "react-redux";
import LoginScreen from "./components/LoginScreen";
import SearchScreen from "./components/SearchScreen";
import CartScreen from "./components/CartScreen";
import NewBookScreen from "./components/NewBookScreen";
import booksReducer from "./data/books/books-reducer";

const store = configureStore({
    reducer:{
        users: usersReducer,
        books: booksReducer,
    }
})

function App() {
  return (
    <div className="container mt-4 mb-4">
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route index element={<HomeScreen/>}/>
                    <Route path={"/home"} element={<HomeScreen/>}/>
                    <Route path={"/signup"} element={<SignupScreen/>}/>
                    <Route path={"/login"} element={<LoginScreen/>}/>
                    <Route path={"/search"} element={<SearchScreen/>}/>
                    <Route path={"/cart"} element={<CartScreen/>}/>
                    <Route path={"/newbook"} element={<NewBookScreen/>}/>
                </Routes>
            </BrowserRouter>
        </Provider>
    </div>
  );
}

export default App;
