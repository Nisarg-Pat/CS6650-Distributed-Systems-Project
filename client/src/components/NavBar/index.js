import React from "react";
import {Link, useLocation, useNavigate} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import {logoutThunk} from "../../data/users/users-thunks";
import {resetBooks} from "../../data/books/books-reducer";

const NavBar = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {server} = useSelector((state)=> state.server);
    const {pathname} = useLocation()
    const parts = pathname.split('/')

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const logoutBtnClick = async () => {
        await dispatch(logoutThunk({newUser:currentUser, url: server.url}));
        dispatch(resetBooks())
        navigate("/home");
    }

    return (
        <ul className="nav nav-pills mb-4">
            <li className="nav-item">
                <Link to="/home" className={`nav-link ds-text-white ${parts[1] === '' || parts[1] === 'home' ? 'active' : ''}`}>
                    Home
                </Link>
            </li>
            <li className="nav-item">
                <Link to="/search" className={`nav-link ds-text-white ${parts[1] === 'search' ? 'active' : ''}`}>
                    Search Books
                </Link>
            </li>
            {
                currentUser &&
                <>
                    <li className="nav-item">
                        <Link to="/cart" className={`nav-link ds-text-white ${parts[1] === 'cart' ? 'active' : ''}`}>
                            Cart
                        </Link>
                    </li>
                    <li className="nav-item">
                        <button className={`btn btn-primary ds-btn-red ds-height-full`} onClick={logoutBtnClick}>
                            Logout
                        </button>
                    </li>
                </>

            }
            <li className="nav-item">
                <Link to="/server" className={`nav-link ds-text-white ${parts[1] === 'server' ? 'active' : ''}`}>
                    Set Server
                </Link>
            </li>
        </ul>
    )
}

export default NavBar;