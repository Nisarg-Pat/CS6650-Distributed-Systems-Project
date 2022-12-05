import React from "react";
import {Link, useLocation} from "react-router-dom";
import {useSelector} from "react-redux";

const NavBar = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {pathname} = useLocation()
    const parts = pathname.split('/')

    return (
        <ul className="nav nav-pills">
            <li className="nav-item">
                <Link to="/" className={`nav-link ${parts[1] === '' ? 'active' : ''}`}>
                    Home
                </Link>
            </li>
            <li className="nav-item">
                <Link to="/searchbooks" className={`nav-link ${parts[1] === 'searchbooks' ? 'active' : ''}`}>
                    Search Books
                </Link>
            </li>
            {/*{*/}
            {/*    currentUser &&*/}
            {/*    <li className="nav-item">*/}
            {/*        <button className={`btn btn-danger`} onClick={logoutBtnClick}>*/}
            {/*            Logout*/}
            {/*        </button>*/}
            {/*    </li>*/}
            {/*}*/}

        </ul>
    )
}

export default NavBar;