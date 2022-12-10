import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signupThunk} from "../../data/users/users-thunks";
import NavBar from "../NavBar";
import {Navigate} from "react-router-dom";

const SignupScreen = () => {

    const {currentUser} = useSelector((state) => state.users)

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const dispatch = useDispatch();

    const onSignupBtnClick = () => {
        const newUser = {username, password, name, email};
        dispatch(signupThunk(newUser));
    }

    return (
        <div className={"row"}>
            {
                currentUser && <Navigate to={'/'} replace={true}/>
            }
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
                <label>
                    Name:
                    <input value={name} onChange={(e) => setName(e.target.value)}/>
                </label>
                <br/>
                <label>
                    Email:
                    <input value={email} onChange={(e) => setEmail(e.target.value)}/>
                </label>
                <br/>
                <button className={"btn btn-secondary"} onClick={onSignupBtnClick}>Signup</button>
            </div>
        </div>
    )


}

export default SignupScreen;