import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {searchBookThunk} from "../../data/books/books-thunk";
import BookItem from "../SearchScreen/BookItem";
import {initSearch} from "../../data/books/books-reducer";


const SearchScreen = () => {

    const {searchList} = useSelector((state) => state.books);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(initSearch())
    }, [dispatch])

    const searchHandler = (search) => {
        if(search === '') {
            dispatch(initSearch());
        } else {
            dispatch(searchBookThunk(search))
        }
    }

    return (
        <>
            <NavBar/>
            <input onChange={(e) => searchHandler(e.target.value)}/>
            {
                searchList.map((book, key) => <BookItem book={book} key={key}/>)
            }
        </>

    );
}

export default SearchScreen;