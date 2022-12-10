import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {getCartThunk} from "../../data/carts/carts-thunk";
import BookItem from "../BookItem";

const CartScreen = () => {
    const {currentUser} = useSelector((state) => state.users);
    const {cartList} = useSelector((state) => state.carts);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getCartThunk(currentUser))
    }, [currentUser, dispatch])

    return (
        <>
            <NavBar/>
            {
                cartList.map((book, key) => <BookItem book={book} key={key} type={'cart'}/>)
            }
        </>
    );
}

export default CartScreen;