import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {searchBookThunk} from "../../data/books/books-thunk";
import BookItem from "../BookItem";


const SearchScreen = () => {

    const {currentUser} = useSelector((state) => state.users)
    const {server} = useSelector((state) => state.server)

    const {searchList} = useSelector((state) => state.books);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(searchBookThunk({search:"", url:server.url}))
    }, [dispatch, server.url])

    const searchHandler = (search) => {
        dispatch(searchBookThunk({search, url:server.url}))
    }

    return (
        <>
            <NavBar/>
            <h2 className={"mb-4"}>
                Search: <input onChange={(e) => searchHandler(e.target.value)}/>
            </h2>
            <div className={"row"}>
                {
                    searchList
                        .filter((book) => currentUser === null || book.userId !== currentUser.id)
                        .map((book, key) =>
                            <div className={"col-3 mb-4"}>
                                <BookItem book={book} key={key} type={'search'}/>
                            </div>)
                }
            </div>


        </>

    );
}

export default SearchScreen;