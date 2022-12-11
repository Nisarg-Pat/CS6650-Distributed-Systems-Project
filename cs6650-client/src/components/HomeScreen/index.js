import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {bookListThunk} from "../../data/books/books-thunk";
import BookItem from "../BookItem";
import {getCartThunk} from "../../data/carts/carts-thunk";

const HomeScreen = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {shelfList, sellList} = useSelector((state) => state.books)
    const {cartList} = useSelector((state) => state.carts)
    const dispatch = useDispatch()

    useEffect(() => {
        if(currentUser) {
            dispatch(bookListThunk(currentUser))
            dispatch(getCartThunk(currentUser))
        }
    }, [currentUser, dispatch])



    return (
        <>
            <NavBar/>
            {
                !currentUser &&
                <>
                    <Link to={"/signup"} className={"btn btn-primary me-4"}>Signup</Link>
                    <Link to={"/login"} className={"btn btn-primary"}>Login</Link>
                </>
            }

            {
                currentUser &&
                <>
                    <h2>
                        Hello {currentUser.name}
                    </h2>
                    <div>
                        <h2>
                            Your Book Shelf
                        </h2>
                        {
                            shelfList.map((book, key) => <BookItem book={book} key={key} type={'shelf'}/>)
                        }
                        <Link to={"/newbook"} className={"btn btn-dark"}>Add</Link>
                    </div>
                    <div>
                        <h2>
                            Your Sell List
                        </h2>
                        {
                            sellList.map((book, key) => <BookItem book={book} key={key} type={'sell'}/>)
                        }
                    </div>
                </>
            }
        </>
    )
}

export default HomeScreen;