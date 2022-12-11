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
                    <div className={"row"}>
                        <div className={"col-6"}>
                            <div className={"ds-border p-2"}>
                                <h2>
                                    Your Book Shelf <Link to={"/newbook"} className={"btn ds-btn-green ds-right"}>Add</Link>
                                </h2>
                                <div className={"row"}>
                                    {
                                        shelfList.map((book, key) =>
                                        <div className={"col-6 mb-4 ds-text-center"}>
                                            <BookItem book={book} key={key} type={'shelf'}/>
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
                                {
                                    sellList.map((book, key) => <BookItem book={book} key={key} type={'sell'}/>)
                                }
                            </div>
                        </div>
                    </div>

                </>
            }
        </>
    )
}

export default HomeScreen;