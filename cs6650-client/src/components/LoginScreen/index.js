import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {loginThunk} from "../../data/users/users-thunks";
import NavBar from "../NavBar";

const LoginScreen = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const {currentUser} = useSelector((state) => state.users)

    const dispatch = useDispatch();

    const onLoginBtnClick = () => {
        const newUser = {username, password};
        dispatch(loginThunk(newUser));
    }

    return (
        <div className={"row"}>
            <NavBar/>
            <div className={"ds-text-center"}>
                <label>
                    Username:
                    <input value={username} onChange={(e) => setUsername(e.target.value)}/>
                </label>
                <br/>
                <label>
                    Password:
                    <input value={password} onChange={(e) => setPassword(e.target.value)}/>
                </label>
                <br/>
                <button className={"btn btn-secondary"} onClick={onLoginBtnClick}>Login</button>
            </div>
            {
                currentUser &&
                <h2>
                    Hello {currentUser.name}
                </h2>
            }
        </div>
    )


}

export default LoginScreen;