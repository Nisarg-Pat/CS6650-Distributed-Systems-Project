import NavBar from "../NavBar";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {buyCartThunk, getCartThunk} from "../../data/carts/carts-thunk";
import BookItem from "../BookItem";
import {useNavigate} from "react-router-dom";

const CartScreen = () => {
    const {currentUser} = useSelector((state) => state.users);
    const {cartList} = useSelector((state) => state.books);

    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => {
        dispatch(getCartThunk(currentUser))
    }, [currentUser, dispatch])

    const buyBtnClickHandler = async () => {
        const cart = {bookList:cartList, newUser:currentUser}
        await dispatch(buyCartThunk(cart));
        navigate("/home");
    }

    console.log(cartList)

    return (
        <>
            <NavBar/>
            {
                cartList.map((book, key) => <BookItem book={book} key={key} type={'cart'}/>)
            }
            Total: ${cartList.reduce((acc, book) => acc + book.sellPrice, 0)}
            <br/>
            <button className={"btn btn-dark"} onClick={buyBtnClickHandler}>Buy</button>
        </>
    );
}

export default CartScreen;