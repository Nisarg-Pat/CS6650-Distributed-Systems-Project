import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {addToCartThunk} from "../../data/carts/carts-thunk";
import {useNavigate} from "react-router-dom";

const BookItem = ({book}) => {

    const {currentUser} = useSelector((state) => state.users)

    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onClickAddToCartBtn = async () => {
        const cart = {userId: currentUser.id, bookId: book.id}
        await dispatch(addToCartThunk(cart))
        navigate("/cart");
    }

    return (
        <div>
            {book.name}
            <button className={"btn btn-danger"} onClick={onClickAddToCartBtn}> Add To Cart </button>
        </div>
    )
}

export default BookItem;