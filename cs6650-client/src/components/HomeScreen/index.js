import {Link} from "react-router-dom";

const HomeScreen = () => {

    return (
        <>
            <Link to={"/signup"} className={"btn btn-primary"}>Signup</Link>
        </>
    )
}

export default HomeScreen;