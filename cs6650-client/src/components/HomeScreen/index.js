import {Link} from "react-router-dom";
import {useDispatch, useSelector} from "react-redux";
import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {bookListThunk} from "../../data/books/books-thunk";

const HomeScreen = () => {
    const {currentUser} = useSelector((state) => state.users)
    const {booksList} = useSelector((state) => state.books)

    const dispatch = useDispatch()

    useEffect(() => {
        dispatch(bookListThunk(currentUser))
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
                        <ul>
                            {
                                booksList.map((book) =>
                                <li key={book.id}>{book.name}</li>)
                            }
                        </ul>
                        {/*{bookList.map((item) => {*/}
                        {/*    return (*/}
                        {/*    <li>*/}
                        {/*        {item.name}*/}
                        {/*    </li>)*/}
                        {/*})}*/}
                        <Link to={"/newbook"} className={"btn btn-dark"}>Add</Link>
                    </div>
                    <div>
                        <h2>
                            Sell List
                        </h2>
                    </div>
                </>
            }
        </>
    )
}

export default HomeScreen;