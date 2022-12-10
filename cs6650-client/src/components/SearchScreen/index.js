import NavBar from "../NavBar";
import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {searchBookThunk} from "../../data/books/books-thunk";
import BookItem from "../HomeScreen/BookItem";


const SearchScreen = () => {

    const {searchList} = useSelector((state) => state.books);

    const dispatch = useDispatch();

    const searchHandler = (search) => {
        dispatch(searchBookThunk(search))
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