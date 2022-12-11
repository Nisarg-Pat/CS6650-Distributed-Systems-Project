import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signupThunk} from "../../data/users/users-thunks";
import NavBar from "../NavBar";
import {Navigate} from "react-router-dom";
import {setError} from "../../data/users/users-reducer";

const SignupScreen = () => {

    const {currentUser, error} = useSelector((state) => state.users)

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const dispatch = useDispatch();

    const onSignupBtnClick = () => {
        if(username === '' || password === '' || name === '' || email === '') {
            dispatch(setError("Fields cannot be empty"));
            return;
        }
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
                <h2>
                    Signup
                </h2>
                <table className={"ds-text-center ds-table ds-center"}>
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
                            <td className={"mb-2"}><input value={password} onChange={(e) => {
                                dispatch(setError(null));
                                setPassword(e.target.value)
                            }}/></td>
                        </tr>
                        <tr>
                            <td>Name:</td>
                            <td><input value={name} onChange={(e) => {
                                dispatch(setError(null));
                                setName(e.target.value)
                            }}/></td>
                        </tr>
                        <tr>
                            <td>Email:</td>
                            <td><input value={email} onChange={(e) => {
                                dispatch(setError(null));
                                setEmail(e.target.value)
                            }}/></td>
                        </tr>
                        <tr>
                            <td colSpan={2}>
                                <button className={"btn btn-primary ds-btn-green"} onClick={onSignupBtnClick}>Signup</button>
                            </td>
                        </tr>
                    </tbody>
                    <tfoot>

                    </tfoot>

                </table>
                {
                    error &&
                    <span className={"ds-error"}>{error}</span>
                }
            </div>
        </div>
    )


}

export default SignupScreen;