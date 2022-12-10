import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import BookItem from "../CartScreen/BookItem";
import {getCartThunk} from "../../data/carts/carts-thunk";

const CartScreen = () => {
    const {currentUser} = useSelector((state) => state.users);
    const {userCart} = useSelector((state) => state.carts);

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(getCartThunk(currentUser))
    }, [currentUser, dispatch])

    return (
        <>
            <NavBar/>
            {
                userCart.map((book, key) => <BookItem book={book} key={key}/>)
            }
        </>
    );
}

export default CartScreen;