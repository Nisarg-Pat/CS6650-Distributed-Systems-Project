import React from "react";
import {Link, useLocation} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {logoutThunk} from "../../data/users/users-thunks";

const NavBar = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {pathname} = useLocation()
    const parts = pathname.split('/')
    console.log(currentUser)

    const dispatch = useDispatch();

    const logoutBtnClick = () => {
        dispatch(logoutThunk(currentUser));
    }

    return (
        <ul className="nav nav-pills mb-4">
            <li className="nav-item">
                <Link to="/" className={`nav-link ${parts[1] === '' ? 'active' : ''}`}>
                    Home
                </Link>
            </li>
            <li className="nav-item">
                <Link to="/search" className={`nav-link ${parts[1] === 'search' ? 'active' : ''}`}>
                    Search Books
                </Link>
            </li>
            {
                currentUser &&
                <>
                    <li className="nav-item">
                        <Link to="/cart" className={`nav-link ${parts[1] === 'cart' ? 'active' : ''}`}>
                            Cart
                        </Link>
                    </li>
                    <li className="nav-item">
                        <button className={`btn btn-danger`} onClick={logoutBtnClick}>
                            Logout
                        </button>
                    </li>
                </>

            }
        </ul>
    )
}

export default NavBar;