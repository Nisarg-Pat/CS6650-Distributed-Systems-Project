import {Link} from "react-router-dom";
import {useSelector} from "react-redux";
import NavBar from "../NavBar";
import React from "react";

const HomeScreen = () => {
    const {currentUser} = useSelector((state) => state.users)

    return (
        <>
            <NavBar/>
            {
                !currentUser &&
                <>
                    <Link to={"/signup"} className={"btn btn-primary"}>Signup</Link>
                    <Link to={"/login"} className={"btn btn-primary"}>Login</Link>
                </>
            }

            {
                currentUser &&
                <>
                    <h2>
                        Hello {currentUser.name}
                    </h2>
                </>
            }
        </>
    )
}

export default HomeScreen;