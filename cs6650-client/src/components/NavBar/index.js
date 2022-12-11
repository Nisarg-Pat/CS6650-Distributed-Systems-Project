import React from "react";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {logoutThunk} from "../../data/users/users-thunks";
import {resetBooks} from "../../data/books/books-reducer";
import {resetCarts} from "../../data/carts/carts-reducer";

const NavBar = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {pathname} = useLocation()
    const parts = pathname.split('/')

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const logoutBtnClick = async () => {
        await dispatch(logoutThunk(currentUser));
        dispatch(resetBooks())
        dispatch(resetCarts())
        navigate("/home");
    }

    return (
        <ul className="nav nav-pills mb-4">
            <li className="nav-item">
                <Link to="/home" className={`nav-link ${parts[1] === '' || parts[1] === 'home' ? 'active' : ''}`}>
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