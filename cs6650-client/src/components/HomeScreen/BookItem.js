import React from "react";
import {useDispatch} from "react-redux";
import {sellBookThunk} from "../../data/books/books-thunk";

const BookItem = ({book}) => {

    const dispatch = useDispatch();

    const onClickSellBook = () => {
        dispatch(sellBookThunk(book));
    }

    return (
        <div>
            {book.name}
            <button className={"btn btn-danger"} onClick={onClickSellBook}> Sell </button>
        </div>
    )
}

export default BookItem;