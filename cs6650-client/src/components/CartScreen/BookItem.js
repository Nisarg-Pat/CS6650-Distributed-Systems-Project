import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {addToCartThunk} from "../../data/carts/carts-thunk";
import {useNavigate} from "react-router-dom";

const BookItem = ({book}) => {

    // const {currentUser} = useSelector((state) => state.users)

    // const dispatch = useDispatch();
    // const navigate = useNavigate();

    return (
        <div>
            {book.name}
        </div>
    )
}

export default BookItem;