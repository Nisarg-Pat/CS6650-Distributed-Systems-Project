import {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {signupThunk} from "../../data/users/users-thunks";

const SignupScreen = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const {currentUser} = useSelector((state) => state.users)

    const dispatch = useDispatch();

    const onSignupBtnClick = () => {
        const newUser = {username, password, name, email};
        console.log(newUser);
        dispatch(signupThunk(newUser));
    }

    return (
        <div className={"row"}>
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
                {
                    currentUser &&
                    <h2>
                        Hello {currentUser.name}
                    </h2>
                }
            </div>
        </div>
    )


}

export default SignupScreen;