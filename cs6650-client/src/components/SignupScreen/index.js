import {useState} from "react";

const SignupScreen = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");

    const onSignupBtnClick = () => {
        const newUser = {username, password, name, email}
        console.log(newUser)
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
            </div>
        </div>
    )


}

export default SignupScreen;