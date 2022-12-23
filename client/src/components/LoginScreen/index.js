import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {loginThunk} from "../../data/users/users-thunks";
import NavBar from "../NavBar";
import {Navigate} from "react-router-dom";
import {setError} from "../../data/users/users-reducer";

const LoginScreen = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const {currentUser, error} = useSelector((state) => state.users)
    const {server} = useSelector((state)=> state.server);

    const dispatch = useDispatch();

    const onLoginBtnClick = () => {
        const newUser = {username, password};
        const url = server.url;
        dispatch(loginThunk({newUser, url}));
    }

    return (
        <div className={"row"}>
            {
                currentUser && <Navigate to={'/'} replace={true}/>
            }
            <NavBar/>
            <div className={"ds-text-center"}>
                <h2>
                    Login
                </h2>
                <table className={"ds-table ds-center"}>
                    <tbody>
                        <tr>
                            <td>Username:</td>
                            <td><input value={username} onChange={(e) => {
                                dispatch(setError(null));
                                setUsername(e.target.value)
                            }}/></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input value={password} onChange={(e) => {
                                dispatch(setError(null));
                                setPassword(e.target.value)
                            }}/></td>
                        </tr>
                        <tr>
                            <td colSpan={2}><button className={"btn btn-primary ds-btn-green"} onClick={onLoginBtnClick}>Login</button></td>
                        </tr>
                    </tbody>
                </table>
                {
                    error &&
                    <span className={"ds-error"}>{error}</span>
                }
            </div>
        </div>
    )
}

export default LoginScreen;