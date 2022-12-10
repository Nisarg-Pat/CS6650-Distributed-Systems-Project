import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {searchBookThunk} from "../../data/books/books-thunk";
import BookItem from "../BookItem";


const SearchScreen = () => {

    const {currentUser} = useSelector((state) => state.users)

    const {searchList} = useSelector((state) => state.books);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(searchBookThunk(""))
    }, [dispatch])

    const searchHandler = (search) => {
        dispatch(searchBookThunk(search))
    }

    return (
        <>
            <NavBar/>
            <input onChange={(e) => searchHandler(e.target.value)}/>
            {
                searchList
                    .filter((book) => currentUser === null || book.userId !== currentUser.id)
                    .map((book, key) => <BookItem book={book} key={key} type={'search'}/>)
            }
        </>

    );
}

export default SearchScreen;