import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import './App.css';
import HomeScreen from "./components/HomeScreen";
import SignupScreen from "./components/SignupScreen";

function App() {
  return (
    <div className="container mt-4 mb-4">
        <BrowserRouter>
            <Routes>
                <Route index element={<HomeScreen/>}/>
                <Route path={"/signup"} element={<SignupScreen/>}/>
            </Routes>
        </BrowserRouter>
    </div>
  );
}

export default App;
