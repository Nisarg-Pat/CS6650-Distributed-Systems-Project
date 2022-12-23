import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {bookListThunk} from "../../data/books/books-thunk";
import BookItem from "../BookItem";
import {getCartThunk} from "../../data/carts/carts-thunk";

const HomeScreen = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {server} = useSelector((state => state.server));
    const {shelfList, sellList} = useSelector((state) => state.books)
    const dispatch = useDispatch()

    useEffect(() => {
        if(currentUser) {
            dispatch(bookListThunk({user:currentUser, url:server.url}))
            dispatch(getCartThunk({user:currentUser, url:server.url}))
        }
    }, [currentUser, dispatch, server.url])



    return (
        <>
            <NavBar/>
            <h1>
                BookKeeper
            </h1>
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
                    <h2 className={"mb-4"}>
                        Hello {currentUser.name}
                    </h2>
                    <div className={"row"}>
                        <div className={"col-6"}>
                            <div className={"ds-border p-2"}>
                                <h2>
                                    Your Book Shelf <Link to={"/newbook"} className={"btn ds-btn-green ds-right"}>Add</Link>
                                </h2>
                                <div className={"row"}>
                                    {
                                        shelfList.map((book) =>
                                        <div className={"col-6 mb-4"}>
                                            <BookItem book={book} key={book.id} type={'shelf'}/>
                                        </div>)
                                    }
                                </div>

                            </div>
                        </div>
                        <div className={"col-6"}>
                            <div className={"ds-border p-2"}>
                                <h2>
                                    Your Sell List
                                </h2>
                                <div className={"row"}>
                                    {
                                        sellList.map((book) =>
                                        <div className={"col-6 mb-4"}>
                                            <BookItem book={book} key={book.id} type={'sell'}/>
                                        </div>)
                                    }
                                </div>
                            </div>
                        </div>
                    </div>

                </>
            }
        </>
    )
}

export default HomeScreen;